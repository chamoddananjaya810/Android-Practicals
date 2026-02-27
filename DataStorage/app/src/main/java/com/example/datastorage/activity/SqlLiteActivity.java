package com.example.datastorage.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datastorage.R;
import com.example.datastorage.activity.helper.SQLiteHelper;

public class SqlLiteActivity extends AppCompatActivity {


    private Button insertBtn, updateBtn, deleteBtn, searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_light);
        this.insertBtn = findViewById(R.id.insertBtn);
        this.updateBtn = findViewById(R.id.updateBtn);
        this.searchBtn = findViewById(R.id.searchBtn);
        this.deleteBtn = findViewById(R.id.deleteBtn);


        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchData();
            }
        });

    }


    private void insertData() {
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase writableDatabase = sqLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "Sahan Perera");
        values.put("age", 20);

        writableDatabase.insert("student", null, values);
    }

    private void updateData() {
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase writableDatabase = sqLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("age", 25);
        writableDatabase.update("student", values, "id=?", new String[]{String.valueOf(2)});

    }

    private void deleteData() {
  SQLiteHelper sqLiteHelper=SQLiteHelper.getInstance(this);
    SQLiteDatabase writableDatabase =sqLiteHelper.getWritableDatabase();
  int delete=  writableDatabase.delete("student","id=?",new String[]{String.valueOf(1)});
        Log.d(SqlLiteActivity.class.getSimpleName(),"deleteData"+delete);
    }
    private void searchData(){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase readableDatabase = sqLiteHelper.getReadableDatabase();

        // Using try-with-resources (API 16+) automatically closes the cursor
        try (Cursor cursor = readableDatabase.rawQuery("SELECT * FROM student", null)) {
            if (cursor != null && cursor.moveToFirst()){
                do {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    Log.d("SqlLiteActivity", "Student Name: " + name);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("SqlLiteActivity", "Error searching data", e);
        }
    }

}