package com.example.greenorbit2.mvc;

/**
 * Created by Green Orbit 2 on 11/1/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import static com.example.greenorbit2.mvc.MainActivity.APP_TAG;
import static com.example.greenorbit2.mvc.R.attr.title;

final class Model
{
    private static final String DB_NAME = "tasks_db";
    private static final String TABLE_NAME = "task";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_QUERY = "CREATE TABLE " +
            Model.TABLE_NAME + " (id integer primary key autoincrement,title text not null);";

    private final SQLiteDatabase database;

    private final SQLiteOpenHelper helper;

    public Model(final Context ctx)
    {
        this.helper = new SQLiteOpenHelper(ctx, Model.DB_NAME,
                null, Model.DB_VERSION)
        {
            @Override
            public void onCreate(final SQLiteDatabase db)
            {
                db.execSQL(Model.DB_CREATE_QUERY);
            }

            @Override
            public void onUpgrade(final SQLiteDatabase db,
                                  final int oldVersion, final int newVersion)
            {
                db.execSQL("DROP TABLE IF EXISTS " + Model.TABLE_NAME);
                this.onCreate(db);
            }
        };

        this.database = this.helper.getWritableDatabase();
    }

    public void addTask(ContentValues data)
    {
        this.database.insert(Model.TABLE_NAME, null, data);
    }

    public void deleteTask(final String field_params)
    {
        this.database.delete(Model.TABLE_NAME, field_params, null);
    }

    public Cursor loadAllTasks()
    {
        Log.d(APP_TAG, "loadAllTasks()");

        final Cursor c = this.database.query(Model.TABLE_NAME,
                new String[] { "title" }, null, null, null, null, null);

        return c;
    }}


