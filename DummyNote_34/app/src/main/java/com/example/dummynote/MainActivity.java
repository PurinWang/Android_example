package com.example.dummynote;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends  ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getListView().setEmptyView(findViewById(R.id.empty));
        setAdapter();

    }
//    private String[] note_array = {
//            "gasolin",
//            "crota",
//            "louk",
//            "magicion"
//    };
    private DB mDbHelper;
    private Cursor mNotesCursor;

    private void setAdapter() {
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, note_array);
//        setListAdapter(adapter);
        mDbHelper = new DB(this);
        mDbHelper.open();
        fillData();

    }

    private void fillData() {
        mNotesCursor = mDbHelper.getAll();

        String[] from = new String[]{DB.KEY_NOTE};
        int[] to = new int[]{android.R.id.text1};
        // Now create a simple cursor adapter
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mNotesCursor, from, to,  CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);
    }


}