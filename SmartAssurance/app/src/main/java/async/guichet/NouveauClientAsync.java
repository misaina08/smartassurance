package async.guichet;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;

import com.aro.misaina.smartassurance.AccueilActivity;

import modeles.guichet.AttenteView;
import services.ObjetsStatique;
import sqlite.GuichetDao;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/5/2017.
 * params[0] = idagence (dans le tag)
 * params[1] = token
 */

public class NouveauClientAsync extends AsyncTask<String, Void, String> {
    private AccueilActivity accueilActivity;

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

            System.out.println("____________________"+guichetDao.getNumeroEnCours());
            System.out.println("sur guichet : "+guichetDao.estSurGuichet());

            return "Mr/Mme, bienvenue à l'agence " + messagesTag[1] + ". Vous êtes N°" + resNumero;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String message) {
        if (message != null) {
            Snackbar.make(accueilActivity.contentAccueil, message, Snackbar.LENGTH_LONG).show();
            ObjetsStatique.setEstSurGuichet(true);
            accueilActivity.changeContent();
        }
    }

    public AccueilActivity getAccueilActivity() {
        return accueilActivity;
    }

    public void setAccueilActivity(AccueilActivity accueilActivity) {
        this.accueilActivity = accueilActivity;
    }
}

