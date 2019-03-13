package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;


public class GuessTheCountryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = GuessTheCountryActivity.class.getSimpleName();

    private String countrySelected = "";
    private String countryDisplayed = "";
    private String randCountryAbbr = "";

    private boolean isSumbitted = false;

    private CountDownTimer timer;
    private long timeLeftOnTimer = 10000;
    private boolean timerActive;
    private TextView timerTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_country);

        CountriesInfo ci = new CountriesInfo();
        String[] countries = ci.getCountries();
        HashMap<String, String> countriesMap = ci.getCountriesMap();

        Log.d(TAG, "--------------------------------");

        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            timerActive = bundle.getBoolean("timerSwitchIsChecked");
        }

        if (savedInstanceState != null) {
            countryDisplayed = savedInstanceState.getString("countryDisplayed");
            countrySelected = savedInstanceState.getString("countrySelected");
            randCountryAbbr = savedInstanceState.getString("randCountryAbbr");

            isSumbitted = savedInstanceState.getBoolean("isSumbitted");


            Spinner spinner = findViewById(R.id.spinner_gtc);

            for (int i = 0; i < spinner.getCount(); i++) {
                if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(countrySelected)) {
                    spinner.setSelection(i);
                    break;
                }
            }

            timeLeftOnTimer = savedInstanceState.getLong("timeLeftOnTimer");

            if (isSumbitted) {
                onSubmit(null);

            }

        } else {
            Random rng = new Random();
            int randIdx = -1;
            randIdx = rng.nextInt(countries.length);

            randCountryAbbr = countries[randIdx];
            countryDisplayed = countriesMap.get(randCountryAbbr);
            Log.d(TAG, randCountryAbbr);
            Log.d(TAG, countryDisplayed);

        }

        ImageView img = findViewById(R.id.imageView_gtc);
        Resources res = this.getResources();
        int resID = res.getIdentifier(randCountryAbbr.toLowerCase(), "drawable", this.getPackageName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img.setImageDrawable(getResources().getDrawable(resID, getApplicationContext().getTheme()));
        }

        img.setImageResource(resID);


        Spinner spinner = findViewById(R.id.spinner_gtc);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.countries_array,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (timerActive) {
            findViewById(R.id.timer_section_gtc).setVisibility(View.VISIBLE);
            timerTV = findViewById(R.id.time_remaining_gtc);
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
                }
            }.start();

            if (isSumbitted) {
                timer.cancel();
                int secondsRemaining = ((int) timeLeftOnTimer / 1000);
                if (secondsRemaining <= 0) {
                    timerTV.setText(String.valueOf(0));
                } else {
                    timerTV.setText(String.valueOf(secondsRemaining + 1));
                }
            }
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        countrySelected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onSubmit(View view) {

        if (isSumbitted) {
            Log.d(TAG, "");
        } else {
            isSumbitted = true;
        }
        if (timer != null) {
            timer.cancel();
        }

        TextView submitMessageTextView = findViewById(R.id.submit_message);
        TextView countryDisplayedeTextView = findViewById(R.id.countryDisplayed);

        Log.d(TAG, "Country Selected: " + countrySelected);

        if (countrySelected.toLowerCase().equals(countryDisplayed.toLowerCase())) {
            submitMessageTextView.setText(R.string.correct);
            submitMessageTextView.setVisibility(View.VISIBLE);
        } else {
            submitMessageTextView.setText(R.string.wrong);
            submitMessageTextView.setTextColor(Color.RED);
            submitMessageTextView.setVisibility(View.VISIBLE);
        }
        countryDisplayedeTextView.setText(countryDisplayed);
        countryDisplayedeTextView.setVisibility(View.VISIBLE);

        Button submitBtn = findViewById(R.id.submit_btn_gtc);
        Button nextBtn = findViewById(R.id.next_btn_gtc);

        submitBtn.setVisibility(View.GONE);
        nextBtn.setVisibility(View.VISIBLE);
    }

    public void onNext(View view) {
        Intent intent = new Intent(this, GuessTheCountryActivity.class);
        intent.putExtra("timerSwitchIsChecked", timerActive);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("countryDisplayed", countryDisplayed);
        outState.putString("countrySelected", countrySelected);
        outState.putString("randCountryAbbr", randCountryAbbr);
        outState.putBoolean("isSumbitted", isSumbitted);
        outState.putLong("timeLeftOnTimer", timeLeftOnTimer);
    }
}
