package com.daffatahta.utsmobile2019.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daffatahta.utsmobile2019.Fragment.FragmentCurrencyConverter;
import com.daffatahta.utsmobile2019.Fragment.FragmentTemperature;
import com.daffatahta.utsmobile2019.R;

public class Converter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
    }

    public void handleCurrencyConverter(View view) {
        final Button btn;
        btn = findViewById(R.id.buttonCurrency);
        btn.setVisibility(View.INVISIBLE);

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.currencyConversionPlaceholder, new FragmentCurrencyConverter());
        ft.commit();

    }

    public void handleTemperatureConversion(View view) {
        //Intent i = new Intent(this, FragmentTemperature.class);
        //startActivity(i);
        final Button btn;
        btn = findViewById(R.id.buttonTemperature);
        //hide button
        btn.setVisibility(View.INVISIBLE);
        //call fragment
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentTemperaturePlaceHolder, new FragmentTemperature());
        ft.commit();

    }
}
