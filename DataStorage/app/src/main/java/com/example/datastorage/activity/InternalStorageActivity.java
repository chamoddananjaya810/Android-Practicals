package com.example.datastorage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.example.datastorage.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class InternalStorageActivity extends AppCompatActivity {

    private Button storeBtn, retriveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_internal_storage);


        storeBtn = findViewById(R.id.store_Btn);
        retriveBtn = findViewById(R.id.retrive_btn);

        storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeData();
            }
        });


        retriveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        });
    }

    private void storeData() {
        //  getFilesDir(); ///files directory -> data_storage/files
        // getCacheDir(); //cach directry  ->data_storage/cace
        File file = new File(getFilesDir(), "AppData.txt");
        try {
            FileOutputStream fileOutputStream = openFileOutput("AppData.txt", MODE_PRIVATE);
            String text = "My name is Chamod";
            fileOutputStream.write(text.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void readData() {
        //  getFilesDir(); ///files directory -> data_storage/files
        // getCacheDir(); //cach directry  ->data_storage/cace


        try {
            FileInputStream fileInputStream = openFileInput("AppData.txt");


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            fileInputStream.close();
            bufferedReader.close();
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}