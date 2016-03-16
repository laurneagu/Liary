package com.Liary.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Laur on 1/25/2015.
 */
public class DatabaseManipulation {

    private static SQLiteDatabase db;

    private static String m_tableName = "tbl_lies";
    private static String m_dbName = "lie.db";

    public static final String KEY_ROWID = "id";
    public static final String KEY_TEXT = "lie";
    public static final String KEY_CATEGORY = "lie_category";
    public static final String KEY_LIED_TO = "lied_to";

    public static void OpenOrCreateDatabase(Context i_currContext){
        db  = i_currContext.openOrCreateDatabase(m_dbName, SQLiteDatabase.CREATE_IF_NECESSARY, null);

        db.setVersion(1);
        db.setLocale(Locale.getDefault());
   }

    public static void CreateLieTable(){
        String CREATE_TABLE_LIES =
                "CREATE TABLE IF NOT EXISTS " + m_tableName + " ("
                        + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_TEXT + " TEXT,"
                        + KEY_CATEGORY + " INTEGER,"
                        + KEY_LIED_TO + " TEXT);";

        db.execSQL(CREATE_TABLE_LIES);
    }

    public static void InsertLie(Lie i_lie ){
        ContentValues values = new ContentValues();

        values.put(KEY_TEXT, i_lie.getText());
        values.put(KEY_CATEGORY, i_lie.getCategory());
        values.put(KEY_LIED_TO, i_lie.getPersonLied());

        db.insert(m_tableName, null, values);

    }

    public static ArrayList<Lie> GetLies(){
        ArrayList<Lie> m_lies = new ArrayList<Lie>();

                Cursor cur = db.query(m_tableName,
                null, null, null, null, null, null);

        cur.moveToFirst();

        while (cur.isAfterLast() == false) {
            Lie curr_lie = new Lie("","",0);

            curr_lie.setId(Integer.parseInt(cur.getString(0)));
            curr_lie.setText(cur.getString(1));
            curr_lie.setCategory(Integer.parseInt(cur.getString(2)));
            curr_lie.setPersonLied(cur.getString(3));

            cur.moveToNext();

            m_lies.add(curr_lie);
        }
        cur.close();

        return m_lies;
    }

    public static void DeleteLieTableEntries(){
        String TRUNCATE_TABLE_LIES =
                "DELETE FROM " + m_tableName ;

        db.execSQL(TRUNCATE_TABLE_LIES);
    }

    public static void DeleteLie(int i_id){
        String WHERE_CLAUSE = KEY_ROWID + "=" + i_id;
        db.delete(m_tableName, WHERE_CLAUSE, null);
    }


}
