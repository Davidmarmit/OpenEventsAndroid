package com.openevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Integer slashScreenTimeout = 2000;

        final Handler handler = new Handler(Looper.getMainLooper());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        TextView title_op = findViewById(R.id.textView_login);
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            title_op.setTextColor(getResources().getColor(R.color.white));
        }
        SharedPreferences pref = getApplicationContext().getSharedPreferences("token", 0);
        String token = pref.getString("token", null);
        if (token != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            }, slashScreenTimeout);
        }else{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            }, slashScreenTimeout);
        }
    }
}