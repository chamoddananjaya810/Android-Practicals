package com.example.uicomponents.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uicomponents.R;
import com.example.uicomponents.adapter.LangAdapter;

public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle_view);


        // findViewById(R.id.recycleView) වෙනුවට findViewById(R.id.main) දාන්න
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Performance Optimization: If you know the item heights won't change,
        // set this to true to avoid unnecessary layout calculations.
        recyclerView.setHasFixedSize(true);

        String[] langData = {"Java", "Python", "Kotlin"};
        LangAdapter langAdapter = new LangAdapter(langData);
        recyclerView.setAdapter(langAdapter);
    }
}