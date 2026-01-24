package com.example.activity.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;
import com.example.activity.activity.log.LogWritter;

public class HomeActivity extends AppCompatActivity {

    // 1. CONSTANTS & VARIABLES (Define variables at the top)
    private Button homeBtn;
    private Button btn3;
    private TextView txtCounter;

    private int count = 0; // Variable to store our counter value
    private boolean isRunning = false; // Flag to control whether the thread is running

    private MediaPlayer mediaPlayer;

    // Broadcast Receiver Definition for Battery Monitoring
    private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            LogWritter.writeInfoLog(context, "Battery Level: " + level + "%");
        }
    };

    public HomeActivity() {
        super(R.layout.activity_home);
    }

    // 2. ON CREATE (Main Setup happens here)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // A. View Initialization
        homeBtn = findViewById(R.id.homeBtn);
        // Ensure the ID matches the one in activity_home.xml
        txtCounter = findViewById(R.id.textView);
        btn3 = findViewById(R.id.button3);

        // B. Helper Initialization
        initMusicPlayer();

        // C. Restore Saved State (Handle Screen Rotation)
        if (savedInstanceState != null) {
            // Retrieve the previously saved value
            count = savedInstanceState.getInt("MY_COUNT_KEY");

            // Update UI with saved value before the thread starts
            if (txtCounter != null) {
                txtCounter.setText(String.valueOf(count));
            }
        }

        // D. Listeners (Best Practice: Define listeners inside onCreate)
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintLogStatement();
                // Play music if it is not already playing
                playMusic();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // NEVER CALL onPause() MANUALLY!
                // Lifecycle methods are managed by the system.
                // To test the lifecycle, we close the app using finish().
                finish();
            }
        });
    }

    // 3. LIFECYCLE METHODS (Ordered sequentially)

    @Override
    protected void onStart() {
        super.onStart();
        LogWritter.writeInfoLog(this, "onStart() -01");
        // Start monitoring resources (e.g., Battery)
        startBatteryMonitor();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogWritter.writeInfoLog(this, "onResume() - Counter Started");

        // Best place to start Threads, Animations, or Media
        if (!isRunning) {
            isRunning = true;
            startCounterThread();
        }

        // Music playback logic can also be placed here if needed
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogWritter.writeInfoLog(this, " onPause()  -03");

        // Stop things when the app goes to the background to save battery
        isRunning = false;
        pauseMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogWritter.writeInfoLog(this, "onStop() -04");

        // [IMPORTANT] Stop the monitor here to conserve resources
        stopBatteryMonitor();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogWritter.writeInfoLog(this, "onRestart() -05");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogWritter.writeInfoLog(this, "onDestroy() -06");
        // Release memory and clean up resources
        releaseMusicPlayer();
    }

    // 4. STATE SAVING METHODS
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current count value into the bundle
        outState.putInt("MY_COUNT_KEY", count);
        LogWritter.writeInfoLog(this, "Data Saved on Rotate!");
    }

    // 5. HELPER METHODS (Keep private logic at the bottom)

    private void startBatteryMonitor() {
        try {
            IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            registerReceiver(batteryReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopBatteryMonitor() {
        try {
            unregisterReceiver(batteryReceiver);
        } catch (Exception e) {
            // Safe to ignore if already unregistered
        }
    }

    private void startCounterThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Keep running as long as isRunning is true
                while (isRunning) {
                    try {
                        Thread.sleep(1000); // Wait for 1 second
                        count++;

                        // Update UI on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (txtCounter != null) {
                                    txtCounter.setText(String.valueOf(count));
                                    Log.i("COUNTER_LOG", "Current Count: " + count);
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initMusicPlayer() {
        // Initialize the media player with the default ringtone
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
            mediaPlayer.setLooping(true); // Loop the audio
        }
    }

    private void playMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void releaseMusicPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Release system resources
            mediaPlayer = null;
        }
    }

    public void PrintLogStatement() {
        LogWritter.writeInfoLog(this, "My Name Is Chamod");
    }
}