package com.voltdevelopers.lotto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        });

        stat.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, StatActivity.class);
            startActivity(intent);
        });

    }

    private void findRes() {
        standardGame = findViewById(R.id.btn1);
        patternGame = findViewById(R.id.btn2);
        stat = findViewById(R.id.btn3);
    }
}