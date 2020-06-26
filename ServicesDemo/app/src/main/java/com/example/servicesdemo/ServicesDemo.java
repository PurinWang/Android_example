package com.example.servicesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ServicesDemo extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ServiceDemo";
    Button buttonStart,buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                Log.d(TAG, "onClick: starting service");
                startService(new Intent(this, MyService.class));
                break;
            case R.id.buttonStop:
                Log.d(TAG, "onClick: stopping service");
                stopService(new Intent(this, MyService.class));
                break;
        }
    }
}