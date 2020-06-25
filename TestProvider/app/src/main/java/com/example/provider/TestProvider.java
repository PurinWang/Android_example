package com.example.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;

public class TestProvider extends ContentProvider {
	//SQLiteOpenHelper Ʈwtest.db Table:test
    private static class DatabaseHelper extends SQLiteOpenHelper {
        //test.db
    	DatabaseHelper(Context context) {
            super(context, "test.db", null, 1);
        }
    	@Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE test (" + BaseColumns._ID
                    + " INTEGER PRIMARY KEY," + "name TEXT,"
                    + "description TEXT" + ");");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS test");
            onCreate(db);
        }
    }
    //DatabaseHelper
    DatabaseHelper databaseHelper;
    //Content Providers onCreate
    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return true;
    }
    //Content Providers insert()
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.insert("test", null, values);
        return null;
    }
    //Content Providers query()
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("test");
        Cursor c = qb.query(db, projection, selection, selectionArgs, null,
                null, null);
        return c;
    }
    //Content Providers delete()
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }
    //Content Providers getType()
    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }
    //Content Providers update()
    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }
}
