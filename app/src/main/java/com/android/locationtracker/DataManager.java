package com.android.locationtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Personal on 01/10/2018.
 */

public class DataManager {
    //Notification
    public static final String NOTIFICATION_TBL_ROW_ID = "notication_row_id";
    public static final String NOTIFICATION_TBL_TITLE = "notication_title";
    public static final String NOTIFICATION_TBL_MESSAGE = "notication_message";
    public static final String NOTIFICATION_TBL_DATE = "notication_date";
    public static final String NOTIFICATION_TBL_NAME = "notication";


    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "favourites_Db";
    private static final int DATABASE_VERSION = 1;
    private final Context mCtx;

    private static final String NOTIFICATION_TBL_CREATE =
            "CREATE TABLE " + NOTIFICATION_TBL_NAME + " (" +
                    NOTIFICATION_TBL_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTIFICATION_TBL_TITLE + " TEXT NULL, " +
                    NOTIFICATION_TBL_MESSAGE + " TEXT NULL, " +
                    NOTIFICATION_TBL_DATE + " TEXT NULL " +
                    ");";

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(NOTIFICATION_TBL_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+NOTIFICATION_TBL_NAME);
            onCreate(db);
        }
    }
    public DataManager(Context ctx)
    {
        this.mCtx = ctx;


    }

    public DataManager open() throws SQLException
    {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public DataManager openRead() throws  SQLException
    {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getReadableDatabase();
        return this;
    }



    public void close()
    {
        mDbHelper.close();
    }
    public Boolean isDbOpen()
    {
        if(mDb!=null)
            return mDb.isOpen();
        else
            return false;
    }

    public long insertNotification(NotificationObject notifyObj)
    {
        long retValue = 0;

        ContentValues values = new ContentValues();
        values.put(DataManager.NOTIFICATION_TBL_TITLE, notifyObj.push_title);
        values.put(DataManager.NOTIFICATION_TBL_MESSAGE, notifyObj.push_message);
        values.put(DataManager.NOTIFICATION_TBL_DATE, notifyObj.dateadded);

        retValue = mDb.insert(NOTIFICATION_TBL_NAME, null, values);

        return retValue;
    }

    public List<NotificationObject> getnotificationByDate()
    {
        List<NotificationObject> list = new ArrayList<NotificationObject>();

        Cursor c = mDb.query(DataManager.NOTIFICATION_TBL_NAME, new String[]{"*"},null,
                null, null, null, NOTIFICATION_TBL_ROW_ID +" desc", "10");

        if(c.moveToFirst())
        {
            do
            {
                NotificationObject notifyObj = new NotificationObject();
                notifyObj.push_title = c.getString(c.getColumnIndex(DataManager.NOTIFICATION_TBL_TITLE));
                notifyObj.push_message =  c.getString(c.getColumnIndex(DataManager.NOTIFICATION_TBL_MESSAGE));
                notifyObj.dateadded =  c.getString(c.getColumnIndex(DataManager.NOTIFICATION_TBL_DATE));
                list.add(notifyObj);
            } while (c.moveToNext());
        }
        c.close();

        return list;
    }

}

