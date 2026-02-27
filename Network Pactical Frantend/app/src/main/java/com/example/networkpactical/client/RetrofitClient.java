package com.example.networkpactical.client;

import android.content.Context;
import com.example.networkpactical.AuthInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    // HTTPS වෙනුවට HTTP දාන්න. පෝට් එක 8080 ලෙස දාන්න.
// "https" වෙනුවට "http" දාන්න. අගට පෝට් එක (8080) අනිවාර්යයි.
    public static final String BASE_URL = "http://192.168.8.105:8080/";
    public static Retrofit getInstance(Context context) {
        if (retrofit == null) {
            // Interceptor එක සහිත OkHttpClient එක සාදා ගැනීම
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(context))
                    .build();

            // එම client එක Retrofit එකට ලබා දීම
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client) // <-- මෙය අනිවාර්යයෙන්ම තිබිය යුතුයි
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}