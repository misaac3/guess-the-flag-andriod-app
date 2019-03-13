package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class AdvLev extends AppCompatActivity {

    private static final String TAG = AdvLev.class.getSimpleName();
    private static String countryDisplayed1 = "";
    private static String countryDisplayedAbbr1 = "";
    private static String countryDisplayed2 = "";
    private static String countryDisplayedAbbr2 = "";
    private static String countryDisplayed3 = "";
    private static String countryDisplayedAbbr3 = "";
    private static int guessesRemaining;
    private static int score;

    private static boolean flag1Correct;
    private static boolean flag2Correct;
    private static boolean flag3Correct;

    private CountDownTimer timer;
    private long timeLeftOnTimer = 10000;
    private boolean timerActive;
    private TextView timerTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_lev);

        Log.d(TAG, "--------------------------------------");


        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            timerActive = bundle.getBoolean("timerSwitchIsChecked");
        }

        if (savedInstanceState != null) {
            int sc = savedInstanceState.getInt("score");
            score = sc;

            countryDisplayed1 = savedInstanceState.getString("countryDisplayed1");
            countryDisplayedAbbr1 = savedInstanceState.getString("countryDisplayedAbbr1");
            countryDisplayed2 = savedInstanceState.getString("countryDisplayed2");

            countryDisplayedAbbr2 = savedInstanceState.getString("countryDisplayedAbbr2");

            countryDisplayed3 = savedInstanceState.getString("countryDisplayed3");
            countryDisplayedAbbr3 = savedInstanceState.getString("countryDisplayedAbbr3");
            guessesRemaining = savedInstanceState.getInt("guessesRemaining");
            score = savedInstanceState.getInt("score");

            flag1Correct = savedInstanceState.getBoolean("flag1Correct");

            flag2Correct = savedInstanceState.getBoolean("flag2Correct");

            flag3Correct = savedInstanceState.getBoolean("flag3Correct");

            timeLeftOnTimer = savedInstanceState.getLong("timeLeftOnTimer");


        } else {


            flag1Correct = false;
            flag2Correct = false;
            flag3Correct = false;


            guessesRemaining = 3;

            CountriesInfo ci = new CountriesInfo();
            String[] countries = ci.getCountries();
            HashMap<String, String> countriesMap = ci.getCountriesMap();

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
//
//        Log.d(TAG, String.valueOf(randIdx1));
//        Log.d(TAG, String.valueOf(randIdx2));
//        Log.d(TAG, String.valueOf(randIdx3));

            String randCountry1 = countries[randIdx1];
            String randCountry2 = countries[randIdx2];
            String randCountry3 = countries[randIdx3];
            Log.d(TAG, countriesMap.get(randCountry1));
            Log.d(TAG, countriesMap.get(randCountry2));
            Log.d(TAG, countriesMap.get(randCountry3));


            countryDisplayed1 = countriesMap.get(randCountry1);
            countryDisplayedAbbr1 = randCountry1;

            countryDisplayed2 = countriesMap.get(randCountry2);
            countryDisplayedAbbr2 = randCountry2;

            countryDisplayed3 = countriesMap.get(randCountry3);
            countryDisplayedAbbr3 = randCountry3;

        }


        TextView scoreTV = findViewById(R.id.score_al);
        scoreTV.setText(String.valueOf(score));


        TextView guessesRemainingTV = findViewById(R.id.guesses_remaining_al);
        guessesRemainingTV.setText(String.valueOf(guessesRemaining));

        ImageView flag1 = findViewById(R.id.img1al);
        ImageView flag2 = findViewById(R.id.img2al);
        ImageView flag3 = findViewById(R.id.img3al);

        Resources res = this.getResources();
        int resID1 = res.getIdentifier("small" + countryDisplayedAbbr1.toLowerCase(), "drawable", this.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flag1.setImageDrawable(getResources().getDrawable(resID1, getApplicationContext().getTheme()));
        }
        flag1.setImageResource(resID1);

        int resID2 = res.getIdentifier("small" + countryDisplayedAbbr2.toLowerCase(), "drawable", this.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flag2.setImageDrawable(getResources().getDrawable(resID2, getApplicationContext().getTheme()));
        }
        flag2.setImageResource(resID2);

        int resID3 = res.getIdentifier("small" + countryDisplayedAbbr3.toLowerCase(), "drawable", this.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flag3.setImageDrawable(getResources().getDrawable(resID3, getApplicationContext().getTheme()));
        }
        flag3.setImageResource(resID3);

        EditText et1 = findViewById(R.id.et1);
        EditText et2 = findViewById(R.id.et2);
        EditText et3 = findViewById(R.id.et3);

        if (flag1Correct) {
            et1.setTextColor(Color.GREEN);
            et1.setInputType(0); // uneditable
        }
        if (flag2Correct) {
            et2.setTextColor(Color.GREEN);
            et2.setInputType(0); // uneditable
        }
        if (flag3Correct) {
            et3.setTextColor(Color.GREEN);
            et3.setInputType(0); // uneditable
        }

        if (guessesRemaining <= 0) {
            et1.setInputType(0); // uneditable
            et2.setInputType(0); // uneditable
            et3.setInputType(0); // uneditable

            Button submitBtn = findViewById(R.id.submit_btn_al);
            Button nextBtn = findViewById(R.id.next_btn_al);

            submitBtn.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);

            if (!flag1Correct) {
                TextView tv = findViewById(R.id.correct_country1_al);
                tv.setText(countryDisplayed1);
            }
            if (!flag2Correct) {
                TextView tv = findViewById(R.id.correct_country2_al);
                tv.setText(countryDisplayed2);
            }
            if (!flag3Correct) {
                TextView tv = findViewById(R.id.correct_country3_al);
                tv.setText(countryDisplayed3);
            }
            if (!(flag1Correct && flag2Correct && flag3Correct)) {
                TextView tv = findViewById(R.id.submit_message_al);
                tv.setVisibility(View.VISIBLE);
            }
        }
        if (flag1Correct && flag2Correct && flag3Correct) {
            Button submitBtn = findViewById(R.id.submit_btn_al);
            Button nextBtn = findViewById(R.id.next_btn_al);

            submitBtn.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);
        }

        if (timerActive) {

            findViewById(R.id.timer_section_al).setVisibility(View.VISIBLE);
            timerTV = findViewById(R.id.time_remaining_al);
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
                    onSubmit(null);
                    if(guessesRemaining > 0){
                        timeLeftOnTimer = 10000;
                        timer.start();
                    }
//                                        playerLost();
                }
            }.start();
        }
    }


    public void onNext(View view) {
        Intent intent = new Intent(this, AdvLev.class);
        intent.putExtra("timerSwitchIsChecked", timerActive);
        startActivity(intent);
    }

    public void onSubmit(View view) {

        EditText et1 = findViewById(R.id.et1);
        String submittedText1 = et1.getText().toString();
//        Log.d(TAG, "submittedText1: " + submittedText1);

        EditText et2 = findViewById(R.id.et2);
        String submittedText2 = et2.getText().toString();
//        Log.d(TAG, "submittedText2: " + submittedText2);

        EditText et3 = findViewById(R.id.et3);
        String submittedText3 = et3.getText().toString();
//        Log.d(TAG, "submittedText3: " + submittedText3);

        if (!flag1Correct) {
            flag1Correct = submittedText1.toLowerCase().equals(countryDisplayed1.toLowerCase());
            if (flag1Correct) {
                Log.d(TAG, "Country 1 correct");
                score++;
            }
        }

        if (!flag2Correct) {
            flag2Correct = submittedText2.toLowerCase().equals(countryDisplayed2.toLowerCase());
            if (flag2Correct) {
                Log.d(TAG, "Country 2 correct");
                score++;
            }
        }

        if (!flag3Correct) {
            flag3Correct = submittedText3.toLowerCase().equals(countryDisplayed3.toLowerCase());
            if (flag3Correct) {
                Log.d(TAG, "Country 3 correct");
                score++;
            }

        }
        if (flag1Correct) {
//            Log.d(TAG, "Changing color of Country 1 ");
            et1.setTextColor(Color.GREEN);
            et1.setInputType(0); // uneditable
        }
        if (flag2Correct) {
//            Log.d(TAG, "Changing color of Country 2 ");
            et2.setTextColor(Color.GREEN);
            et2.setInputType(0); // uneditable
        }
        if (flag3Correct) {
//            Log.d(TAG, "Changing color of Country 3 ");
            et3.setTextColor(Color.GREEN);
            et3.setInputType(0); // uneditable
        }


        guessesRemaining--;
        TextView guessesRemainingTV = findViewById(R.id.guesses_remaining_al);
        guessesRemainingTV.setText(String.valueOf(guessesRemaining));

        TextView scoreTV = findViewById(R.id.score_al);
        scoreTV.setText(String.valueOf(score));

        boolean allFlagsCorrect = (flag1Correct && flag2Correct && flag3Correct);

        //Out of Guesses
        if (guessesRemaining <= 0) {


            playerLost();

        }
        // All flags are correctly guessed
        else if (allFlagsCorrect) {
            Button submitBtn = findViewById(R.id.submit_btn_al);
            Button nextBtn = findViewById(R.id.next_btn_al);

            submitBtn.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);
        }
    }

    private void playerLost() {
        EditText et1 = findViewById(R.id.et1);
        EditText et2 = findViewById(R.id.et2);
        EditText et3 = findViewById(R.id.et3);

        et1.setInputType(0); // uneditable
        et2.setInputType(0); // uneditable
        et3.setInputType(0); // uneditable

        Button submitBtn = findViewById(R.id.submit_btn_al);
        Button nextBtn = findViewById(R.id.next_btn_al);

        submitBtn.setVisibility(View.GONE);
        nextBtn.setVisibility(View.VISIBLE);

        if (!flag1Correct) {
            TextView tv = findViewById(R.id.correct_country1_al);
            tv.setText(countryDisplayed1);
        }
        if (!flag2Correct) {
            TextView tv = findViewById(R.id.correct_country2_al);
            tv.setText(countryDisplayed2);
        }
        if (!flag3Correct) {
            TextView tv = findViewById(R.id.correct_country3_al);
            tv.setText(countryDisplayed3);
        }

        boolean allFlagsCorrect = (flag1Correct && flag2Correct && flag3Correct);
        if (!allFlagsCorrect) {
            TextView tv = findViewById(R.id.submit_message_al);
            tv.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("countryDisplayed1", countryDisplayed1);
        outState.putString("countryDisplayedAbbr1", countryDisplayedAbbr1);

        outState.putString("countryDisplayed2", countryDisplayed2);
        outState.putString("countryDisplayedAbbr2", countryDisplayedAbbr2);

        outState.putString("countryDisplayed3", countryDisplayed3);
        outState.putString("countryDisplayedAbbr3", countryDisplayedAbbr3);

        outState.putInt("guessesRemaining", guessesRemaining);
        outState.putInt("score", score);

        outState.putBoolean("flag1Correct", flag1Correct);
        outState.putBoolean("flag2Correct", flag2Correct);
        outState.putBoolean("flag3Correct", flag3Correct);

        outState.putLong("timeLeftOnTimer", timeLeftOnTimer);


    }


}
