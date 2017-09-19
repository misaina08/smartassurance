package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by misa on 9/6/2017.
 */

public class LangueDao extends BaseDao {
    public LangueDao(Context context) {
        dbHelper = DbHelper.init(context, true);
    }

    public String getCurrentLangue() throws Exception {
        try {
            Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select code from langue", null);
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

    public void setLangue(String langue) throws Exception {
        try {
            SQLiteDatabase db = this.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("code", langue);
            db.update("langue", values, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
