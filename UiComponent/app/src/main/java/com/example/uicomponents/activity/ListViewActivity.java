package com.example.uicomponents.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uicomponents.R;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view);
        ListView listView=findViewById(R.id.listView);
        String[] languages={"Java","Kotlin","Sql","Python","JavaScript"};
     ArrayAdapter<String>arrayAdapter =new ArrayAdapter<>(ListViewActivity.this, android.R.layout.simple_list_item_1,languages);
     listView.setAdapter(arrayAdapter);

     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            String lang =languages[position];
             Toast.makeText(ListViewActivity.this,lang
             ,Toast.LENGTH_SHORT).show();
         }
     });
    }


}