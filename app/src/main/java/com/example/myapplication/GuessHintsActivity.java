package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GuessHintsActivity extends AppCompatActivity {

    private static final String TAG = GuessHintsActivity.class.getSimpleName();
    private static int incorrectGuessesRemaining;
    private static char[] lettersGuess = new char[30];
    private static String initialDashes = "";
    private static String countryDisplayed = "";
    private String randCountryAbr = "";
    private String displayString = "";
    private static boolean[] showCharArr;

    private CountDownTimer timer;
    private long timeLeftOnTimer = 10000;
    //    private long timeLeftOnTimer = 1000000;
    private boolean timerActive;
    private TextView timerTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_hints);

        CountriesInfo ci = new CountriesInfo();
        String[] countries = ci.getCountries();
        HashMap<String, String> countriesMap = ci.getCountriesMap();

        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            timerActive = bundle.getBoolean("timerSwitchIsChecked");
        }
        boolean isComplete = false;
        if (savedInstanceState != null) {
            incorrectGuessesRemaining = savedInstanceState.getInt("incorrectGuessesRemaining");
            timeLeftOnTimer = savedInstanceState.getLong("timeLeftOnTimer");

            if (incorrectGuessesRemaining <= 0 || timeLeftOnTimer <= 0) playerLoses();


            initialDashes = savedInstanceState.getString("initialDashes");
            displayString = savedInstanceState.getString("displayString");

            countryDisplayed = savedInstanceState.getString("countryDisplayed");
            randCountryAbr = savedInstanceState.getString("randCountryAbr");

            showCharArr = savedInstanceState.getBooleanArray("showCharArr");

            isComplete = true;
            for (boolean b : showCharArr) {
                if (!b) {
                    isComplete = false;
                }
            }
            if (isComplete) {
                playerWins();
                if (timer != null) {
                    timer.cancel();
                }
            }

            TextView dashes_textView = findViewById(R.id.country_name_dashes_gh);
            dashes_textView.setText(displayString);


        } else {
            incorrectGuessesRemaining = 3;

            Random rng = new Random();
            int randIdx = rng.nextInt(countries.length);
            randCountryAbr = countries[randIdx];
            countryDisplayed = countriesMap.get(randCountryAbr);

            showCharArr = new boolean[countryDisplayed.length()];
            for (int i = 0; i < showCharArr.length; i++) {
                showCharArr[i] = false;
            }

            initialDashes = "";
            for (int i = 0; i < countryDisplayed.length(); i++) {
                if (countryDisplayed.charAt(i) == " ".charAt(0)) {
//                Log.d(TAG, "Space Found");
                    initialDashes = initialDashes.concat("  ");
                    showCharArr[i] = true;
                } else if (countryDisplayed.charAt(i) == ",".charAt(0)) {
                    initialDashes = initialDashes.concat(", ");
                    showCharArr[i] = true;
                } else {
                    initialDashes = initialDashes.concat("_ ");
                }
            }
            TextView dashes_textView = findViewById(R.id.country_name_dashes_gh);
            dashes_textView.setText(initialDashes);
        }


        TextView tv = findViewById(R.id.guesses_remaining_gh);
        tv.setText("Guesses Remaining: " + incorrectGuessesRemaining);

        Log.d(TAG, countryDisplayed);

        ImageView img = findViewById(R.id.imageView_gh);

        Resources res = this.getResources();
        int resID = res.getIdentifier(randCountryAbr.toLowerCase(), "drawable", this.getPackageName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img.setImageDrawable(getResources().getDrawable(resID, getApplicationContext().getTheme()));
        }

        img.setImageResource(resID);

//        Log.d(TAG, initialDashes);


        if (timerActive) {
            findViewById(R.id.timer_section_gh).setVisibility(View.VISIBLE);
            timerTV = findViewById(R.id.time_remaining_gh);
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
                    playerLoses();
                }
            }.start();

            if (isComplete && timer != null) {
                timer.cancel();
                int secondsRemaining = ((int) timeLeftOnTimer / 1000);
                timerTV.setText(String.valueOf(secondsRemaining + 1));
            }
        }


    }

    public void onSubmit(View view) {


        TextView error_TextView = findViewById(R.id.error_message_gh);
        error_TextView.setVisibility(View.INVISIBLE);

        EditText et = findViewById(R.id.edit_text_gh);
        String submittedText = et.getText().toString();

        boolean isAlpha = true;
        for (int i = 0; i != submittedText.length(); ++i) {
            if (!(Character.isLetter(submittedText.charAt(i)))) {
                if (!(submittedText.charAt(i) == " ".charAt(0))) {
                    if (!(submittedText.charAt(i) == "(".charAt(0) || submittedText.charAt(i) == ")".charAt(0))) {
                        if (!(submittedText.charAt(i) == "-".charAt(0))) {
                            isAlpha = false;
                        }
                    }
                }

            }

        }
        /*---------------------------------------------*/
        if ((submittedText.length() == 1) && isAlpha) { //Valid Inout


            ArrayList<Integer> indicesWithChar = new ArrayList<Integer>(countryDisplayed.length());
            char[] charArr = countryDisplayed.toLowerCase().toCharArray();
            char charSubmitted = submittedText.toLowerCase().charAt(0);

            for (int i = 0; i < charArr.length; i++) {
                if (charSubmitted == charArr[i]) {
                    indicesWithChar.add(i);
                }
            }
//            Log.d(TAG, "Num matches: " + String.valueOf(indicesWithChar.size()));
            if (indicesWithChar.size() > 0) { //Correct guess
                for (int i = 0; i < showCharArr.length; i++) {
                    if (indicesWithChar.contains(i)) {
                        showCharArr[i] = true;
                    }
                }
                displayString = "";
                for (int i = 0; i < countryDisplayed.length(); i++) {
                    if (showCharArr[i]) {
                        displayString = displayString.concat(String.valueOf(countryDisplayed.charAt(i)) + " ");
                    } else {
                        displayString = displayString.concat("_ ");
                    }
                }
                Log.d(TAG, displayString);
                TextView dashes_textView = findViewById(R.id.country_name_dashes_gh);
                dashes_textView.setText(displayString);


//                Log.d(TAG, Arrays.toString(showCharArr));
                boolean isComplete = true;
                for (boolean b : showCharArr) {
                    if (!b) {
                        isComplete = false;
                    }
                }
//                Log.d(TAG, "isComplete: " + isComplete);

                if (isComplete) {
                    playerWins();
                }
            } else { //WRONG GUESS
                incorrectGuessesRemaining--;
                TextView tv = findViewById(R.id.guesses_remaining_gh);
                tv.setText("Guesses Remaining: " + incorrectGuessesRemaining);
                if (incorrectGuessesRemaining < 1) {
                    playerLoses();
                }
            }
        } else { //Invalid Input
            error_TextView.setVisibility(View.VISIBLE);
        }
    }

    public void playerWins() {
        if (timer != null) {
            timer.cancel();
        }

        TextView tv = findViewById(R.id.end_message_gh);
        tv.setTextColor(Color.GREEN);
        tv.setVisibility(View.VISIBLE);


        Button submitBtn = findViewById(R.id.submit_btn_gh);
        submitBtn.setVisibility(View.GONE);

        Button nextBtn = findViewById(R.id.next_btn_gh);
        nextBtn.setVisibility(View.VISIBLE);

        EditText et = findViewById(R.id.edit_text_gh);
        et.setInputType(0); //uneditable

    }

    public void playerLoses() {
        TextView tv = findViewById(R.id.end_message_gh);
        tv.setText(R.string.wrong);
        tv.setTextColor(Color.RED);
        tv.setVisibility(View.VISIBLE);

        TextView countryDisplayTextView = findViewById(R.id.country_name_display_gh);
        countryDisplayTextView.setTextColor(Color.BLUE);
        countryDisplayTextView.setText(countryDisplayed);
        countryDisplayTextView.setVisibility(View.VISIBLE);

        Button submitBtn = findViewById(R.id.submit_btn_gh);
        submitBtn.setVisibility(View.GONE);

        Button nextBtn = findViewById(R.id.next_btn_gh);
        nextBtn.setVisibility(View.VISIBLE);

        EditText et = findViewById(R.id.edit_text_gh);
        et.setBackgroundColor(Color.GRAY);
        et.setInputType(0); //uneditable

    }

    public void onNext(View view) {
        Intent intent = new Intent(this, GuessHintsActivity.class);
        intent.putExtra("timerSwitchIsChecked", timerActive);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        /*
         * incorrectGuessesRemaining
         * countryDisplayed
         * randCountryAbr
         * showCharArr
         * initialDashes
         *
         * */
        outState.putInt("incorrectGuessesRemaining", incorrectGuessesRemaining);

        outState.putString("initialDashes", initialDashes);
        outState.putString("displayString", displayString);

        outState.putString("countryDisplayed", countryDisplayed);
        outState.putString("randCountryAbr", randCountryAbr);

        outState.putBooleanArray("showCharArr", showCharArr);

        outState.putLong("timeLeftOnTimer", timeLeftOnTimer);
    }
}

