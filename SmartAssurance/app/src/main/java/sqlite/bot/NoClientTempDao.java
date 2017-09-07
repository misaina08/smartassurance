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

public class NoClientTempDao extends BaseDao {
    public NoClientTempDao(Context context) {
        dbHelper = DbHelper.init(context, true);
    }

    public String getNoClient() throws Exception{
        try {
            Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select noclient from noclient_temp", null);
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
    public void setNoClient(String noclient) throws Exception {
        try {
            SQLiteDatabase db = this.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("noclient", noclient);
            db.update("noclient_temp", values, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
