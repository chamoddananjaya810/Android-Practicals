package com.example.datastorage.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datastorage.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ExternalStorageActivity extends AppCompatActivity {
    private Button writeBtn, readBtn, pickerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        this.writeBtn = findViewById(R.id.writeBtn);
        this.readBtn = findViewById(R.id.readBtn);
        this.pickerBtn = findViewById(R.id.pickerBtn);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                writeFile();
                writeFileIndownloads();
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFileFromDownloads();
            }
        });

        pickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilePicker();
            }
        });
    }


    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 101);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    if (uri == null) throw  new RuntimeException("Something went wrong");
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);

                    }
                    bufferedReader.close();
                    Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private static final String FILE_NAME = "data.txt";

    private void writeFile() {
        try {
            File dir = getExternalFilesDir(null);
            File file = new File(dir, FILE_NAME);

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String text = "My name is Chamod";
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void readFile() {
        // 1. String wenuwata File use karanna
        File dir = getExternalFilesDir(null);
        File file = new File(dir, FILE_NAME);

        try {
            // 2. FileInputStream eka kelinma BufferedReader ekata danna puluwan
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            StringBuilder stringBuilder = new StringBuilder(); // StringBuffer ekata wada StringBuilder fast
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n"); // Aluth peliyak ekathu karanna
            }

            bufferedReader.close();

            // Output eka Toast ekak widiyata
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File eka hoyaganna na!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "File eka kiyawanna bari una!", Toast.LENGTH_SHORT).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void writeFileIndownloads() {
        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Downloads.DISPLAY_NAME, FILE_NAME);
            values.put(MediaStore.Downloads.MIME_TYPE, "text/plain");

            Uri uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
            OutputStream outputStream = getContentResolver().openOutputStream(uri);
            String text = "This is sample text";
            outputStream.write(text.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFileFromDownloads() {

    }
}