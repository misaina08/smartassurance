package sqlite.bot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import sqlite.BaseDao;
import sqlite.DbHelper;

/**
 * Created by misa on 9/7/2017.
 */

public class QuestionCotisationDao extends BaseDao {
    public QuestionCotisationDao(Context context) {
        dbHelper = DbHelper.init(context, true);
    }

    public void setAge(Integer age) throws Exception {
        try {
            SQLiteDatabase db = this.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("age", age);
            db.update("question_cotisation", values, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void setMtDesire(Double mt) throws Exception {
        try {
            SQLiteDatabase db = this.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("mtdesire", mt);
            db.update("question_cotisation", values, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Integer getAge() throws Exception {
        try {
            Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select age from question_cotisation", null);
            Integer res = 0;
            while (cursor.moveToNext()) {
                res = cursor.getInt(0);
            }
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Double getMtDesire() throws Exception {
        try {
            Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select mtdesire from question_cotisation", null);
            Double res = new Double(0);
            while (cursor.moveToNext()) {
                res = cursor.getDouble(0);
            }
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
