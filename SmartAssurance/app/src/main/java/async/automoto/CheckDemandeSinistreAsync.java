package async.automoto;

import android.os.AsyncTask;
import android.widget.Toast;

import com.aro.misaina.smartassurance.FicheSinistreActivity;

import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/20/2017.
 */

public class CheckDemandeSinistreAsync extends AsyncTask<Integer, Void, Integer> {
    private FicheSinistreActivity ficheSinistreActivity;
    private Integer prec;

    @Override
    protected Integer doInBackground(Integer... params) {
        try {
            String url = WSUtil.getUrlServer() + "/vehicules/sinistres/" + params[0] + "/etat";
            WSRequestModele requestModele = new WSRequestModele();
            Integer res = new Integer(requestModele.getContent(url));
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Integer s) {
        if (s != null) {
            if (s == 0){
                ficheSinistreActivity.getSendSinistre().setVisible(true);
                Toast.makeText(ficheSinistreActivity, "Vous ne pouvez pas encore envoyer la déclaration. Veuillez ajouter les dommages, les photos sur le lieu, les circonstances", Toast.LENGTH_LONG).show();
            }
            else{
                ficheSinistreActivity.getSendSinistre().setVisible(false);
                if(prec == 1){
                    Toast.makeText(ficheSinistreActivity, "Déclaration de sinistre envoyé", Toast.LENGTH_LONG).show();
                    ficheSinistreActivity.finish();
                }
            }
        }
    }

    public FicheSinistreActivity getFicheSinistreActivity() {
        return ficheSinistreActivity;
    }

    public void setFicheSinistreActivity(FicheSinistreActivity ficheSinistreActivity) {
        this.ficheSinistreActivity = ficheSinistreActivity;
    }

    public Integer getPrec() {
        return prec;
    }

    public void setPrec(Integer prec) {
        this.prec = prec;
    }
}
