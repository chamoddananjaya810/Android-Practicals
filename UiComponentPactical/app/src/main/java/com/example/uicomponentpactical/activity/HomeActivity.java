package com.example.uicomponentpactical.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uicomponentpactical.R;
import com.example.uicomponentpactical.adapter.UserAdapter;
import com.example.uicomponentpactical.entity.User;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    public static List<User>userList=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        userList.add(new User("chamod","Gampaha"));
        userList.add(new User("Ishara","Ampara"));
        userList.add(new User("chamthka0","Kandy"));
        userList.add(new User("Damith","Colombo"));


    RecyclerView recyclerView =findViewById(R.id.recycleView);

    LinearLayoutManager linearLayoutManager =new  LinearLayoutManager(HomeActivity.this);
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    recyclerView.setLayoutManager(linearLayoutManager);


  UserAdapter userAdapter =new UserAdapter(userList);
  recyclerView.setAdapter(userAdapter);
    }
}