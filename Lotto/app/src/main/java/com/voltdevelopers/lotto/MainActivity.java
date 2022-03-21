package com.voltdevelopers.lotto;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.voltdevelopers.lotto.activities.RulesActivity;
import com.voltdevelopers.lotto.activities.StartGameActivity;
import com.voltdevelopers.lotto.layout.Console;


public class MainActivity extends AppCompatActivity {

    Button start, rules;
    TextView textViewHero, textViewStart, textViewRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findRes();
        initRes();

        start.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, StartGameActivity.class);
            startActivity(intent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Console.getInstance().printStr("Started Activity" + intent.getIdentifier());
            }
        });

        rules.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RulesActivity.class);
            startActivity(intent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Console.getInstance().printStr("Started Activity" + intent.getIdentifier());
            }
        });
    }

    private void findRes() {
        start = findViewById(R.id.buttonStart);
        rules = findViewById(R.id.buttonRules);
        textViewHero = findViewById(R.id.textViewHTML);
        textViewStart = findViewById(R.id.textView5);
        textViewRules = findViewById(R.id.textView6);
    }

    private void initRes() {
        textViewHero.setText(Html.fromHtml("<p>Se consideriamo il gioco del lotto da un punto di vista <span style=\"color:#00ff00;\">matematico</span> e <span style=\"color:#00ff00;\">calcoli</span> la probabilità di vincita di tutte le strategie è assolutamente la stessa e noi lo dimostreremo chiaramente.</p>"));
        textViewStart.setText(Html.fromHtml("<p>Avviare la <span style=\"color:#00ff00;\">simulazione...</span></p>"));
        textViewRules.setText(Html.fromHtml("<p>O leggere la <span style=\"color:#00ff00;\">teoria ;)</span></p>"));
    }
}