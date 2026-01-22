package com.example.intentfilter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.intentfilter.R;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_share);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (stringExtra != null) {
            Log.i(ShareActivity.class.getSimpleName(), stringExtra);
        } else {
            Log.w(ShareActivity.class.getSimpleName(), "String extra not found!");

        }
    }
}