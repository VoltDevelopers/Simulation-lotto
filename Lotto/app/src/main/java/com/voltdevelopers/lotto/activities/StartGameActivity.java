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
import java.util.List;

public class StartGameActivity extends AppCompatActivity {
    private static final String TAG = "PatternGameActivity";
    private LineChart myChart;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        myChart = (LineChart) findViewById(R.id.graph);
        myChart.setDragEnabled(true);
        myChart.setScaleEnabled(false);

//        List<Entry> yValues = new ArrayList<>();
//        yValues.add(new Entry(0, 60f));
//        yValues.add(new Entry(1, 50f));
//        yValues.add(new Entry(2, 20f));
//        yValues.add(new Entry(3, 90f));
//
//        LineDataSet set_1 = new LineDataSet(yValues, "Player 1 winnings");
//        set_1.setFillAlpha(110);
//        List<ILineDataSet> dataSets= new ArrayList<>();
//        dataSets.add(set_1);
//
//        LineData data = new LineData(dataSets);
//        myChart.setData(data);

        database = Database.getInstance();
        addDataToGraph();
    }

    private void addDataToGraph() {
        List<Entry> yValues = new ArrayList<>();

        for (int i = 0; i < database.getSizeAllPulls(); i++) {
            // ASSE Y
            for (int j = 0; j < 5; j++) {
                int y = 0;
                int[] arr = database.getPlayerLastBet(j);
                for (int k : arr) {
                    y += k;
                }
                yValues.add(new Entry(i, y));
                LineDataSet lineDataSet = new LineDataSet(yValues, "Player " + i + "winnings");
                lineDataSet.setFillAlpha(110);
                List<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet);

                LineData data = new LineData(dataSets);
                myChart.setData(data);
            }
        }
    }
}