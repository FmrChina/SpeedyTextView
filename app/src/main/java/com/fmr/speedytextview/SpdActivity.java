package com.fmr.speedytextview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import speedytextview.fmr.com.spdtvlibrary.SpdTextView;

public class SpdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spd);
        ((SpdTextView)findViewById(R.id.spd6))
                .setBgColor(ContextCompat.getColor(this, R.color.darkorchid))
                .setBgClickColor(ContextCompat.getColor(this, R.color.brown))
                .setNormalStorkeWidth(5)
                .setNormalStorkeColor(ContextCompat.getColor(this, R.color.burlywood))
                .setPressStorkeWidth(2)
                .setPressStorkeColor(ContextCompat.getColor(this, R.color.antiquewhite))
                .setBgCircleAngle(40)
                .build();

    }
}
