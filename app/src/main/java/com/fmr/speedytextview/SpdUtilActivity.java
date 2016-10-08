package com.fmr.speedytextview;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fmr.speedytextview.utils.SelectorUtils;

public class SpdUtilActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private TextView textView1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spd_util);
        init();
        setSelector();
    }

    private void init() {
        editText = ((EditText) findViewById(R.id.edit1));
        textView = ((TextView) findViewById(R.id.tv));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"aaaa",Toast.LENGTH_SHORT).show();
            }
        });

        textView1 = ((TextView) findViewById(R.id.tv1));
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"dddd",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setSelector() {

        GradientDrawable gradientDrawable = SelectorUtils.getInstance(this)
                .getDrawable(20, ContextCompat.getColor(this, R.color.bisque), 2, ContextCompat.getColor(this, R.color.aqua));
        editText.setBackground(gradientDrawable);


        GradientDrawable normalDraw = SelectorUtils.getInstance(this)
                .getDrawable(20, ContextCompat.getColor(this, R.color.aliceblue), 2, ContextCompat.getColor(this, R.color.blueviolet));
        GradientDrawable pressDraw = SelectorUtils.getInstance(this)
               .getDrawable(20, ContextCompat.getColor(this, R.color.brown), 2, ContextCompat.getColor(this, R.color.colorPrimaryDark));

        StateListDrawable stateListDrawable = SelectorUtils.getSelector(normalDraw, pressDraw);
        textView.setBackground(stateListDrawable);


        GradientDrawable normalDraw1 = SelectorUtils.getInstance(this)
                .getDrawable(10,20,0,40, ContextCompat.getColor(this, R.color.chocolate), 2, ContextCompat.getColor(this, R.color.darkcyan));
        GradientDrawable pressDraw1 = SelectorUtils.getInstance(this)
                .getDrawable(10, 10, 0, 0, ContextCompat.getColor(this, R.color.darkgoldenrod), 2, ContextCompat.getColor(this, R.color.darkorchid));

        StateListDrawable stateListDrawable1 = SelectorUtils.getSelector(normalDraw1,pressDraw1);
        textView1.setBackground(stateListDrawable1);
    }


}
