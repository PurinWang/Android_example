package com.example.dialup;

import android.content.Intent;
/*必需引用Uri類別才能使用Uri.parse()*/
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
/*必需引用 widget.Button才能宣告使用Button物件*/
import android.widget.Button;
/*必需引用 widget.EditText才能宣告使用EditText物件*/
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*必需引用 java.util.regex才能使用Regular Expression*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Dialup extends AppCompatActivity {

    /*宣告Button與EditText物件名稱*/
    private Button mButton1;
    private EditText mEditText1;

    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        String expression2 = "^\\(?(\\d{2})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
        CharSequence inputStr = phoneNumber;
        /*建立Pattern*/
        Pattern pattern = Pattern.compile(expression);
        /*將Pattern 以參數傳入Matcher作Regular expression*/
        Matcher matcher = pattern.matcher(inputStr); /*建立Pattern2*/
        Pattern pattern2 = Pattern.compile(expression2);
        /*將Pattern2 以參數傳入Matcher2作Regular expression*/
        Matcher matcher2 = pattern2.matcher(inputStr);
        if (matcher.matches() || matcher2.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*>android 6.0(API 23)之後請求權限*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 111);
        }
        /*透過findViewById建構子來建構EditText與Button物件*/
        mEditText1 = (EditText) findViewById(R.id.myEditText1);
        mButton1 = (Button) findViewById(R.id.myButton1);

        mButton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                try { /*取得EditText中使用者輸入的字串*/
                    String strInput = mEditText1.getText().toString();
                    if (isPhoneNumberValid(strInput) == true) { /*建構一個新的Intent並執行action.CALL的常數與透過Uri將字串帶入*/
                        Intent myIntentDial = new Intent("android.intent.action.CALL", Uri.parse("tel:" + strInput)); /*在startActivity()方法中帶入自訂的Intent物件以執行撥打電話的工作*/
                        startActivity(myIntentDial);
                        mEditText1.setText("");
                    } else {
//                        mEditText1.setText("");
                        Toast.makeText(Dialup.this, "輸入的電話格式不符", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}