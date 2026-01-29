package com.example.uicomponents.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uicomponents.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        AutoCompleteTextView autoCompleteTextView=findViewById(R.id.autoCompleteTextView);

        String[] cities={"Anuradapura","Gampha","Colombo","Badulla","Bandarawela","Mathale","Mathara","polonnaruwa","polgahawela"};

       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,cities);

autoCompleteTextView.setAdapter(arrayAdapter);

    }
}