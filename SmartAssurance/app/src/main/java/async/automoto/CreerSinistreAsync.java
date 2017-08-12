package async.automoto;

import android.content.Intent;
import android.os.AsyncTask;

import com.aro.misaina.smartassurance.FicheContratAutoActivity;
import com.aro.misaina.smartassurance.FormulaireSinistreActivity;

import modeles.automoto.AmSinistreView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/12/2017.
 */

public class CreerSinistreAsync extends AsyncTask<AmSinistreView, Void, Integer> {
    private FormulaireSinistreActivity activity;
    private String datajson;
    @Override
    protected Integer doInBackground(AmSinistreView... params) {
        try{
            String url = WSUtil.getUrlServer()+"/vehicules/sinistres/save-info";
            WSRequestModele requestModele = new WSRequestModele();
            requestModele.post(url, params[0]);
            return params[0].getSouscriptionProduitId();
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Integer idSouscription){
        if(idSouscription != null){
            Intent intent = new Intent(activity, FicheContratAutoActivity.class);
            intent.putExtra("idSouscription", idSouscription);
            intent.putExtra("idtab", 3);
            intent.putExtra("dataJson", datajson);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }
    }

    public FormulaireSinistreActivity getActivity() {
        return activity;
    }

    public void setActivity(FormulaireSinistreActivity activity) {
        this.activity = activity;
    }

    public String getDatajson() {
        return datajson;
    }

    public void setDatajson(String datajson) {
        this.datajson = datajson;
    }
}
