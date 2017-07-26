package async.client;

import android.content.Intent;
import android.os.AsyncTask;

import com.aro.misaina.smartassurance.ApresInscriptionActivity;
import com.aro.misaina.smartassurance.SaisiIdentifierClientActivity;

import modeles.Client;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by Misaina on 22/07/2017.
 */

public class InscriptionClientAsync extends AsyncTask<Client, Void, Client>{

    private SaisiIdentifierClientActivity activity;
    @Override
    protected Client doInBackground(Client... params) {
        try {
            String url = WSUtil.getUrlServer()+"/clients/inscrire";
            WSRequestModele wsRequestModele = new WSRequestModele();
            wsRequestModele.post(url, params[0]);
            return params[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected  void onPostExecute(Client a){
        if(a!=null){
            Intent intent = new Intent(activity, ApresInscriptionActivity.class);
            intent.putExtra("nomCompletClient", "Mr ou Mme "+a.getNom());
            activity.startActivity(intent);

        }
    }

    public SaisiIdentifierClientActivity getActivity() {
        return activity;
    }

    public void setActivity(SaisiIdentifierClientActivity activity) {
        this.activity = activity;
    }
}
