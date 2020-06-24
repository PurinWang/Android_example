package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        findViews();
        showResults();
        setListensers();

    }

    private Button button_back;
    private TextView view_result;
    private TextView view_suggest;
    private void findViews() {
        button_back = (Button) findViewById(R.id.report_back);
        view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
    }
    //Listen for button clicks
    private void setListensers() {
        button_back.setOnClickListener(backMain);
    }
    private Button.OnClickListener backMain = new Button.OnClickListener() {
        public void onClick(View v) {
            // Close this Activity
            report.this.finish();
        }
    };
    private void showResults() {
        DecimalFormat nf = new DecimalFormat("0.00");
        Bundle bundle = this.getIntent().getExtras();	// 取出由上一個Activity所傳入的Bundle物件
        double height = Double.parseDouble(bundle.getString("KEY_HEIGHT"))/100;
        double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));
        double BMI = weight / (height * height);
        view_result.setText(getString(R.string.bmi_result) +nf.format(BMI));
        //Give health advice
        if(BMI>25) {
            showNotification(BMI);
            view_suggest.setText(R.string.advice_heavy);
        } else if(BMI<20) {
            view_suggest.setText(R.string.advice_light);
        } else {
            view_suggest.setText(R.string.advice_average);
        }
    }

    protected void showNotification (double BMI) {
        final int notifyID = 1; // 通知的識別號碼
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); // 取得系統的通知服務
        final Notification notification = new  Notification.Builder(getApplicationContext()).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("內容標題").setContentText("內容文字").build(); // 建立通知
        notificationManager.notify(notifyID, notification); // 發送通知
    }

}
