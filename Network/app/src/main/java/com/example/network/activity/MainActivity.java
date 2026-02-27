package com.example.network.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.network.Client.RetrofitClient;
import com.example.network.R;
import com.example.network.model.User;
import com.example.network.service.UserService;

// These imports are essential for reading the network data
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // --- MISSING VARIABLES DEFINED HERE ---
    private static final String TAG = "MainActivity";
    private static final int SUCCESS_CODE = 200;

    // This creates a pool of threads so you don't freeze the UI
    private static final ExecutorService backgroundExecutor = Executors.newFixedThreadPool(4);
    // ---------------------------------------

    private Button httpUrlBtn,requestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.httpUrlBtn = findViewById(R.id.httpUrlButton);
        this.requestBtn = findViewById(R.id.requestBtn);

        httpUrlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestUserData();
            }
        });
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
getAllUserData();
            }
        });
    }


//Retrofit
private void getAllUserData(){
  try (ExecutorService service=Executors.newSingleThreadExecutor()){
     UserService userService = RetrofitClient.getInstance().create(UserService.class);
   Call<List<User>> call= userService.getAllUser();

   call.enqueue(new Callback<List<User>>() {
       @Override
       public void onResponse(Call<List<User>> call, Response<List<User>> response) {

           if (response.isSuccessful()){
              List<User> users = response.body();
              if (users !=null){
                  for (User user:users){
                      Log.d(MainActivity.class.getSimpleName(),"Name  : "+user.getName());
                      Log.d(MainActivity.class.getSimpleName(),"Email : "+user.getEmail());
                  }


              }
           }



       }

       @Override
       public void onFailure(Call<List<User>> call, Throwable t) {
  Log.e(MainActivity.class.getSimpleName(),"Retrofit Failure"+t.getMessage());
       }
   });
  }
}

    //HTTPURLCONNCETION
    private void requestUserData() {
        // Now backgroundExecutor is recognized because it's defined above!
        backgroundExecutor.execute(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/users/1");
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                int responseCode = connection.getResponseCode();

                if (responseCode == SUCCESS_CODE) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream())
                    );
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    Log.d(TAG, "Response: " + sb.toString());

                } else {
                    Log.e(TAG, "Server returned error code: " + responseCode);
                }

            } catch (IOException e) {
                Log.e(TAG, "Network error occurred", e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        });
    }
}