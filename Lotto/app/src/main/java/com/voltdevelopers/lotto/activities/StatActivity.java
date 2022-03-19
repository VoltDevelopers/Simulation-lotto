package com.voltdevelopers.lotto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.voltdevelopers.lotto.R;
import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.layout.Console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatActivity extends AppCompatActivity {

    Database db;
    Button btnGetFile;
    Console console;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        db = Database.getInstance();
        btnGetFile = findViewById(R.id.btnGetFile);
        console = Console.getInstance();
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        TextView text = findViewById(R.id.dbStat);
        text.setText(db.toString());
    }

    View.OnClickListener onGetFile = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                writeToFile(db.toString(), getApplicationContext());
            } catch (IOException e) {
                console.printStr("Exception File write failed: " + e.toString());
            }
        }
    };

    private void writeToFile(String data, Context context) throws IOException {

        File dir = new File(context.getFilesDir(), "lotto");
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File gpxfile = new File(dir, buildFileName());
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(db.toString());
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        /*File path = Environment.getDataDirectory();
        if(!path.exists()){
            path.mkdir();
        }
        String fileName = buildFileName();
        File outputFile = new File(path, fileName);
        outputFile.createNewFile();

        FileOutputStream stream = new FileOutputStream(outputFile);

        try {
            stream.write(db.toString().getBytes());
        }
        catch (IOException e) {
            console.printStr("Exception File write failed: " + e.toString());
        } finally {
            stream.close();
        }*/


        /*File path = Environment.getDataDirectory();
        File outputFile = new File(path, buildFileName());

        FileOutputStream stream = new FileOutputStream(outputFile);

        try {
            stream.write("text-to-write".getBytes());
        }
        catch (IOException e) {
            console.printStr("Exception File write failed: " + e.toString());
        } finally {
            stream.close();
        }*/

        /*try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("lotto_data.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            console.printStr("Exception File write failed: " + e.toString());
        }*/
    }

    private String buildFileName(){
        return("lotto_output" + sdf.format(new Date() + ".txt"));
    }
}