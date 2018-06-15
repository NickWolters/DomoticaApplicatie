package com.example.nick.domoticapp.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nick.domoticapp.Model.RGB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class RGBValuesSqlite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "domotic_db";

    public RGBValuesSqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(RGB.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + RGB.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public long insertNote(String note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(RGB.COLUMN_RGB, note);
        // insert row
        long id = db.insert(RGB.TABLE_NAME, null, values);
        // close db connection
        db.close();
        // return newly inserted row id
        return id;
    }

    public RGB getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(RGB.TABLE_NAME,
                new String[]{RGB.COLUMN_ID, RGB.COLUMN_RGB, RGB.COLUMN_TIMESTAMP},
                RGB.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        // prepare note object
        RGB note = new RGB(
                cursor.getInt(cursor.getColumnIndex(RGB.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(RGB.COLUMN_RGB)),
                cursor.getString(cursor.getColumnIndex(RGB.COLUMN_TIMESTAMP)));
        // close the db connection
        cursor.close();
        return note;
    }

    public List<RGB> getAllNotes() {
        List<RGB> rgbs = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + RGB.TABLE_NAME + " ORDER BY " +
                RGB.COLUMN_TIMESTAMP + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RGB rgb = new RGB();
                rgb.setId(cursor.getInt(cursor.getColumnIndex(RGB.COLUMN_ID)));
                rgb.set(cursor.getString(cursor.getColumnIndex(RGB.COLUMN_RGB)));
                rgb.setTimestamp(cursor.getString(cursor.getColumnIndex(RGB.COLUMN_TIMESTAMP)));

                rgbs.add(note);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return notes list
        return rgbs;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + RGB.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public int updateNote(RGB note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RGB.COLUMN_NOTE, note.getNote());
        // updating row
        return db.update(RGB.TABLE_NAME, values, RGB.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(RGB note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RGB.TABLE_NAME, RGB.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}
}
