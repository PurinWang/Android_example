package com.example.bmi_8;

import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Listen for button clicks
        Button button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(calcBMI);
    }

    private OnClickListener calcBMI = new OnClickListener() {
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            EditText fieldheight = (EditText) findViewById(R.id.height);
            EditText fieldweight = (EditText) findViewById(R.id.weight);
            double height = Double.parseDouble(fieldheight.getText().toString()) / 100;
            double weight = Double.parseDouble(fieldweight.getText().toString());
            double BMI = weight / (height * height);
            TextView result = (TextView) findViewById(R.id.result);
            result.setText("Your BMI is " + nf.format(BMI));
            TextView fieldsuggest = (TextView) findViewById(R.id.suggest);
            if (BMI > 25) {
                fieldsuggest.setText(R.string.advice_heavy);
            } else if (BMI < 20) {
                fieldsuggest.setText(R.string.advice_light);
            } else {
                fieldsuggest.setText(R.string.advice_average);
            }
        }
    };

    Button button = (Button) findViewById(R.id.button_send);
    button.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
        /* Do something in response to button click */
    } });
}