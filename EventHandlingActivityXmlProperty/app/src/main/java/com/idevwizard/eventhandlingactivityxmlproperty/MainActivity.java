package com.idevwizard.eventhandlingactivityxmlproperty;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
    }

    public void changeToGreen(View view) {
        relativeLayout.setBackgroundColor(Color.GREEN);
    }

    public void changeToBlue(View view) {
        relativeLayout.setBackgroundColor(Color.BLUE);
    }
}
