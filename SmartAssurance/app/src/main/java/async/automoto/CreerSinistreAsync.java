package async.automoto;

import android.os.AsyncTask;

import modeles.automoto.AmSinistreView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/12/2017.
 */

public class CreerSinistreAsync extends AsyncTask<AmSinistreView, Void, String> {
    @Override
    protected String doInBackground(AmSinistreView... params) {
        try{
            String url = WSUtil.getUrlServer()+"/vehicules/sinistres/save-info";
            WSRequestModele requestModele = new WSRequestModele();
            requestModele.post(url, params[0]);
            return "ok";
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String res){
        if(res != null){

        }
    }
}
