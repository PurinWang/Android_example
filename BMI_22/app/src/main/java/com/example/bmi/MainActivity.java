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

    private String PRFF = "BMI_PREF";
    private String PREF_Height = "BMI_HEIGHT";
    private String PREF_Weight = "BMI_WEIGHT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
        restorePref();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences(PRFF,0);
        settings.edit().putString(PREF_Height,fieldheight.getText().toString()).commit();
        settings.edit().putString(PREF_Weight,fieldweight.getText().toString()).commit();

    }

    private  void restorePref(){
        SharedPreferences setting = getSharedPreferences(PRFF,0);
        String pheight = setting.getString(PREF_Height,"");
        String pweight = setting.getString(PREF_Height,"");
        if (!pheight.equals("")){
            fieldweight.setText(pweight);
            fieldheight.setText(pheight);
        }
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

            Bundle bundle = new Bundle();	// 建立Bundle儲存要給下一個Activity的資料
            bundle.putString("KEY_HEIGHT", fieldheight.getText().toString());
            bundle.putString("KEY_WEIGHT", fieldweight.getText().toString());
            intent.putExtras(bundle);

            startActivity(intent); // 啟動執行下一個Activity


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