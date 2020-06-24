package com.example.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmi.R;

public class MainActivity extends AppCompatActivity {
    private Button calcbutton;
    private Spinner field_feet,field_inch;
    private EditText fieldweight;
    private TextView view_result;
    private TextView view_suggest;

    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_QUIT = Menu.FIRST+1;

    private String PRFF = "BMI_PREF";
    private String PREF_Height = "BMI_HEIGHT";
    private String PREF_Weight = "BMI_WEIGHT";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences(PRFF,0);
//        settings.edit().putString(PREF_Height,fieldheight.getText().toString()).commit();
        settings.edit().putString(PREF_Weight,fieldweight.getText().toString()).commit();

    }


    private void findViews(){
        calcbutton = (Button)findViewById(R.id.submit);
        field_feet = (Spinner)findViewById(R.id.feet);
        field_inch = findViewById(R.id.inch);
        fieldweight = (EditText)findViewById(R.id.weight);
        view_result = (TextView)findViewById(R.id.result);
        view_suggest = (TextView)findViewById(R.id.suggest);

        ArrayAdapter <CharSequence> adapter_ft = ArrayAdapter.createFromResource(this,R.array.feets,android.R.layout.simple_spinner_dropdown_item);
        field_feet.setAdapter(adapter_ft);
        ArrayAdapter <CharSequence> adapter_inch = ArrayAdapter.createFromResource(this,R.array.inches,android.R.layout.simple_spinner_item);
        field_inch.setAdapter(adapter_inch);
    }
    //Listen for button clicks
    private void setListeners(){
        calcbutton.setOnClickListener(calcBMI);
        field_feet.setOnItemSelectedListener(getFeet);
        field_inch.setOnItemSelectedListener(getInch);
    }


    private int feet;
    private int inch;

    private Spinner.OnItemSelectedListener getFeet = new Spinner.OnItemSelectedListener() {
        public void onItemSelected(AdapterView parent, View v, int position, long id) {
            feet = parent.getSelectedItemPosition()+2;
        }
        public void onNothingSelected(AdapterView parent) {
        }
    };

    private Spinner.OnItemSelectedListener getInch = new Spinner.OnItemSelectedListener() {
        public void onItemSelected(AdapterView parent, View v, int position, long id) {
            inch = parent.getSelectedItemPosition()+1;
        }
        public void onNothingSelected(AdapterView parent) {

        }
    };

    private Button.OnClickListener calcBMI = new Button.OnClickListener() {
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            try {
                double height = (feet*12+inch)*2.54/100;
                double weight = Double.parseDouble(fieldweight.getText().toString())*0.45359;
                double BMI = weight / (height * height);
                //Present result
                view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));
                //Give health advice
                if(BMI > 27) {
                    view_suggest.setText(R.string.advice_fat);
                } else if(BMI > 25) {
                    view_suggest.setText(R.string.advice_heavy);
                } else if(BMI < 20) {
                    view_suggest.setText(R.string.advice_light);
                } else {
                    view_suggest.setText(R.string.advice_average);
                }
            } catch(Exception obj) {
                Toast.makeText(MainActivity.this, getString(R.string.input_error), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void openOptionsDialog() {
        Toast.makeText(MainActivity.this, "BMI 計算器", Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.about_title)
                .setMessage(R.string.about_msg)
                .setPositiveButton("確認",  new DialogInterface.OnClickListener(){
                    public void onClick(
                            DialogInterface dialoginterface, int i){
                    }
                })
                .setNegativeButton(R.string.homepage_label, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialoginterface, int i){
                        //Home Page
                        Uri uri = Uri.parse("http://tw.yahoo.com/");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(0, MENU_ABOUT, 0, "關於...").setIcon(android.R.drawable.ic_menu_help);
        menu.add(0, MENU_QUIT, 0, "結束").setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()) {
            case MENU_ABOUT:
                openOptionsDialog();
                break;
            case MENU_QUIT:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}