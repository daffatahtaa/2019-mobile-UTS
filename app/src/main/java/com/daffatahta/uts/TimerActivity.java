package com.daffatahta.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {
    private EditText editText;
    private Button setButton;
    private Button startPause;
    private Button reset;
    private TextView viewCountDown;

    private Boolean timeRunning;

    private long minuteStartInMilliS;
    private long minuteTimeLeftInMilliS;
    private long minuteEndInMillis;

    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        setButton = findViewById(R.id.buttonSetTimer);
        startPause = findViewById(R.id.buttonStart);
        reset = findViewById(R.id.buttonReset);
        editText = findViewById(R.id.timerInput);
        viewCountDown = findViewById(R.id.timeLeftTextView);

        setButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                if (input.length() == 0){
                    Toast.makeText(TimerActivity.this, "Mohon di isi!", Toast.LENGTH_SHORT).show();
                    return;
                }
                long miliSec = Long.parseLong(input) * 60000;
                if (miliSec == 0 ){
                    Toast.makeText(TimerActivity.this, "Masukkan Angka positif", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(miliSec);
                editText.setText("");
            }
        });

        startPause.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(timeRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });
    }

    private void setTime (long miliSec){
        minuteStartInMilliS = miliSec;
        resetTimer();
        closeKeyboard();
    }

    private void startTimer(){
        minuteEndInMillis = System.currentTimeMillis() + minuteTimeLeftInMilliS;
        countDownTimer = new CountDownTimer(minuteTimeLeftInMilliS, 1000) {
            @Override
            public void onTick(long millisUntilFinish) {
                minuteTimeLeftInMilliS = millisUntilFinish;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeRunning = false;
                updateWatchInterface();
            }
        }.start();

        timeRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer(){
        countDownTimer.cancel();
        timeRunning = false;
        updateWatchInterface();
    }
    private void resetTimer(){
        minuteTimeLeftInMilliS = minuteStartInMilliS;
        updateCountDownText();
        updateWatchInterface();
    }
    private void updateCountDownText(){
        int hours = (int) (minuteTimeLeftInMilliS/1000) / 3600;
        int minutes = (int) ((minuteTimeLeftInMilliS / 1000) % 3600) / 60;
        int seconds = (int) (minuteTimeLeftInMilliS /1000) % 60;

        String timeLeftFormatted;
        if (hours > 0){
            timeLeftFormatted  = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, seconds);
        }else{
            timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        }
        viewCountDown.setText(timeLeftFormatted);
    }

    private void updateWatchInterface(){
        if (timeRunning){
            editText.setVisibility(View.INVISIBLE);
            setButton.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);
            startPause.setText("Pause");
        }else{
            editText.setVisibility(View.VISIBLE);
            setButton.setVisibility(View.VISIBLE);
            startPause.setText("Start");

            if (minuteTimeLeftInMilliS < 1000){
                startPause.setVisibility(View.INVISIBLE);
            }else{
                startPause.setVisibility(View.VISIBLE);
            }if (minuteTimeLeftInMilliS < minuteStartInMilliS){
                reset.setVisibility(View.VISIBLE);
            }else{
                reset.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService((INPUT_METHOD_SERVICE));
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("StartTimeInMillisecs",minuteStartInMilliS);
        editor.putLong("MillisecondsLeft", minuteTimeLeftInMilliS);
        editor.putBoolean("TImeRunning", timeRunning);
        editor.putLong("EndTime", minuteTimeLeftInMilliS);

        editor.apply();

        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        minuteStartInMilliS = prefs.getLong("StartTimeInMillisecs", minuteTimeLeftInMilliS);
        minuteTimeLeftInMilliS = prefs.getLong("MillisecondsLeft", minuteTimeLeftInMilliS);
        timeRunning = prefs.getBoolean("timerRunnging" , false);

        updateCountDownText();
        updateWatchInterface();

        if (timeRunning) {
            minuteEndInMillis = prefs.getLong("endTine",0);
            minuteTimeLeftInMilliS = minuteEndInMillis - System.currentTimeMillis();

            if (timeRunning) {
                minuteTimeLeftInMilliS = 0;
                timeRunning = false;
                updateCountDownText();
                updateWatchInterface();
            }else{
                startTimer();
            }
        }
    }
}