package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    public TextView TextView1;
    public RadioGroup RadioGroup1;
    public RadioButton Radio1,Radio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 定義變數
        TextView1 = (TextView) findViewById(R.id.textView);
        RadioGroup1 = (RadioGroup) findViewById(R.id.radiogroup);
        Radio1 = (RadioButton) findViewById(R.id.man);
        Radio2 = (RadioButton) findViewById(R.id.woman);

        // 監聽
        RadioGroup1.setOnCheckedChangeListener(ChangeRadio);
    }

    private RadioGroup.OnCheckedChangeListener ChangeRadio = new
            RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {
                    // TODO Auto-generated method stub
                    if(checkedId==Radio1.getId())
                    {
                        TextView1.setText(Radio1.getText());
                    }
                    else if(checkedId==Radio2.getId())
                    {
                        TextView1.setText(Radio2.getText());
                    }
                }
            };

}