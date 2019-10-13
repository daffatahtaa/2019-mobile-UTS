package com.daffatahta.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daffatahta.uts.fragment.CurrencyFragment;
import com.daffatahta.uts.fragment.TemperatureFragment;

public class Conversion extends AppCompatActivity implements CurrencyFragment.OnFragmentInteractionListener {
    private Button currencyConverter;
    private Button temperatureConversion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        currencyConverter = findViewById(R.id.currency);
        currencyConverter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new CurrencyFragment()).commit();
            }
        });

        temperatureConversion = findViewById(R.id.temperature);
        temperatureConversion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new TemperatureFragment()).commit();
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
