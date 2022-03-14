package com.voltdevelopers.lotto;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.voltdevelopers.lotto.activitys.StartGameActivity;
import com.voltdevelopers.lotto.activitys.RulesActivity;

public class MainActivity extends AppCompatActivity {

    Button start, rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findRes();

        start.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, StartGameActivity.class);
            startActivity(intent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Log.i("INFO", "Started Activity" + intent.getIdentifier());
            }
        });

        rules.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RulesActivity.class);
            startActivity(intent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Log.i("INFO", "Started Activity" + intent.getIdentifier());
            }
        });

//        Game game = new Game(10);
//        game.gameLoop();
    }

    private void findRes() {
        start = findViewById(R.id.buttonStart);
        rules = findViewById(R.id.buttonRules);
    }
}