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

public class CurrentQuestionDao extends BaseDao {
    public CurrentQuestionDao(Context context) {
        dbHelper = DbHelper.init(context, true);
    }


    public String getCurrentQuestion() throws Exception {
        try {
            Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select action from current_question", null);
            String res = "";
            while (cursor.moveToNext()) {
                res = cursor.getString(0);
            }
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void setCurrentQuestion(String questionAction) throws Exception {
        try {
            SQLiteDatabase db = this.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("action", questionAction);
            db.update("current_question", values, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
