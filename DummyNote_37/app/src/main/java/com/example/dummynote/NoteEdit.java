package com.example.dummynote;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteEdit extends AppCompatActivity {
    private DB mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        mDbHelper = new DB(this);
        mDbHelper.open();
        findViews();
        showViews(savedInstanceState);
    }

    private EditText field_note;
    private Button button_confirm;
    private void findViews() {
        field_note = (EditText) findViewById(R.id.note);
        button_confirm = (Button) findViewById(R.id.confirm);
    }
    private Long mRowId;
    private void showViews(Bundle savedInstanceState) {
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(DB.KEY_ROWID) : null;
        }
        populateFields();
        button_confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mDbHelper.update(mRowId, field_note.getText().toString());
                setResult(RESULT_OK);
                finish();
            }

        });
    }
    private void populateFields() {
        if (mRowId != null) {
            Cursor note = mDbHelper.get(mRowId);

            field_note.setText(note.getString(
                    note.getColumnIndexOrThrow(DB.KEY_NOTE)
            ));
        }
    }

}