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
        setContentView(R.layout.activity_rules);

        db = Database.getInstance(5, 18d);

//        TextView text;
//        text = findViewById(R.id.db);
//        text.setText(db.toString());
    }
}