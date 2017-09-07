package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by misa on 9/6/2017.
 */

public class GuichetDao extends BaseDao {
    public GuichetDao(Context context) {
        dbHelper = DbHelper.init(context, true);
    }

    public Integer getNumeroEnCours() throws Exception {
        try {
            Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select numero from guichet", null);
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

    public void setNumeroEnCours(Integer numero) throws Exception {
        try {
            SQLiteDatabase db = this.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("numero", numero);
            if (getNumeroEnCours() == 0) {
                db.insert("guichet", null, values);
            } else {
                db.update("guichet", values, null, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public boolean estSurGuichet() throws Exception {
        try {
            Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select surguichet from guichet", null);
            Integer surguichet = 0;
            while (cursor.moveToNext()) {
                surguichet = cursor.getInt(0);
            }
            if (surguichet != 0) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void setSurGuichet(Integer value) throws Exception {
        try {
            SQLiteDatabase db = this.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("surguichet", value);

            db.update("guichet", values, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
