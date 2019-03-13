package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class GuessTheFlagActivity extends AppCompatActivity {
    private static final String TAG = GuessTheFlagActivity.class.getSimpleName();
    private static String countryDisplayed = "";
    private static String countryDisplayedAbbr = "";
    private static int flagNum;

    private String randCountry1;
    private String randCountry2;
    private String randCountry3;

    private boolean guessMade = false;
    private boolean isCorrect;

    private CountDownTimer timer;
    private long timeLeftOnTimer = 10000;
    private boolean timerActive;
    private TextView timerTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_flag);

        CountriesInfo ci = new CountriesInfo();
        String[] countries = ci.getCountries();
        HashMap<String, String> countriesMap = ci.getCountriesMap();

        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            timerActive = bundle.getBoolean("timerSwitchIsChecked");
        }

        if (savedInstanceState != null) {
            randCountry1 = savedInstanceState.getString("randCountry1");
            randCountry2 = savedInstanceState.getString("randCountry2");
            randCountry3 = savedInstanceState.getString("randCountry3");

            countryDisplayed = savedInstanceState.getString("countryDisplayed");
            countryDisplayedAbbr = savedInstanceState.getString("countryDisplayedAbbr");

            isCorrect = savedInstanceState.getBoolean("isCorrect");
            guessMade = savedInstanceState.getBoolean("guessMade");
            if (guessMade) {
                TextView messageTV = findViewById(R.id.message_gtf);
                if (isCorrect) {
                    messageTV.setText(R.string.correct);
                    messageTV.setBackgroundColor(Color.GREEN);
                    messageTV.setVisibility(View.VISIBLE);

                } else {
                    messageTV.setText(R.string.wrong);
                    messageTV.setBackgroundColor(Color.RED);
                    messageTV.setVisibility(View.VISIBLE);
                }
            }

            timeLeftOnTimer = savedInstanceState.getLong("timeLeftOnTimer");

        } else {
            Log.d(TAG, "------------------------------");
            Random rng = new Random();
            int randIdx1 = -1;
            int randIdx2 = -1;
            int randIdx3 = -1;

            randIdx1 = rng.nextInt(countries.length);

            while ((randIdx2 == -1) || (randIdx2 == randIdx1)) {
                randIdx2 = rng.nextInt(countries.length);
            }

            while ((randIdx3 == -1) || (randIdx3 == randIdx1) || (randIdx3 == randIdx2)) {
                randIdx3 = rng.nextInt(countries.length);
            }

            randCountry1 = countries[randIdx1];
            randCountry2 = countries[randIdx2];
            randCountry3 = countries[randIdx3];

            int randFlagNum = rng.nextInt(3) + 1;
            flagNum = randFlagNum;
            switch (randFlagNum) {
                case 1:
                    countryDisplayed = countriesMap.get(randCountry1);
                    countryDisplayedAbbr = randCountry1;
                    break;
                case 2:
                    countryDisplayed = countriesMap.get(randCountry2);
                    countryDisplayedAbbr = randCountry2;
                    break;
                case 3:
                    countryDisplayed = countriesMap.get(randCountry3);
                    countryDisplayedAbbr = randCountry3;
                    break;
            }

            Log.d(TAG, countriesMap.get(randCountry1));
            Log.d(TAG, countriesMap.get(randCountry2));
            Log.d(TAG, countriesMap.get(randCountry3));
        }


        ImageView flag1 = findViewById(R.id.flag1imgGTF);
        ImageView flag2 = findViewById(R.id.flag2imgGTF);
        ImageView flag3 = findViewById(R.id.flag3imgGTF);


        Resources res = this.getResources();

        int resID1 = res.getIdentifier(randCountry1.toLowerCase(), "drawable", this.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flag1.setImageDrawable(getResources().getDrawable(resID1, getApplicationContext().getTheme()));
        }
        flag1.setImageResource(resID1);

        int resID2 = res.getIdentifier(randCountry2.toLowerCase(), "drawable", this.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flag2.setImageDrawable(getResources().getDrawable(resID2, getApplicationContext().getTheme()));
        }
        flag2.setImageResource(resID2);

        int resID3 = res.getIdentifier(randCountry3.toLowerCase(), "drawable", this.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flag3.setImageDrawable(getResources().getDrawable(resID3, getApplicationContext().getTheme()));
        }
        flag3.setImageResource(resID3);

        TextView tv = findViewById(R.id.country_name_gtf);
        tv.setText(countryDisplayed);

        if (timerActive) {
            findViewById(R.id.timer_section_gtf).setVisibility(View.VISIBLE);
            timerTV = findViewById(R.id.time_remaining_gtf);
            timer = new CountDownTimer(timeLeftOnTimer, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftOnTimer = millisUntilFinished;
                    updateTimer();
                }

                private void updateTimer() {
                    int secondsRemaining = ((int) timeLeftOnTimer / 1000);
                    timerTV.setText(String.valueOf(secondsRemaining + 1));
                }

                @Override
                public void onFinish() {
                    timerTV.setText(String.valueOf(0));
//                    playerLost();
                    ImageView flag1 = findViewById(R.id.flag1imgGTF);
                    ImageView flag2 = findViewById(R.id.flag2imgGTF);
                    ImageView flag3 = findViewById(R.id.flag3imgGTF);

                    flag1.setOnClickListener(null);
                    flag2.setOnClickListener(null);
                    flag3.setOnClickListener(null);
                    View gtfmain = findViewById(R.id.gtf_main);
                    gtfmain.setBackgroundColor(Color.GRAY);
                }
            }.start();
        }

    }

    public void flag1Click(View view) {
//        String id = view.getResources().getResourceEntryName(view.getId());
//        Log.d(TAG, id);

        if (flagNum == 1) {
            correctGuess();
        } else {
            incorrectGuess();
        }

    }

    public void flag2Click(View view) {
//        String id = view.getResources().getResourceEntryName(view.getId());
//        Log.d(TAG, id);
        if (flagNum == 2) {
            correctGuess();
        } else {
            incorrectGuess();
        }
    }

    public void flag3Click(View view) {
//        String id = view.getResources().getResourceEntryName(view.getId());
//        Log.d(TAG, id);
        if (flagNum == 3) {
            correctGuess();
        } else {
            incorrectGuess();
        }
    }


    public void correctGuess() {
        TextView messageTV = findViewById(R.id.message_gtf);

        messageTV.setText(R.string.correct);
        messageTV.setBackgroundColor(Color.GREEN);
        messageTV.setVisibility(View.VISIBLE);

        isCorrect = true;
        guessMade = true;
    }


    public void incorrectGuess() {
        TextView messageTV = findViewById(R.id.message_gtf);
        messageTV.setText(R.string.wrong);
        messageTV.setBackgroundColor(Color.RED);
        messageTV.setVisibility(View.VISIBLE);

        isCorrect = false;
        guessMade = true;
    }

    public void onNext(View view) {
        view.setVisibility(View.VISIBLE);
        findViewById(R.id.next_btn_gtf).setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, GuessTheFlagActivity.class);
        intent.putExtra("timerSwitchIsChecked", timerActive);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("randCountry1", randCountry1);
        outState.putString("randCountry2", randCountry2);
        outState.putString("randCountry3", randCountry3);

        outState.putBoolean("isCorrect", isCorrect);
        outState.putBoolean("guessMade", guessMade);

        outState.putString("countryDisplayed", countryDisplayed);
        outState.putString("countryDisplayedAbbr", countryDisplayedAbbr);

        outState.putLong("timeLeftOnTimer", timeLeftOnTimer);


    }

}
