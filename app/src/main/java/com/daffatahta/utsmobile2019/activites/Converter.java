package com.daffatahta.utsmobile2019.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daffatahta.utsmobile2019.R;

public class Converter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
    }

    public void handleCurrencyConverter(View view) {
        Intent i = new Intent(this, CurrencyConverterActivity.class);
        startActivity(i);

    }

    public void handleTemperatureConversion(View view) {
        Intent i = new Intent(this, TemperatureConverterActivity.class);
        startActivity(i);
    }
}
