package com.example.service_log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TestService extends AppCompatActivity {

    private Button btn01,btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = (Button)findViewById(R.id.button);
        btn02 = (Button)findViewById(R.id.button2);

        btn01.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                /* 建構Intent物件，指定開啟對象為mService1服務 */
                Intent i = new Intent(TestService.this,mService1.class);

                /* 設定新TASK的方式 */
                i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );

                /* 以startService方法啟動Intent */
                startService(i);
            }
        });

        btn02.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* 建構Intent物件，指定欲關閉的對象為mService1服務 */
                Intent i = new Intent( TestService.this, mService1.class );

                /* 以stopService方法關閉Intent */
                stopService(i);
            }
        });
    }
}