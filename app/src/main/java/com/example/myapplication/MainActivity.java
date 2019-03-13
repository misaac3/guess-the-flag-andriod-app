package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Switch timerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSwitch = findViewById(R.id.timer_switch);
        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextOnSwitch();
            }
        });
    }

    private void changeTextOnSwitch() {
        timerSwitch = findViewById(R.id.timer_switch);
        if (timerSwitch.isChecked()) {
            timerSwitch.setText(getString(R.string.timer_on));
        } else {
            timerSwitch.setText(getString(R.string.timer_off));
        }
    }


    public void gtcLaunch(View view) {
        Intent intent = new Intent(this, GuessTheCountryActivity.class);
        intent.putExtra("timerSwitchIsChecked", timerSwitch.isChecked());
        startActivity(intent);
    }

    public void ghLaunch(View view) {
        Intent intent = new Intent(this, GuessHintsActivity.class);
        intent.putExtra("timerSwitchIsChecked", timerSwitch.isChecked());
        startActivity(intent);
    }

    public void gtfLaunch(View view) {
        Intent intent = new Intent(this, GuessTheFlagActivity.class);
        intent.putExtra("timerSwitchIsChecked", timerSwitch.isChecked());
        startActivity(intent);
    }

    public void ALLaunch(View view) {
        Intent intent = new Intent(this, AdvLev.class);
        intent.putExtra("timerSwitchIsChecked", timerSwitch.isChecked());
        startActivity(intent);
    }
}
