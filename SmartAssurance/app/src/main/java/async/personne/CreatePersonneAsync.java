package async.personne;

import android.os.AsyncTask;

import modeles.Personne;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by Misaina on 16/07/2017.
 */

public class CreatePersonneAsync extends AsyncTask<Personne, Void, String> {

    @Override
    protected String doInBackground(Personne... params) {
        try {
            String url = WSUtil.getUrlServer()+"/personnes/create";
            WSRequestModele wsRequestModele = new WSRequestModele();
            wsRequestModele.post(url, params[0]);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "no";
        }
    }

    @Override
    protected void onPostExecute(String personne) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
