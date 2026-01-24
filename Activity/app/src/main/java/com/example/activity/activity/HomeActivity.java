package com.example.activity.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;
import com.example.activity.activity.log.LogWritter;

public class HomeActivity extends AppCompatActivity {
//private  static final String TAG=HomeActivity.class.getSimpleName();

    private   Button homeBtn;
    public  HomeActivity (){
super(R.layout.activity_home);
    }
    @Override
    protected void onPause() {

        super.onPause();
        LogWritter.writeInfoLog(this," onPause()  -03");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);

         homeBtn=findViewById(R.id.homeBtn);

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogWritter.writeInfoLog(this,"onStart() -01");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogWritter.writeInfoLog(this,"onResume() -02");
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintLogStatement();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogWritter.writeInfoLog(this,"onDestroy() -06");
    }

    @Override
    protected void onStop() {

        super.onStop();
        LogWritter.writeInfoLog(this,"onStop() -04");
    }

    @Override
    protected void onRestart() {

        super.onRestart();
        LogWritter.writeInfoLog(this,"onRestart() -05");
    }


    public void PrintLogStatement(){
       // System.out.println("INFO : MY name is Chamod");
//        Log.i(HomeActivity.this.getClass().getName(), "PrintLogStatement: My Name Is Chamod");

        LogWritter.writeInfoLog(this,"My Name Is Chamod");
    }
}
