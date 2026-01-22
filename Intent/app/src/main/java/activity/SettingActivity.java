package activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intent.R;

public class SettingActivity extends AppCompatActivity {

    private Button settingBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.settingBtn = findViewById(R.id.settingBtn);
        //access Data
      String username=  getIntent().getStringExtra("username");
        Log.i(SettingActivity.class.getSimpleName(), "onCreate: "+username);
    }

    @Override
    protected void onResume() {
        super.onResume();
settingBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        movetoHome();

    }
});

    }

    private void movetoHome(){
        Intent intent =new Intent(SettingActivity.this,HomeActivity.class);
        startActivity(intent);

    }
}
