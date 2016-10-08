package com.fmr.speedytextview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUtil = (Button) findViewById(R.id.btn_utils);
        Button btnWidget = (Button) findViewById(R.id.btn_widget);

        btnUtil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                MainActivity.this.openActivity(SpdUtilActivity.class);
            }
        });

        btnWidget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                MainActivity.this.openActivity(SpdActivity.class);
            }
        });
    }

    private void openActivity(Class<?> paramClass) {
        startActivity(new Intent(this, paramClass));
    }
}
