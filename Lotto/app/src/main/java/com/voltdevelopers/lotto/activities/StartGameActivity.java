package com.voltdevelopers.lotto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.voltdevelopers.lotto.MainActivity;
import com.voltdevelopers.lotto.R;
import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.data.Settings;

import java.util.ArrayList;

public class StartGameActivity extends AppCompatActivity {

    EditText presetGameCount, significantGameCount;
    Button buttonStart;
    RadioButton btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    private static final String TAG = "PatternGameActivity";

    private Dialog settingsDialog;
    private LineChart firstChart, secondChart;
    private Database db;
    private static final int[] COLORS = {Color.RED, Color.YELLOW, Color.WHITE, Color.MAGENTA, Color.BLUE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        db = Database.getInstance();
        showSettings();
        initSettingsBtn();
    }

    private void initSettingsBtn(){
        ImageButton settingsBtn = findViewById(R.id.settingsImageButton);
        settingsBtn.setOnClickListener(view -> {
            showSettings();
        });
    }

    private void showSettings(){
        settingsDialog = new Dialog(this);
        settingsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(R.layout.settings_modal);
        buttonStart = (Button)settingsDialog.findViewById(R.id.buttonStartModal);
        buttonStart.setOnClickListener(view -> {
            Log.i("Modal", "BTN START WAS PRESSED");

            btn1 = settingsDialog.findViewById(R.id.radioButton1);
            presetGameCount = settingsDialog.findViewById(R.id.presetGameCount);
            significantGameCount = settingsDialog.findViewById(R.id.significantGameCount);

            Settings.getInstance().setMoneyPerWin(btn1.isActivated() ? 18 : 11.23);

            Settings.getInstance().setExtractions(5);
            Settings.getInstance().setExtractionsPerRound(1);
            Settings.getInstance().setPresetGameCount(Integer.parseInt (presetGameCount.getText().toString()));
            try {
                Game game = new Game(Integer.parseInt (significantGameCount.getText().toString()));
                game.gameLoop();
            } catch (InputException e) {
                e.printStackTrace();
            }
            Database.getInstance().assignWins();

            initAll();
            settingsDialog.dismiss();
        });
        settingsDialog.show();
    }

    private void initAll() {
        initFirstChart();
        initFirstYAxis();
        initFirstXAxis();
        addFirstDescription();
        addFirstLegend();
        addDataToFirstChart();

        initSecondChart();
        initSecondYAxis();
        initSecondXAxis();
        addSecondDescription();
        addSecondLegend();
        addDataToSecondChart();
    }

    private void initFirstChart() {
        firstChart = findViewById(R.id.graphic_1);

        firstChart.setDragEnabled(true);
        firstChart.setScaleEnabled(false);
        firstChart.setDrawBorders(true);
        firstChart.setPinchZoom(false);
        firstChart.setDrawGridBackground(false);
        firstChart.getAxisRight().setEnabled(false);
        firstChart.setBorderColor(Color.GREEN);
        firstChart.setExtraOffsets(0, 5f, 0, 5f);
    }

    private void initFirstYAxis() {

        YAxis yAxis = firstChart.getAxisLeft();
        yAxis.setSpaceBottom(0);
        yAxis.setSpaceTop(0);
        yAxis.setDrawGridLines(false);
        yAxis.setLabelCount(10, true);
        yAxis.setTextColor(Color.GREEN);
        yAxis.removeAllLimitLines();
        yAxis.setAxisMaximum(100); //percentuale massima
        yAxis.setGranularity(1f);
        yAxis.setCenterAxisLabels(false);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value) + "%";
            }
        });

    }

    private void initFirstXAxis() {

        XAxis xAxis = firstChart.getXAxis();
        xAxis.setTextColor(Color.GREEN);
        xAxis.setLabelCount(db.getSizeSignificantPulls() / 10, true);
        xAxis.removeAllLimitLines();
        xAxis.setAxisMaximum(db.getSizeSignificantPulls());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);

    }

    private void addFirstDescription() {

        Description description = new Description();
        description.setText("Percentuale di vincite");
        description.setTextColor(Color.GREEN);
        description.setTextSize(15);
        description.setPosition(900, 100);
        firstChart.setDescription(description);

    }

    private void addFirstLegend() {

        Legend legend;
        legend = firstChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setEnabled(true);
        legend.setTextColor(Color.GREEN);
        legend.setYOffset(10);
        legend.setWordWrapEnabled(true);
        legend.setMaxSizePercent(0.7f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        LegendEntry[] legendEntries = new LegendEntry[5];

        for (int i = 0; i < legendEntries.length; i++) {

            LegendEntry entry = new LegendEntry();
            entry.formColor = COLORS[i];
            entry.label = "giocatore " + (i + 1);
            legendEntries[i] = entry;

        }

        legend.setCustom(legendEntries);

    }

    private void addDataToFirstChart() {

        ArrayList<ArrayList<Entry>> yValues = new ArrayList<>();
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();


        for (int i = 0; i < Settings.getInstance().getPlayersToPlay().length; i++) { //ciclo per le 5 linee

            yValues.add(new ArrayList<>());
            float y = 0;

            for (int j = 0; j < db.getSizeSignificantPulls(); j++) { //ciclo per scorrere tutte le vincite del singolo giocatore

                if (db.getPlayerWinList(i).get(j) == Settings.getInstance().getExtractionsPerRound()) {//se quel pattern ha vinto in quella prtita la percentuale aumenta
                    y++;
                }
                yValues.get(i).add(new Entry(j, y));

            }

            lineDataSets.add(new LineDataSet(yValues.get(i), ""));
            lineDataSets.get(i).setFillAlpha(110);
            lineDataSets.get(i).setLineWidth(1f);
            lineDataSets.get(i).setDrawCircles(false);
            lineDataSets.get(i).setValueTextSize(4);
            lineDataSets.get(i).setColor(COLORS[i]);
            lineDataSets.get(i).setValueTextColor(COLORS[i]);
            dataSets.add(lineDataSets.get(i));

        }

        LineData data = new LineData(dataSets);
        firstChart.setData(data);
        firstChart.invalidate();

    }

    private void initSecondChart(){

        secondChart = findViewById(R.id.graphic_2);

        secondChart.setDragEnabled(true);
        secondChart.setScaleEnabled(false);
        secondChart.setDrawBorders(true);
        secondChart.setPinchZoom(false);
        secondChart.setDrawGridBackground(false);
        secondChart.getAxisRight().setEnabled(false);
        secondChart.setBorderColor(Color.GREEN);
        secondChart.setExtraOffsets(0, 5f, 0, 5f);

    }

    private void initSecondYAxis(){

        YAxis yAxis = secondChart.getAxisLeft();
        yAxis.setSpaceBottom(0);
        yAxis.setSpaceTop(0);
        yAxis.setDrawGridLines(false);
        yAxis.setLabelCount(10, true);
        yAxis.setTextColor(Color.GREEN);
        yAxis.removeAllLimitLines();
        yAxis.setAxisMaximum(1000); //soldi massimi guadagnati
        yAxis.setGranularity(1f);
        yAxis.setCenterAxisLabels(false);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value) + "$";
            }
        });

    }

    private void initSecondXAxis(){

        XAxis xAxis = secondChart.getXAxis();
        xAxis.setTextColor(Color.GREEN);
        xAxis.setLabelCount(db.getSizeSignificantPulls() / 10, true);
        xAxis.removeAllLimitLines();
        xAxis.setAxisMaximum(db.getSizeSignificantPulls());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);

    }

    private void addSecondDescription(){

        Description description = new Description();
        description.setText("Soldi guadagnati");
        description.setTextColor(Color.GREEN);
        description.setTextSize(15);
        description.setPosition(900, 100);
        secondChart.setDescription(description);

    }

    private void addSecondLegend(){

        Legend legend;
        legend = secondChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setEnabled(true);
        legend.setTextColor(Color.GREEN);
        legend.setYOffset(10);
        legend.setWordWrapEnabled(true);
        legend.setMaxSizePercent(0.7f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        LegendEntry[] legendEntries = new LegendEntry[5];

        for (int i = 0; i < legendEntries.length; i++) {

            LegendEntry entry = new LegendEntry();
            entry.formColor = COLORS[i];
            entry.label = "giocatore " + (i + 1);
            legendEntries[i] = entry;

        }

        legend.setCustom(legendEntries);

    }

    private void addDataToSecondChart(){

        ArrayList<ArrayList<Entry>> yValues = new ArrayList<>();
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();


        for (int i = 0; i < Settings.getInstance().getPlayersToPlay().length; i++) { //ciclo per le 5 linee

            yValues.add(new ArrayList<>());
            float y = 0;

            for (int j = 0; j < db.getSizeSignificantPulls(); j++) { //ciclo per scorrere tutte le vincite del singolo giocatore

                y = (float) db.getPlayerMoneyWon(i);
                yValues.get(i).add(new Entry(j, y));

            }

            lineDataSets.add(new LineDataSet(yValues.get(i), ""));
            lineDataSets.get(i).setFillAlpha(110);
            lineDataSets.get(i).setLineWidth(1f);
            lineDataSets.get(i).setDrawCircles(false);
            lineDataSets.get(i).setValueTextSize(4);
            lineDataSets.get(i).setColor(COLORS[i]);
            lineDataSets.get(i).setValueTextColor(COLORS[i]);
            dataSets.add(lineDataSets.get(i));

        }

        LineData data = new LineData(dataSets);
        secondChart.setData(data);
        secondChart.invalidate();

    }

    public static class SettingsDialogFragment extends DialogFragment {
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder
                    .setView(R.layout.settings_modal)
                    .create();
        }
    }
}