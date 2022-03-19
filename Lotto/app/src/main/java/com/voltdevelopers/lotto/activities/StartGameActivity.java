package com.voltdevelopers.lotto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.voltdevelopers.lotto.R;
import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.data.Settings;

import java.util.ArrayList;

public class StartGameActivity extends AppCompatActivity {

    private static final String TAG = "PatternGameActivity";
    private LineChart firstChart;
    private Database db;
    private static final int COLORS[] = {Color.RED,Color.YELLOW,Color.WHITE,Color.MAGENTA,Color.BLUE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        db = Database.getInstance();

        initAll();
    }

    private void initAll(){

        initChart();
        initYAxis();
        initXAxis();
        addDescription();
        addLegend();
        addDataToGraph();

    }

    private void initChart(){

        firstChart = (LineChart) findViewById(R.id.graphic_1);

        firstChart.setDragEnabled(true);
        firstChart.setScaleEnabled(true);
        firstChart.setDrawBorders(true);
        firstChart.setPinchZoom(false);
        firstChart.setDrawGridBackground(false);
        firstChart.getAxisRight().setEnabled(false);
        firstChart.setBorderColor(Color.GREEN);
        firstChart.setExtraOffsets(0,5f,0,5f);

    }

    private void initYAxis(){

        YAxis yAxis = firstChart.getAxisLeft();
        yAxis.setSpaceBottom(0);
        yAxis.setSpaceTop(0);
        yAxis.setDrawGridLines(false);
        yAxis.setLabelCount(100,true);
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

    private void initXAxis(){

        XAxis xAxis = firstChart.getXAxis();
        xAxis.setTextColor(Color.GREEN);
        xAxis.setLabelCount(db.getSizeSignificantPulls(),true);
        xAxis.removeAllLimitLines();
        xAxis.setAxisMaximum(db.getSizeSignificantPulls());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);

    }

    private void addDescription(){

        Description description = new Description();
        description.setText("Percentuale di vincite");
        description.setTextColor(Color.GREEN);
        description.setTextSize(15);
        description.setPosition(900,100);
        firstChart.setDescription(description);

    }

    private void addLegend(){

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

    private void addDataToGraph(){

        ArrayList<ArrayList<Entry>> yValues = new ArrayList<>();
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();


        for(int i = 0; i < Settings.getInstance().getPlayersToPlay().length; i++){ //ciclo per le 5 linee

            yValues.add(new ArrayList<>());
            float y = 0;

            for(int j = 0; j < db.getSizeSignificantPulls(); j++){ //ciclo per scorrere tutte le vincite del singolo giocatore

                if(db.getPlayerWinList(i).get(j) == Settings.getInstance().getExtractionsPerRound()){//se quel pattern ha vinto in quella prtita la percentuale aumenta

                    y++;

                }
                yValues.get(i).add(new Entry(j,y));

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

}