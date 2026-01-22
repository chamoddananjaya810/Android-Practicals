package activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intent.R;

public class HomeActivity extends AppCompatActivity {
    private Button homeBtn,browserBtn,dialBtn,sharebtn;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(HomeActivity.class.getSimpleName(),"onNewIntent");
        setIntent(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.homeBtn = findViewById(R.id.homeBtn);
        this.browserBtn=findViewById(R.id.browserBtn);
        this.dialBtn=findViewById(R.id.dialBtn);
        this.sharebtn=findViewById(R.id.shareBtn);

    }

    @Override
    protected void onResume() {
        super.onResume();
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoSetting();
            }
        });

        browserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage();
            }
        });

        dialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialNumber();
            }
        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
shareAText();
            }
        });
    }

    private void shareAText(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello Anjana");
        startActivity(Intent.createChooser(intent, "Share via"));
    }
  private void dialNumber(){
      Intent intent = new Intent(Intent.ACTION_CALL);
      intent.setData(Uri.parse("tel:0785964517"));
      startActivity(intent);
  }
     private void openWebPage(){
      Intent intent =new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse("https://www.google.com")); // Uri ==> Android.net.Uro
      startActivity(intent);
     }

    private void movetoSetting(){

       Intent intent =new Intent(HomeActivity.this,SettingActivity.class);
       intent.putExtra("username","Chamod");
       startActivity(intent);
    }

}
