package com.example.uicomponents.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uicomponents.R;
import com.example.uicomponents.adapter.CityAdapter;
import com.example.uicomponents.listener.OnCityItemClickLister;

public class AdRecycleViewActivity extends AppCompatActivity
        implements OnCityItemClickLister {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_recycle_view);

      RecyclerView recyclerView =  findViewById(R.id.adRecycleView);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      String[] cities={"Ampara","Jappna","Kaluthara","Galle","Polonnaruwa","Veyangoda"};
         CityAdapter cityAdapter =new CityAdapter(cities,this);
         recyclerView.setAdapter(cityAdapter);

    }

    @Override
    public void onCityItemClick(String value) {
        Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}