package com.idevwizard.eventhandlingactivity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(MainActivity.this);
        button2.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "Button Clicked");

        switch (v.getId()) {

            case R.id.button1:
                Log.i(TAG, "Color change to GREEN");
                relativeLayout.setBackgroundColor(Color.GREEN);
                break;

            case R.id.button2:
                Log.i(TAG, "Color change to BLUE");
                relativeLayout.setBackgroundColor(Color.BLUE);
                break;
        }
    }
}
