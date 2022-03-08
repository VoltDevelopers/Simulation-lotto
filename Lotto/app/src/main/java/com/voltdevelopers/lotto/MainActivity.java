package com.voltdevelopers.lotto;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.voltdevelopers.lotto.activitys.PatternGameActivity;
import com.voltdevelopers.lotto.activitys.StatActivity;

public class MainActivity extends AppCompatActivity {

    Button standardGame, patternGame, stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findRes();

//        standardGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });

        patternGame.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PatternGameActivity.class);
            startActivity(intent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Log.i("INFO", "Started Activity" + intent.getIdentifier());
            }
        });

        stat.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, StatActivity.class);
            startActivity(intent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Log.i("INFO", "Started Activity" + intent.getIdentifier());
            }
        });

    }

    private void findRes() {
        standardGame = findViewById(R.id.btn1);
        patternGame = findViewById(R.id.btn2);
        stat = findViewById(R.id.btn3);
    }
}