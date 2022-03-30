package com.voltdevelopers.lotto.layout;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.data.Settings;

import java.util.ArrayList;

public class StdChart {

    private static final int[] COLORS = {Color.RED, Color.YELLOW, Color.WHITE, Color.MAGENTA, Color.BLUE, Color.GREEN};
    private static final String[] NAMES = {"Il Copione", "Il Ritardatario", "L'Azzardoso", "Il Testardo", "L'Ingenuo"};

    private LineChart lineChart;
    private YAxis yAxis;
    private XAxis xAxis;
    private Legend legend;
    private LineData data;

    public StdChart(LineChart lineChart, int legendSize) {
        if (lineChart != null) {
            initLineChart(lineChart);
            initYAxis();
            initFirstXAxis();
            addLegend(legendSize);

            data = new LineData();
        }
    }

    private void initLineChart(LineChart lineChart) {
        this.lineChart = lineChart;
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setDrawBorders(true);
        lineChart.setPinchZoom(false);
        lineChart.setAutoScaleMinMaxEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setBorderColor(Color.GREEN);
        lineChart.setExtraOffsets(0, 5f, 0, 5f);
    }

    private void initYAxis() {
        yAxis = lineChart.getAxisLeft();
        yAxis.setTextColor(Color.GREEN);
        yAxis.setLabelCount(10, false);
        yAxis.removeAllLimitLines();
        yAxis.setDrawGridLines(false);

        yAxis.setSpaceBottom(0);
        yAxis.setSpaceTop(0);
        yAxis.setGranularity(1);
        yAxis.setCenterAxisLabels(false);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value) + " Wins";
            }
        });
    }

    private void initFirstXAxis() {
        xAxis = lineChart.getXAxis();
        xAxis.setTextColor(Color.GREEN);
        xAxis.setLabelCount(1, false);
        xAxis.removeAllLimitLines();
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

    private void addLegend(int legendSize) {
        legend = lineChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setEnabled(true);
        legend.setTextColor(Color.GREEN);
        legend.setYOffset(10);
        legend.setWordWrapEnabled(true);
        legend.setMaxSizePercent(0.7f);
        legend.setForm(Legend.LegendForm.CIRCLE);

        LegendEntry[] legendEntries = new LegendEntry[legendSize];
        for (int i = 0; i < legendEntries.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = COLORS[i];
            entry.label = NAMES[i];
            legendEntries[i] = entry;
        }

        legend.setCustom(legendEntries);
    }

    private void addDataToLineChart(int lineQuantity, int[] wins) {
        for (int line = 0; line < lineQuantity; line++) {
            yValues.add(new ArrayList<>());
            for (int game = 0; game < Database.getInstance().getPlayerWinList(line).size(); game++) {
            }
        }
    }

}