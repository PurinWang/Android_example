package com.example.lab_gobackactivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Report extends AppCompatActivity
{
    Bundle bunde;
    Intent intent;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mylayout);

        /* 取得Intent中的Bundle物件 */
        intent=this.getIntent();
        bunde = intent.getExtras();


        String sex = bunde.getString("sex");
        double height = bunde.getDouble("height");

        String sexText="";
        if(sex.equals("M")){
            sexText="男性";
        }else{
            sexText="女性";
        }

        String weight=this.getWeight(sex, height);

        TextView tv1=(TextView) findViewById(R.id.text1);
        tv1.setText("你是一位"+sexText+"\n你的身高"+height+
                "公分\n你的標準體重是"+weight+"公斤");

        /* 以findViewById()取得Button物件，並加入onClickListener */
        Button b1 = (Button) findViewById(R.id.back);
        b1.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                /* 回傳result回上一個activity */
                Report.this.setResult(RESULT_OK, intent);

                /* 關閉activity */
                Report.this.finish();
            }
        });
    }

    private String format(double num)
    {
        NumberFormat formatter = new DecimalFormat("0.00");
        String s=formatter.format(num);
        return s;
    }

    private String getWeight(String sex,double height)
    {
        String weight="";
        if(sex.equals("M"))
        {
            weight=format((height-80)*0.7);
        }else
        {
            weight=format((height-70)*0.6);
        }
        return weight;
    }
}

