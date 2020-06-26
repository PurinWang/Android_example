package com.example.lab_gobackactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private RadioButton rb1;
    private RadioButton rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                et = (EditText) findViewById(R.id.height);
                double height = Double.parseDouble(et.getText().toString());

                String sex = "";
                rb1 = (RadioButton) findViewById(R.id.sex1);
                rb2 = (RadioButton) findViewById(R.id.sex2);
                if (rb1.isChecked()) {
                    sex = "M";
                } else {
                    sex = "F";
                }
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Report.class);

                Bundle bundle = new Bundle();
                bundle.putDouble("height", height);
                bundle.putString("sex", sex);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    /* 覆寫 onActivityResult()*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                /* 取得資料，並顯示於畫面上 */
                Bundle bunde = data.getExtras();
                String sex = bunde.getString("sex");
                double height = bunde.getDouble("height");

                et.setText("" + height);
                if (sex.equals("M")) {
                    rb1.setChecked(true);
                } else {
                    rb2.setChecked(true);
                }
                break;
            default:
                break;
        }
    }
}
