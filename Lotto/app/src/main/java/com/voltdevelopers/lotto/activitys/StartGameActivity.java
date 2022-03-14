package com.voltdevelopers.lotto.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.voltdevelopers.lotto.R;

import java.util.ArrayList;

public class StartGameActivity extends AppCompatActivity {

    private static final String TAG = "PatternGameActivity";
    private LineChart myChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_game);

        myChart = (LineChart) findViewById(R.id.stat1);

        myChart.setDragEnabled(true);
        myChart.setScaleEnabled(false);
        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 20f));
        yValues.add(new Entry(3, 90f));

        LineDataSet set_1 = new LineDataSet(yValues, "Data set 1");
        set_1.setFillAlpha(110);
        ArrayList<ILineDataSet> dataSets= new ArrayList<>();
        dataSets.add(set_1);

        LineData data = new LineData(dataSets);
        myChart.setData(data);

    }
}