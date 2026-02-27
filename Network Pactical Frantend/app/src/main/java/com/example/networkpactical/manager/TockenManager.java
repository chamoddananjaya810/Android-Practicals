package com.example.networkpactical.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.networkpactical.dto.TokenDTO;
// මෙතැන ඔබේ TokenDTO පවතින පැකේජය import කරන්න
// import com.example.networkpactical.model.TokenDTO;

public class TockenManager {
    private static final String PREF_NAME = "NetworkPracticalPrefs";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";

    // 1. saveTokens (Context c, TokenDTO dto)
    // ලොගින් වූ පසු Access සහ Refresh ටෝකන් දෙකම එකවර සේව් කිරීමට
    public static void saveTokens(Context c, TokenDTO dto) {
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, dto.getAccessToken()); // DTO එකේ ඇති නම බලන්න
        editor.putString(KEY_REFRESH_TOKEN, dto.getRefeshToken());
        editor.apply();
    }

    // 2. retrieveAccessToken (Context c)
    // Interceptor එක සඳහා Access Token එක ලබා ගැනීමට
    public static String retrieveAccessToken(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }

    // 3. retrieveRefreshToken (Context c)
    // ටෝකන් එක expire වූ විට අලුත් එකක් ලබා ගැනීමට (Refresh)
    public static String retrieveRefreshToken(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_REFRESH_TOKEN, null);
    }

    // 4. clearTokens (Context c)
    // Logout වීමේදී සියලුම ටෝකන් දත්ත මකා දැමීමට
    public static void clearTokens(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }
}