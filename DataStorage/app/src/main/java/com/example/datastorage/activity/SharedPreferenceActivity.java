package com.example.datastorage.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datastorage.R;

public class SharedPreferenceActivity extends AppCompatActivity {


    private static final String APP_DATA = "my_app_data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        EditText emailInput = findViewById(R.id.emailAddressTxt);
        EditText passwordInput = findViewById(R.id.passwordTxt);
        Button submitBtn = findViewById(R.id.loginBtn);




        checkIsLoggedIn();
        // Move these inside the onClick listener!
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current text when the button is pressed
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (email.equals("chamod@gmail.com") && password.equals("1234")) {
                    Toast.makeText(SharedPreferenceActivity.this,
                            "Login Successfull",
                            Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences = getSharedPreferences(APP_DATA, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLogIn", true);
                    editor.apply();
                } else {
                    Toast.makeText(SharedPreferenceActivity.this,
                            "Please check your Details!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkIsLoggedIn(){
       SharedPreferences sharedPreferences = getSharedPreferences(APP_DATA,MODE_PRIVATE);
   boolean isLogged= sharedPreferences.getBoolean("isLogIn",false);
   if (isLogged){
       Toast.makeText(this,
               "Alredy Logged",
               Toast.LENGTH_SHORT).show();

   }else{
       Toast.makeText(this,
               "Please fill login data",
               Toast.LENGTH_SHORT).show();
   }

    }
}