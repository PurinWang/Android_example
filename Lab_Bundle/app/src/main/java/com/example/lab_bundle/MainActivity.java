package com.example.lab_bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {

                EditText et = (EditText) findViewById(R.id.height);
                double height=Double.parseDouble(et.getText().toString());

                String sex="";
                RadioButton rb1 = (RadioButton) findViewById(R.id.sex1);
                if(rb1.isChecked())
                {
                    sex="M";
                }else{
                    sex="F";
                }
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Report.class);

                Bundle bundle = new Bundle();
                bundle.putDouble("height",height);
                bundle.putString("sex",sex);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}
