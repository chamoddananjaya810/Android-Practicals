package com.example.listeners.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listeners.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button homeBtn = findViewById(R.id.homeBtn);

//    homeBtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//        }
//    });


        Button onclickBtn = findViewById(R.id.onclick);
        onclickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "onClick Event", Toast.LENGTH_LONG).show();
            }
        });

//setOnClickListener

        Button longPressBtn = findViewById(R.id.longBtn);

        longPressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "OnClick Event -  long press Btn", Toast.LENGTH_LONG).show();
            }
        });

        //onLongClickListners
        longPressBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Toast.makeText(MainActivity.this, "OnLongClick Event-long press Btn", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        EditText editText = findViewById(R.id.editText);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                view.setBackgroundColor(Color.GRAY);
            }
        });

        EditText editText2 = findViewById(R.id.editText2);

        editText2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    Toast.makeText(MainActivity.this, "OnkeyListeners", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });


        View view=findViewById(R.id.view);
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
//
//                    Toast.makeText(MainActivity.this, "Touched", Toast.LENGTH_LONG).show();
//                }
//                return true;
//            }
//        });
//
//
//registerForContextMenu(view);

        view.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                Toast.makeText(MainActivity.this, "Dropped", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Options");

        menu.add(0,v.getId(),0,"edit");
        menu.add(0,v.getId(),0,"delete");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

            Toast.makeText(this, "Valume up", Toast.LENGTH_SHORT).show();

        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {

            Toast.makeText(this, "Valume down", Toast.LENGTH_SHORT).show();

        }


        return super.onKeyDown(keyCode, event);
    }
}