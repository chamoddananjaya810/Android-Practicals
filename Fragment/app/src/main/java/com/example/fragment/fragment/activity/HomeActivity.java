package com.example.fragment.fragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fragment.R;
import com.example.fragment.fragment.fragment.SettingFragment;

import java.util.Set;

public class HomeActivity extends AppCompatActivity {
private Button settingBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
this.settingBtn=findViewById(R.id.settingBtn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, SettingFragment.class,null)
                        .commit();
            }
        });
    }
}