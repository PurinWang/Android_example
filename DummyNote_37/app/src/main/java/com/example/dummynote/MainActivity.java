package com.example.dummynote;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;

public class MainActivity extends  ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getListView().setEmptyView(findViewById(R.id.empty));
        registerForContextMenu(getListView());
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
                new SimpleCursorAdapter(this, android.R.layout.simple_list_item_activated_1, mNotesCursor, from, to,  CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);
    }

    private int mNoteNumber = 1;
    protected static final int MENU_INSERT = Menu.FIRST;
    protected static final int MENU_DELETE = Menu.FIRST+1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,MENU_INSERT,0,"新增記事");
        menu.add(0,MENU_DELETE,0,"刪除記事");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case MENU_INSERT:
                String noteName = "Note" + mNoteNumber++;
                mDbHelper.create(noteName);
            case MENU_DELETE:
                Log.e("get", String.valueOf(getListView().getSelectedItemId()));
                mDbHelper.delete(getListView().getSelectedItemId());
                fillData();
        }
        return super.onOptionsItemSelected(item);
    }

    private static final int ACTIVITY_EDIT=0x1001;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, NoteEdit.class);
        intent.putExtra(DB.KEY_ROWID, id);
        startActivityForResult(intent, ACTIVITY_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == ACTIVITY_EDIT){
                fillData();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, MENU_DELETE, 0,  "刪除記事");
        menu.setHeaderTitle("Detail view");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case MENU_DELETE:
                Log.d("MENU", "item"+info.id);
                mDbHelper.delete(info.id);
                fillData();
                break;
        }

        return super.onContextItemSelected(item);
    }
}