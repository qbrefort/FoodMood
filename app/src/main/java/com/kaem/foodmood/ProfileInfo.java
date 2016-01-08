package com.kaem.foodmood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by qbr on 08/01/16.
 */
public class ProfileInfo extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "MyDBName2.db";
    public static final String PROFILE_TABLE_NAME = "profile";
    public static final String PROFILE_COLUMNS_ID = "id";
    public static final String PROFILE_COLUMNS_NAME = "name";
    public static final String PROFILE_COLUMNS_SEX = "sex";
    public static final String PROFILE_COLUMNS_SIZE = "size";
    public static final String PROFILE_COLUMNS_AGE = "age";
    private HashMap hp;

    public ProfileInfo(Context context){
        super(context,DATABASE_NAME,null,1);
    }


    public boolean insertProfile  (String name, String sex, String size, String age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("sex", sex);
        contentValues.put("size", size);
        contentValues.put("age", age);
        db.insert("profile", null, contentValues);
        return true;
    }

    public boolean updateProfile  (Integer id, String name, String sex, String size, String age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("sex", sex);
        contentValues.put("size", size);
        contentValues.put("age", age);
        db.update("profile", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteProfile (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("profile",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public void deleteAllContacts()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("profile", null, null);
    }


    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from profile where id=" + id + "", null);
        return res;
    }

    public ArrayList<String> getAllContacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from profile", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PROFILE_COLUMNS_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public String getLastProfile()
    {
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mcursor = db.rawQuery("select count(*) from profile", null);
        mcursor.moveToFirst();
        if(mcursor.getInt(0)>0){
            Cursor res =  db.rawQuery( "select * from profile", null );
            res.moveToLast();
            return res.getString(res.getColumnIndex(PROFILE_COLUMNS_NAME));
        }else{
            return "";
        }


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table profile " +
                        "(id integer primary key, name text,sex text,size text, age text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS profile");
        onCreate(db);
    }
}
