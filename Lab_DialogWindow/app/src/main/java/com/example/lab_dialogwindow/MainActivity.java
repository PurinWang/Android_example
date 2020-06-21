package com.example.lab_dialogwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    private TextView tvPasswd=null;
    private LayoutInflater dialoglayout;
    @Override
    protected Dialog onCreateDialog(int id) {
        dialoglayout = LayoutInflater.from(this);
        final View textEntryView = dialoglayout.inflate(R.layout.dialog, null);
        return new AlertDialog.Builder(MainActivity.this)
//                .setIcon(R.drawable.dialog_icon)
                .setTitle(R.string.dialog_text)
                .setView(textEntryView)

                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        EditText etPasswd = (EditText)textEntryView.findViewById(R.id.password_edit);
                        tvPasswd.setText(etPasswd.getText());
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPasswd = (TextView) findViewById(R.id.tvPasswd);
        Button textEntry = (Button) findViewById(R.id.dialog_button);
        textEntry.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                showDialog(0);
            }
        });
    }
}