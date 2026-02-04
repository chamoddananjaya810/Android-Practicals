package com.example.recyclerviewpac.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewpac.Entity.Recipe;
import com.example.recyclerviewpac.R;
import com.example.recyclerviewpac.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ImageView productImg;
    Uri selectedImage;
    RecyclerView menurecyclerView;
    EditText menu_name, menu_pirce;
    Button addMenuBtn;
    private static final int PICK_IMAGE = 100;
    List<Recipe>list = new ArrayList<>();
    MenuAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.productImg = findViewById(R.id.productImgView);
        this.menurecyclerView = findViewById(R.id.menuRecycleView);
        this.menu_name = findViewById(R.id.menuName);
        this.menu_pirce = findViewById(R.id.menuPrice);
        this.addMenuBtn = findViewById(R.id.menuBtn);

        adapter = new MenuAdapter(list);

        menurecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menurecyclerView.setAdapter(adapter);

   addMenuBtn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           if(menu_name.getText().toString().isEmpty() ||   productImg==null
                   || menu_pirce.getText().toString().isEmpty()
           ){
               Toast.makeText(HomeActivity.this,"Fill all Fields",Toast.LENGTH_SHORT).show();
               return ;

           }

           list.add(new Recipe(menu_name.getText().toString()
                   ,menu_pirce.getText().toString(),selectedImage));

           adapter.notifyItemInserted(list.size()-1);


           menu_name.setText("");
           menu_pirce.setText("");
           productImg.setImageResource(R.drawable.default_icon);
           productImg=null;
       }
   });


        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            Log.i("PICK_IMAGE", String.valueOf(data.getData()));
            productImg.setImageURI(selectedImage);


        }

    }
}