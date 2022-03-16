package com.voltdevelopers.lotto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.voltdevelopers.lotto.R;
import com.voltdevelopers.lotto.data.Database;

import java.util.ArrayList;

public class StartGameActivity extends AppCompatActivity {

    private static final String TAG = "PatternGameActivity";
    private LineChart myChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Database.getInstance(5, 18d).setOnGraphData(onGraphData);

        myChart = (LineChart) findViewById(R.id.graph);

        myChart.setDragEnabled(true);
        myChart.setScaleEnabled(false);
        /*ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 20f));
        yValues.add(new Entry(3, 90f));

        LineDataSet set_1 = new LineDataSet(yValues, "Player 1 winnings");
        set_1.setFillAlpha(110);
        ArrayList<ILineDataSet> dataSets= new ArrayList<>();
        dataSets.add(set_1);

        LineData data = new LineData(dataSets);
        myChart.setData(data);*/
    }

    private void addDataToGraph(int gameCounter, int[] results){

        ArrayList<ArrayList<Entry>> yValues = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {

            yValues.get(i).add(new Entry(gameCounter, results[i]));
            LineDataSet lineDataSet = new LineDataSet(yValues.get(i), "Player " + i + "winnings");
            lineDataSet.setFillAlpha(110);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);

            LineData data = new LineData(dataSets);
            myChart.setData(data);
            myChart.invalidate();

        }


    }

    Database.OnGraphData onGraphData = (gameCounter, results) -> addDataToGraph(gameCounter, results);

}