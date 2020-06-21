package com.example.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmi.R;

public class MainActivity extends AppCompatActivity {
    private Button calcbutton;
    private EditText fieldheight;
    private EditText fieldweight;
    private TextView view_result;
    private TextView view_suggest;

    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_QUIT = Menu.FIRST+1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }

    private void findViews(){
        calcbutton = (Button)findViewById(R.id.submit);
        fieldheight = (EditText)findViewById(R.id.height);
        fieldweight = (EditText)findViewById(R.id.weight);
        view_result = (TextView)findViewById(R.id.result);
        view_suggest = (TextView)findViewById(R.id.suggest);
    }
    //Listen for button clicks
    private void setListeners(){
        calcbutton.setOnClickListener(calcBMI);
    }


    private Button.OnClickListener calcBMI = new Button.OnClickListener() {
        public void onClick(View v) {

            Intent intent = new Intent();  //  建立意圖物件
            intent.setClass(MainActivity.this, report.class);  // 設定下一個要執行的Activity
            startActivity(intent); // 啟動執行下一個Activity


        }
    };

}