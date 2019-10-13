package com.daffatahta.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void gotoAbout(View view) {
        Intent i = new Intent(this, About.class);
        startActivity(i);

    }

    public void gotoConversion(View view) {
        Intent i = new Intent(this, Conversion.class);
        startActivity(i);

    }

    public void gotoTimer(View view) {
        Intent i = new Intent(this, TimerActivity.class);
        startActivity(i);

    }
}
