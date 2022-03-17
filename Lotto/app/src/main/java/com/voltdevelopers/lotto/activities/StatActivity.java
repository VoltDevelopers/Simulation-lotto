package com.voltdevelopers.lotto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.voltdevelopers.lotto.R;
import com.voltdevelopers.lotto.data.Database;

public class StatActivity extends AppCompatActivity {


  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        Database db = Database.getInstance();

        TextView text= findViewById(R.id.dbStat);
        text.setText(db.toString());
    }
}