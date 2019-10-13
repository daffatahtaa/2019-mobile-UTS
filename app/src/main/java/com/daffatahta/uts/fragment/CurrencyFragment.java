package com.daffatahta.uts.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.daffatahta.uts.Conversion;
import com.daffatahta.uts.Hitung;
import com.daffatahta.uts.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrencyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CurrencyFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Spinner fromSpinner, toSpinner;
    private EditText fromEdittext, toEditText;
    private Button convert;
    private Double rs;

    public CurrencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_currency, container, false);

        View view = inflater.inflate(R.layout.fragment_currency, container, false);
        fromSpinner = view.findViewById(R.id.spinnerfrom);
        toSpinner = view.findViewById(R.id.spinnerto);
        fromEdittext = view.findViewById(R.id.editTextfrom);
        toEditText = view.findViewById(R.id.editTextTo);
        convert = view.findViewById(R.id.convertBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.currency,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        final EditText fin = view.findViewById(R.id.editTextfrom);

        convert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (mListener != null){
                    String fromValue = fin.getText().toString();
                    String fromString = (String) fromSpinner.getSelectedItem();
                    String toString = (String) toSpinner.getSelectedItem();
                    if (fromValue.equals("")){
                        Toast.makeText(getContext(), "Masukkan inputan!", Toast.LENGTH_SHORT).show();
                    }else{
                        double input = Double.valueOf(fromEdittext.getText().toString());
                        Hitung.Currency fromCurrency = Hitung.fromString(fromString);
                        Hitung.Currency toCurrency = Hitung.fromString(toString);

                        Hitung  hitung = new Hitung(fromCurrency, toCurrency);
                        double rs = hitung.converted(input);
                        toEditText.setText(String.valueOf(rs));
                    }
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
