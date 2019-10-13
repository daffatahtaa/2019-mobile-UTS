package com.daffatahta.utsmobile2019.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daffatahta.utsmobile2019.R;

public class TemperatureConverterActivity extends AppCompatActivity {
    Button b1;
    EditText et;
    RadioButton cf;
    RadioButton fc;
    TextView rs;
    Double a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        et = findViewById(R.id.editText);
        b1 = findViewById(R.id.convertButton);
        cf = findViewById(R.id.farenheitButton);
        fc = findViewById(R.id.celciusButton);
        rs = findViewById(R.id.temperatureResult);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (et.getText().toString().isEmpty()){
                    Toast.makeText(TemperatureConverterActivity.this,"Masukkan suhu!", Toast.LENGTH_SHORT).show();
                }else if(cf.isChecked()){
                    a = Double.parseDouble(String.valueOf(et.getText()));
                    Double b = a * 9 / 5 + 32;
                    String r = String.valueOf(a);
                    rs.setText(r + " F");
                }else if (fc.isChecked()){
                    a = Double.parseDouble((String.valueOf(et.getText())));
                    Double b = a -32;
                    Double c = b * 5/9;
                    String r = String.valueOf(c);
                    rs.setText(r + " °C");
                }
            }
        });
    }

    public void backHandler(View view) {
        Intent i = new Intent(this, Converter.class);
        startActivity(i);
    }
}
