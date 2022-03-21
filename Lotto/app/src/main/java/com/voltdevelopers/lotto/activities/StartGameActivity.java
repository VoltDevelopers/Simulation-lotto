package com.voltdevelopers.lotto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.voltdevelopers.lotto.R;
import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.data.Settings;
import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.game.Game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class StartGameActivity extends AppCompatActivity {

    EditText presetGameCount, significantGameCount, startMoney;
    Button buttonStart;
    RadioButton btn1, btn2, btn3;
    ArrayList<RadioButton> arrayBtn;
    TextView textData, textData2;
    SimpleDateFormat sdf;

    private Dialog settingsDialog;
    private LineChart firstChart, secondChart;
    private static final int[] COLORS = {Color.RED, Color.YELLOW, Color.WHITE, Color.MAGENTA, Color.BLUE, Color.GREEN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        showSettings();
        initSettingsBtn();
    }

    private void initSettingsBtn() {
        ImageButton settingsBtn = findViewById(R.id.settingsImageButton);
        settingsBtn.setOnClickListener(view -> {
            showSettings();
        });
    }

    @SuppressLint("SimpleDateFormat")
    private void showSettings() {
        Database.createInstance();

        settingsDialog = new Dialog(this);
        settingsDialog.setCancelable(false);
        settingsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(R.layout.settings_modal);
        initRes();
        buttonStart.setOnClickListener(view -> {

            if (btn1.isChecked()) {
                Settings.getInstance().setMoneyPerWin(11.23);
                Settings.getInstance().setExtractionsPerRound(1);
            }
            if (btn2.isChecked()) {
                Settings.getInstance().setMoneyPerWin(18);
                Settings.getInstance().setExtractionsPerRound(1);
            }
            if (btn3.isChecked()) {
                Settings.getInstance().setMoneyPerWin(250);
                Settings.getInstance().setExtractionsPerRound(2);
            }

            int preGames = Optional.ofNullable(presetGameCount.getText())
                    .map(Editable::toString)
                    .filter(s -> s.matches("\\d+"))
                    .map(Integer::valueOf)
                    .orElse(1000);

            int significantGames = Optional.ofNullable(significantGameCount.getText())
                    .map(Editable::toString)
                    .filter(s -> s.matches("\\d+"))
                    .map(Integer::valueOf)
                    .orElse(1000);

            int money = Optional.ofNullable(startMoney.getText())
                    .map(Editable::toString)
                    .filter(s -> s.matches("\\d+"))
                    .map(Integer::valueOf)
                    .orElse(0);

            Settings.getInstance().setStartMoney(money);
            Settings.getInstance().setPresetGameCount(preGames);

            try {
                Game game = new Game(significantGames);
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

    private void initRes() {
        arrayBtn = new ArrayList<>();
        buttonStart = settingsDialog.findViewById(R.id.buttonStartModal);
        btn1 = settingsDialog.findViewById(R.id.radioButton1);
        arrayBtn.add(btn1);
        btn2 = settingsDialog.findViewById(R.id.radioButton2);
        arrayBtn.add(btn2);
        btn3 = settingsDialog.findViewById(R.id.radioButton3);
        arrayBtn.add(btn3);
        presetGameCount = settingsDialog.findViewById(R.id.presetGameCount);
        significantGameCount = settingsDialog.findViewById(R.id.significantGameCount);
        startMoney = settingsDialog.findViewById(R.id.startMoney);
    }

    private void initAll() {
        initFirstChart();
        initFirstYAxis();
        initFirstXAxis();
        addFirstDescription();
        addFirstLegend();
        addDataToFirstChart();
        addFinalResultsFirstChart();

        initSecondChart();
        initSecondYAxis();
        initSecondXAxis();
        addSecondDescription();
        addSecondLegend();
        addDataToSecondChart();
        addFinalResultsSecondChart();
    }

    private void initFirstChart() {
        firstChart = findViewById(R.id.graphic_1);

        firstChart.setDragEnabled(true);
        firstChart.setScaleEnabled(false);
        firstChart.setDrawBorders(true);
        firstChart.setPinchZoom(false);
        firstChart.setAutoScaleMinMaxEnabled(true);
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
        yAxis.setLabelCount(10, false);
        yAxis.setTextColor(Color.GREEN);
        yAxis.removeAllLimitLines();
        yAxis.setGranularity(1f);
        yAxis.setCenterAxisLabels(false);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value) + " Wins";
            }
        });

    }

    private void initFirstXAxis() {

        XAxis xAxis = firstChart.getXAxis();
        xAxis.setTextColor(Color.GREEN);
        xAxis.setLabelCount(1, false);
        xAxis.removeAllLimitLines();
        xAxis.setAxisMaximum(Database.getInstance().getSizeSignificantPulls());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value) + " Games";
            }
        });

    }

    private void addFirstDescription() {
        Description description = new Description();
        description.setText("Numero di vittorie");
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

            for (int j = 0; j < Database.getInstance().getSizeSignificantPulls(); j++) { //ciclo per scorrere tutte le vincite del singolo giocatore

                if (Database.getInstance().getPlayerWinList(i).get(j) == Settings.getInstance().getExtractionsPerRound()) {//se quel pattern ha vinto in quella prtita la percentuale aumenta
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

    private void addFinalResultsFirstChart() {

        textData = findViewById(R.id.textData);
        String text = "Percentuale di vittorie in " + Database.getInstance().getSizeSignificantPulls() + " partite:\n";

        for (int i = 0; i < 5; i++) {

            double aproxPerc = Math.round(Database.getInstance().getPlayerWinPercentage(i) * 100.0) / 100.0;
            text += "Giocatore " + (i + 1) + " percentuale --> " + aproxPerc + "%\n";

        }
        textData.setText(text);
    }

    private void initSecondChart() {

        secondChart = findViewById(R.id.graphic_2);

        secondChart.setDragEnabled(true);
        secondChart.setScaleEnabled(false);
        secondChart.setDrawBorders(true);
        secondChart.setAutoScaleMinMaxEnabled(true);
        secondChart.setPinchZoom(false);
        secondChart.setDrawGridBackground(false);
        secondChart.getAxisRight().setEnabled(false);
        secondChart.setBorderColor(Color.GREEN);
        secondChart.setExtraOffsets(0, 5f, 0, 5f);

    }

    private void initSecondYAxis() {

        YAxis yAxis = secondChart.getAxisLeft();
        yAxis.setSpaceBottom(0);
        yAxis.setSpaceTop(0);
        yAxis.setDrawGridLines(false);
        yAxis.setLabelCount(10, false);
        yAxis.setTextColor(Color.GREEN);
        yAxis.removeAllLimitLines();
        yAxis.setGranularity(1f);
        yAxis.setCenterAxisLabels(false);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value) + "$";
            }
        });

    }

    private void initSecondXAxis() {

        XAxis xAxis = secondChart.getXAxis();
        xAxis.setTextColor(Color.GREEN);
        xAxis.setLabelCount(1, false);
        xAxis.removeAllLimitLines();
        xAxis.setAxisMaximum(Database.getInstance().getSizeSignificantPulls());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value) + " Games";
            }
        });

    }

    private void addSecondDescription() {
        Description description = new Description();
        description.setText("'Guadagno' netto");
        description.setTextColor(Color.GREEN);
        description.setTextSize(15);
        description.setPosition(900, 100);
        secondChart.setDescription(description);
    }

    private void addSecondLegend() {

        Legend legend;
        legend = secondChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setEnabled(true);
        legend.setTextColor(Color.GREEN);
        legend.setYOffset(10);
        legend.setWordWrapEnabled(true);
        legend.setMaxSizePercent(0.7f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        LegendEntry[] legendEntries = new LegendEntry[6];

        for (int i = 0; i < 5; i++) {

            LegendEntry entry = new LegendEntry();
            entry.formColor = COLORS[i];
            entry.label = "giocatore " + (i + 1);
            legendEntries[i] = entry;

        }

        LegendEntry entry = new LegendEntry();
        entry.formColor = Color.GREEN;
        entry.label = "Banco";
        legendEntries[5] = entry;

        legend.setCustom(legendEntries);
    }

    private void addDataToSecondChart() {

        ArrayList<ArrayList<Entry>> yValues = new ArrayList<>();
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        for (int i = 0; i < 6; i++) { //ciclo per le 5 linee
            yValues.add(new ArrayList<>());
                for (int j = 0; j < Database.getInstance().getSizeSignificantPulls(); j++) { //ciclo per scorrere tutte le vincite del singolo giocatore
                    if (yValues.size() == 6){
                        yValues.get(i).add(new Entry(j, Database.getInstance().getSystemNetList().get(j).floatValue()));
                    }else{
                        yValues.get(i).add(new Entry(j, Database.getInstance().getPlayerNetList(i).get(j).floatValue()));
                    }
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

    private void addFinalResultsSecondChart() {

        textData2 = findViewById(R.id.textData2);
        String text = "Credito dei giocatori dopo " + Database.getInstance().getSizeSignificantPulls() + " partite:\n";

        for (int i = 0; i < 5; i++) {

            double aproxPerc = Math.round(Database.getInstance().getPlayerNet(i) * 100.0) / 100.0;
            text += "Credito del giocatore " + (i + 1) + " --> " + aproxPerc + "$\n";

        }
        text += "Credito del banco " + (6) + " --> " + Math.round(Database.getInstance().getSystemNetList().get( Database.getInstance().getSystemNetList().size() - 1) * 100.0) / 100.0 + "$\n";
        textData2.setText(text);
    }

    public void saveText(View view) {
        try (FileOutputStream fos = new FileOutputStream(getExternalPath())) {
            String text = Database.getInstance().toString();
            fos.write(text.getBytes());
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        fileSavedSuccessfully();
    }

    private File getExternalPath() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "lotto_output" + sdf.format(new Date()) + (".txt"));
        }else{
            return new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "lotto_output" + sdf.format(new Date()) + (".txt"));
        }
    }

    public void showText(View view) {
        Intent intent = new Intent(StartGameActivity.this, StatisticActivity.class);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Log.i("INFO", "Started Activity" + intent.getIdentifier());
        }
    }

    private void fileSavedSuccessfully(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            Toast.makeText(this, "File saved to Downloads folder", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "File saved to /storage/self/primary/Android/data/com.voltdevelopers.lotto", Toast.LENGTH_SHORT).show();
        }
    }
}