package utilitaire;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

import sqlite.LangueDao;

/**
 * Created by misa on 9/14/2017.
 */

public class LocaleHelper {
    private Context context;
    public LocaleHelper(Context context) {
        this.context = context;
    }
    public void setLocale() {

        final Resources resources = context.getResources();
        final Configuration configuration = resources.getConfiguration();
        final Locale locale = getLocale(context);
        if (!configuration.locale.equals(locale)) {
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration, null);
        }
    }

    public Locale getLocale(Context context) {
        try {
            LangueDao langueDao = new LangueDao(context);
            return new Locale(langueDao.getCurrentLangue());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}