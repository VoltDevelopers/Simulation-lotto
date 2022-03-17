package com.voltdevelopers.lotto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.voltdevelopers.lotto.R;
import com.voltdevelopers.lotto.data.Database;

import java.util.ArrayList;
import java.util.Random;

public class StartGameActivity extends AppCompatActivity {

    private static final String TAG = "PatternGameActivity";
    private LineChart myChart;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        myChart = (LineChart) findViewById(R.id.graph);

        myChart.setDragEnabled(true);
        myChart.setScaleEnabled(false);
        myChart.setDrawBorders(true);
        myChart.setPinchZoom(false);
        myChart.setDrawGridBackground(false);
        myChart.getAxisRight().setEnabled(false);
        myChart.setBorderColor(Color.GREEN);

        db = Database.getInstance();
        addDataToGraph();
    }

    private void addDataToGraph(){

        int colors[] = {Color.RED,Color.YELLOW,Color.WHITE,Color.GREEN,Color.BLUE};
        Random random = new Random();

        ArrayList<ArrayList<Entry>> yValues = new ArrayList<>();
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        YAxis yAxis = myChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setLabelCount(5,true);
        yAxis.setTextColor(Color.GREEN);
        yAxis.removeAllLimitLines();
        yAxis.setAxisMaximum(5f);

        XAxis xAxis = myChart.getXAxis();
        xAxis.setTextColor(Color.GREEN);
        xAxis.setLabelCount(db.getSizeSignificantPulls(),true);
        xAxis.removeAllLimitLines();
        xAxis.setAxisMaximum(db.getSizeSignificantPulls());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);

        for(int i = 0; i < 5; i++){ //ciclo per le 5 linee

            yValues.add(new ArrayList<>());

            for(int j = 0; j < db.getSizeSignificantPulls(); j++){ //ciclo per scorrere tutte le vincite del singolo giocatore

                //asse X = j
                int y = 0; // asse y;
                y = db.getPlayerWinList(i).get(j);
                //y = random.nextInt(6);
                yValues.get(i).add(new Entry(j,y));

            }

            lineDataSets.add(new LineDataSet(yValues.get(i), "player " + i + " winnings"));
            lineDataSets.get(i).setFillAlpha(110);
            lineDataSets.get(i).setLineWidth(3f);
            lineDataSets.get(i).setDrawCircles(false);
            //lineDataSets.get(i).setCircleRadius(6);
            //lineDataSets.get(i).setCircleHoleRadius(6);
            lineDataSets.get(i).setValueTextSize(6);
            lineDataSets.get(i).setColor(colors[i]);
           // lineDataSets.get(i).setCircleColor(colors[i]);
            lineDataSets.get(i).setValueTextColor(colors[i]);
            dataSets.add(lineDataSets.get(i));

        }

        //LineData data = new LineData(lineDataSets.get(0),lineDataSets.get(1),lineDataSets.get(2),lineDataSets.get(3),lineDataSets.get(4));
        LineData data = new LineData(dataSets);
        myChart.setData(data);

    }
}