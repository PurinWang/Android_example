package com.example.lab_checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.CompoundButton;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	
	private TextView TextView1;
 	private CheckBox CheckBox1;
 	private CheckBox CheckBox2;
 	private CheckBox CheckBox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView1 = (TextView) findViewById(R.id.result);
        TextView1.setText("請選擇項目: ");
      
        CheckBox1=(CheckBox)findViewById(R.id.air_ticket);
        CheckBox2=(CheckBox)findViewById(R.id.iphone);
        CheckBox3=(CheckBox)findViewById(R.id.android_book);

        CheckBox1.setOnCheckedChangeListener(CheckBoxChanged);
        CheckBox2.setOnCheckedChangeListener(CheckBoxChanged);
        CheckBox3.setOnCheckedChangeListener(CheckBoxChanged);
    }

    private CheckBox.OnCheckedChangeListener CheckBoxChanged
            = new CheckBox.OnCheckedChangeListener()
    {
        public void onCheckedChanged( CompoundButton buttonView,
                                      boolean isChecked)
        {
            String str0="你選的項目為: ";
            String str1=getString(R.string.air_ticket);
            String str2=getString(R.string.iphone);
            String str3=getString(R.string.android_book);
            String plus=",";
            String result="剛剛好!!";
            String result2="超過預算了!!";


            if(CheckBox1.isChecked()==true & CheckBox2.isChecked()==true
                    & CheckBox3.isChecked()==true)
            {
                TextView1.setText(str0+str1+plus+str2+plus+str3+result2);
            }
            else if(CheckBox1.isChecked()==false & CheckBox2.isChecked()==true
                    & CheckBox3.isChecked()==true)
            {
                TextView1.setText(str0+str2+plus+str3+result2);
            }
            else if(CheckBox1.isChecked()==true & CheckBox2.isChecked()==false
                    & CheckBox3.isChecked()==true)
            {
                TextView1.setText(str0+str1+plus+str3+result);
            }
            else if(CheckBox1.isChecked()==true & CheckBox2.isChecked()==true
                    & CheckBox3.isChecked()==false)
            {
                TextView1.setText(str0+str1+plus+str2+result);
            }
            else if(CheckBox1.isChecked()==false & CheckBox2.isChecked()==false
                    & CheckBox3.isChecked()==true)
            {
                TextView1.setText(str0+str3+plus+result2);
            }
            else if(CheckBox1.isChecked()==false & CheckBox2.isChecked()==true
                    & CheckBox3.isChecked()==false)
            {
                TextView1.setText(str0+str2);
            }
            else if(CheckBox1.isChecked()==true & CheckBox2.isChecked()==false
                    & CheckBox3.isChecked()==false)
            {
                TextView1.setText(str0+str1);
            }
            else if(CheckBox1.isChecked()==false & CheckBox2.isChecked()==false
                    & CheckBox3.isChecked()==false)
            {
                TextView1.setText(str0);
            }
        }
    };
}