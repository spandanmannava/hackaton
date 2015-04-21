package com.example.divya.asesqlite;

/**
 * Created by divya on 2/25/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
public class UserRepo {
    private DBHelper dbHelper;

    public UserRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(User user) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_name, user.username);
        values.put(User.KEY_password, user.password);


        // Inserting Row
        long user_id = db.insert(User.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) user_id;
    }

    public ArrayList<HashMap<String, String>> getUserList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                User.KEY_name + "," +
                User.KEY_password + "," +

                " FROM " + User.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("username", cursor.getString(cursor.getColumnIndex(User.KEY_name)));
                user.put("password", cursor.getString(cursor.getColumnIndex(User.KEY_password)));
                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;

    }

    public User getUserById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                User.KEY_name + "," +
                User.KEY_password + "," +
                " FROM " + User.TABLE
                + " WHERE " +
                User.KEY_name + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        User user = new User();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {

                user.username = cursor.getString(cursor.getColumnIndex(User.KEY_name));
                user.password = cursor.getString(cursor.getColumnIndex(User.KEY_password));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }
}