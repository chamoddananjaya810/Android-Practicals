package com.example.activity.activity.log;

import android.content.Context;
import android.util.Log;

public class LogWritter {

    public static void writeInfoLog(Context context,String message){
        Log.i(context.getClass().getSimpleName(),message);
    }
}
