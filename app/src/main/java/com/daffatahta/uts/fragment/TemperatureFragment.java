package com.daffatahta.uts.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daffatahta.uts.R;

public class TemperatureFragment extends Fragment {
    Button b1;
    EditText et;
    RadioButton cf;
    RadioButton fc;
    TextView rs;
    Double a;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TemperatureFragment() {
        // Required empty public constructor
    }

    public static TemperatureFragment newInstance(String param1, String param2) {
        TemperatureFragment fragment = new TemperatureFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);
        et = view.findViewById(R.id.editText);
        b1 = view.findViewById(R.id.convertButton);
        cf = view.findViewById(R.id.farenheitButton);
        fc = view.findViewById(R.id.celciusButton);
        rs = view.findViewById(R.id.temperatureResult);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (et.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Masukkan suhu!", Toast.LENGTH_SHORT).show();
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
        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
