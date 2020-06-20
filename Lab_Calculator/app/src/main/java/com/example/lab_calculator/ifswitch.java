package com.example.lab_calculator;

import android.os.Bundle;
import android.widget.Button;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ifswitch extends AppCompatActivity {
    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button)findViewById(R.id.add);
        btnAdd.setOnClickListener(lsnOp);
        btnSub = (Button)findViewById(R.id.sub);
        btnSub.setOnClickListener(lsnOp);
        btnMul = (Button)findViewById(R.id.mul);
        btnMul.setOnClickListener(lsnOp);
        btnDiv = (Button)findViewById(R.id.div);
        btnDiv.setOnClickListener(lsnOp);
    }

    private OnClickListener lsnOp = new OnClickListener() {
        public void onClick(View v) {
            EditText fieldNum1 = (EditText)findViewById(R.id.num1);
            EditText fieldNum2 = (EditText)findViewById(R.id.num2);
            int num1 = Integer.parseInt(fieldNum1.getText().toString());
            int num2 = Integer.parseInt(fieldNum2.getText().toString());
            TextView fieldOp = (TextView)findViewById(R.id.operator);
            int sum=0;
            if((Button)v == btnAdd){
                sum = num1 + num2;
                fieldOp.setText("+");
            }else if((Button)v == btnSub){
                sum = num1 - num2;
                fieldOp.setText("-");
            }else if((Button)v == btnMul){
                sum = num1 * num2;
                fieldOp.setText("*");
            }else if((Button)v == btnDiv){
                sum = num1 / num2;
                fieldOp.setText("/");
            }

            TextView fieldResult = (TextView)findViewById(R.id.result);
            fieldResult.setText("" + sum);
        }
    };

}