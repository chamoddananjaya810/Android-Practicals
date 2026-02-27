package com.example.networkpactical.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.networkpactical.R;
import com.example.networkpactical.api.AuthApi;
import com.example.networkpactical.client.RetrofitClient;
import com.example.networkpactical.dto.LoginRequestDTO;
import com.example.networkpactical.dto.TokenDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText emailInput = findViewById(R.id.emaiInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.lang.String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                login(email, password);

            }
        });


    }

    private void login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please Fill all login Fieds", Toast.LENGTH_SHORT).show();
        } else {
            Retrofit instance = RetrofitClient.getInstance(this);
            AuthApi authApi = instance.create(AuthApi.class);

            LoginRequestDTO dto = new LoginRequestDTO(email, password);
            Call<TokenDTO> tokenDTOCall = authApi.userLogin(dto);
            tokenDTOCall.enqueue(new Callback<TokenDTO>() {
                @Override
                public void onResponse(Call<TokenDTO> call, Response<TokenDTO> response) {
                    if (response.isSuccessful()) {
                        TokenDTO tokenDTO = response.body();
                        if (tokenDTO != null) {

                            Log.d("LoginActivity", "Token : " + tokenDTO.getAccessToken());
                        }else {
                            Log.e("LoginActivity", "Server Error Code: " + response.code());
                        }
                    }
                }

                @Override
                public void onFailure(Call<TokenDTO> call, Throwable t) {
                    Log.e("LoginActivity", "Login Error: " + t.getMessage());
                    t.printStackTrace();
                }
            });
        }
    }
}