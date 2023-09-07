package com.example.crud1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    String table ="user";

    public DBHelper(@Nullable Context context) {
        super(context, "testdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "cREATE TABLE IF NOT EXISTS "+table+"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,username TEXT,password TEXT)";
        db.execSQL(sql);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+table);
        onCreate(db);

    }

    public boolean insertData(String name1, String username1, String password1){

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name1);
        contentValues.put("username", username1);
        contentValues.put("password", password1);

        try {
            this.getReadableDatabase().insertOrThrow(table,null,contentValues);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteData(int id) {
        try {
            this.getReadableDatabase().execSQL("DELETE FROM "+table+" WHERE id="+id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modifyData(int id, String name, String email) {
        try {
            String query = "UPDATE " + table + " SET name = '" + name + "', username = '" + email + "' WHERE id = " + id;
            this.getReadableDatabase().execSQL(query);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
