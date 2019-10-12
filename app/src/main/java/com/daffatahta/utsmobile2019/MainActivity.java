package com.daffatahta.utsmobile2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daffatahta.utsmobile2019.activites.Converter;
import com.daffatahta.utsmobile2019.activites.TimerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handlerTimer(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    public void handlerConverter(View view) {
        Intent intent = new Intent (this, Converter.class);
        startActivity(intent);
    }
}
