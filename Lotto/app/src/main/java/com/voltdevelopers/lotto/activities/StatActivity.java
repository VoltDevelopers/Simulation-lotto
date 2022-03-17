package com.voltdevelopers.lotto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.voltdevelopers.lotto.R;
import com.voltdevelopers.lotto.data.Database;

public class StatActivity extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        db = Database.getInstance();

        TextView text = findViewById(R.id.dbStat);
        text.setText(db.toString());
    }
}