package com.example.intentfilter.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.intentfilter.R;

public class HomeActivity extends AppCompatActivity {

    private Button profileBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.profileBtn = findViewById(R.id.OpenProfile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });
    }

    private void openProfile() {
        Intent intent = new Intent("com.example.intentfilter.OPEN_PROFILE");
        intent.setAction("com.example.intentfilter.OPEN_PROFILE");
        startActivity(intent);
//  finish();
    }
}