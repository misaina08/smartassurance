package async.guichet;

import android.app.AlertDialog;
import android.os.AsyncTask;

import com.aro.misaina.smartassurance.AccueilActivity;
import com.aro.misaina.smartassurance.ProgressTagFragment;

import modeles.guichet.AttenteView;
import services.ObjetsStatique;
import sqlite.GuichetDao;
import utilitaire.WSUtil;
import ws.WSRequestModele;

import static com.google.android.gms.internal.zzagz.runOnUiThread;

/**
 * Created by misa on 9/5/2017.
 * params[0] = idagence (dans le tag)
 * params[1] = token
 */

public class NouveauClientAsync extends AsyncTask<String, Void, String> {
    private AccueilActivity accueilActivity;
    private AlertDialog.Builder builder;

    @Override
    protected String doInBackground(String... params) {
        try {
            String url = WSUtil.getUrlServer() + "/guichet/nouveau";
            WSRequestModele wsRequestModele = new WSRequestModele();

            String[] messagesTag = params[0].split("/");

            AttenteView attenteView = new AttenteView();
            attenteView.setIdAgence(new Integer(messagesTag[0]));
            attenteView.setToken(params[1]);

            String resNumero = wsRequestModele.post(url, attenteView);

            /* enregistrer le numero dans sqlite */
            GuichetDao guichetDao = new GuichetDao(accueilActivity);
            guichetDao.setNumeroEnCours(new Integer(resNumero));
            guichetDao.setSurGuichet(1);

            System.out.println("____________________" + guichetDao.getNumeroEnCours());
            System.out.println("sur guichet : " + guichetDao.estSurGuichet());

            return "Mr/Mme, bienvenue à l'agence " + messagesTag[1] + ". Vous êtes N°" + resNumero;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String message) {
        if (message != null) {
//            Snackbar.make(accueilActivity.contentAccueil, message, Snackbar.LENGTH_LONG).show();
            accueilActivity.changeContent(new ProgressTagFragment());
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        synchronized (this) {
                            wait(2000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ObjetsStatique.setEstSurGuichet(true);
                                    accueilActivity.changeContent();
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            timerThread.start();

        }
    }

    public AccueilActivity getAccueilActivity() {
        return accueilActivity;
    }

    public void setAccueilActivity(AccueilActivity accueilActivity) {
        this.accueilActivity = accueilActivity;
    }
}

