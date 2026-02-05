package com.example.uicomponents.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uicomponents.R;
import com.example.uicomponents.listener.OnCityItemClickLister;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {


    private final String[] data;

    private  final  OnCityItemClickLister onCityItemClickLister;

    public CityAdapter(String[] values, OnCityItemClickLister onCityItemClickLister){
          this.data=values;
          this.onCityItemClickLister=onCityItemClickLister;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ad_item_row,parent
                ,false);


        return new ViewHolder(view);
    }

    @Override
    @SuppressLint("RecyclerView")

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
String value = data[position];
 holder.textView.setText(value);
 holder.button.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
      onCityItemClickLister.onCityItemClick(value);
     }
 });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

   public static class ViewHolder extends RecyclerView.ViewHolder {
       private final TextView textView;

       private final Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView=itemView.findViewById(R.id.cityName);
            this.button=itemView.findViewById(R.id.subBtn);
        }
    }
    public  interface  OnItemClickListener{
        void onItemClick();
    }
}
