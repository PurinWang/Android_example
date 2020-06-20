package com.example.lab_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAdd = (Button)findViewById(R.id.add);
        btnAdd.setOnClickListener(add_op);
        Button btnSub = (Button)findViewById(R.id.sub);
        btnSub.setOnClickListener(sub_op);
        Button btnMul = (Button)findViewById(R.id.mul);
        btnMul.setOnClickListener(mul_op);
        Button btnDiv = (Button)findViewById(R.id.div);
        btnDiv.setOnClickListener(div_op);

    }
    private OnClickListener add_op = new OnClickListener() {
        public void onClick(View v) {
            EditText fieldNum1 = (EditText)findViewById(R.id.num1);
            EditText fieldNum2 = (EditText)findViewById(R.id.num2);
            int num1 = Integer.parseInt(fieldNum1.getText().toString());
            int num2 = Integer.parseInt(fieldNum2.getText().toString());
            int sum = num1 + num2;

            TextView fieldOp = (TextView)findViewById(R.id.operator);
            fieldOp.setText("+");
            TextView fieldResult = (TextView)findViewById(R.id.result);
            fieldResult.setText("" + sum);
        }
    };

    private OnClickListener sub_op = new OnClickListener() {
        public void onClick(View v) {
            EditText fieldNum1 = (EditText)findViewById(R.id.num1);
            EditText fieldNum2 = (EditText)findViewById(R.id.num2);
            int num1 = Integer.parseInt(fieldNum1.getText().toString());
            int num2 = Integer.parseInt(fieldNum2.getText().toString());
            int sum = num1 - num2;

            TextView fieldOp = (TextView)findViewById(R.id.operator);
            fieldOp.setText("-");
            TextView fieldResult = (TextView)findViewById(R.id.result);
            fieldResult.setText("" + sum);
        }
    };
    private OnClickListener mul_op = new OnClickListener() {
        public void onClick(View v) {
            EditText fieldNum1 = (EditText)findViewById(R.id.num1);
            EditText fieldNum2 = (EditText)findViewById(R.id.num2);
            int num1 = Integer.parseInt(fieldNum1.getText().toString());
            int num2 = Integer.parseInt(fieldNum2.getText().toString());
            int sum = num1 * num2;

            TextView fieldOp = (TextView)findViewById(R.id.operator);
            fieldOp.setText("*");
            TextView fieldResult = (TextView)findViewById(R.id.result);
            fieldResult.setText("" + sum);
        }
    };
    private OnClickListener div_op = new OnClickListener() {
        public void onClick(View v) {
            EditText fieldNum1 = (EditText)findViewById(R.id.num1);
            EditText fieldNum2 = (EditText)findViewById(R.id.num2);
            int num1 = Integer.parseInt(fieldNum1.getText().toString());
            int num2 = Integer.parseInt(fieldNum2.getText().toString());
            int sum = num1 / num2;

            TextView fieldOp = (TextView)findViewById(R.id.operator);
            fieldOp.setText("/");
            TextView fieldResult = (TextView)findViewById(R.id.result);
            fieldResult.setText("" + sum);
        }
    };

}