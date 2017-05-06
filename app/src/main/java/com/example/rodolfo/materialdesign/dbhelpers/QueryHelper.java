package com.example.rodolfo.materialdesign.dbhelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.rodolfo.materialdesign.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodolfomg on 08/04/17.
 */

public class QueryHelper {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_ID,
                                    DBHelper.COLUMN_USERNAME,
                                    DBHelper.COLUMN_PASSWORD,
                                    DBHelper.COLUMN_EMAIL,
                                    DBHelper.COLUMN_PHONE};

    public QueryHelper(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User createUser(String username, String password, String email, String phone) {
        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_USERNAME, username);
        values.put(DBHelper.COLUMN_PASSWORD, password);
        values.put(DBHelper.COLUMN_EMAIL, email);
        values.put(DBHelper.COLUMN_PHONE, phone);

        long insertId = database.insert(DBHelper.TABLE_USERS, null, values);
        Cursor cursor = database.query(DBHelper.TABLE_USERS,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();
        User user = cursorToUser(cursor);
        cursor.close();

        return user;
    }

    public void deleteUser(User user) {
        long id = user.getId();
        System.out.println("Row deleted with id: " + id);
        database.delete(DBHelper.TABLE_USERS, DBHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.TABLE_USERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setPhone(cursor.getString(4));
        return user;
    }
}
