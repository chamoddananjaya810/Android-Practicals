package com.example.listeners.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listeners.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    Button homeBtn= findViewById(R.id.homeBtn);

//    homeBtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//        }
//    });


        Button onclickBtn=findViewById(R.id.onclick);
        onclickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"onClick Event",Toast.LENGTH_LONG).show();
            }
        });



        Button longPressBtn=findViewById(R.id.longBtn);

        longPressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"OnClick Event -  long press Btn",Toast.LENGTH_LONG).show();
            }
        });

        //onLongClickListners


        longPressBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Toast.makeText(MainActivity.this,"OnLongClick Event-long press Btn",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}