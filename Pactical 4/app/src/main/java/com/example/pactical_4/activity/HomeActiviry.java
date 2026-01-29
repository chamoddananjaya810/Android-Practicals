package com.example.pactical_4.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pactical_4.R;

public class HomeActiviry extends AppCompatActivity {

    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_activiry);
        textView = findViewById(R.id.textView2);

       String email=getIntent().getStringExtra("email");

       if (!email.isEmpty()){

           textView.setText("Welacome"+email);
       }
    }
}