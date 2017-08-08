package async.souscription;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.aro.misaina.smartassurance.AccueilActivity;
import com.aro.misaina.smartassurance.R;

import modeles.paiement.HistoriquePaiementView;
import modeles.souscription.Souscription;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 8/8/2017.
 */

public class PayerContratAsync extends AsyncTask<HistoriquePaiementView, Void, Boolean> {
    private Context context;
    private Souscription souscription;
    @Override
    protected Boolean doInBackground(HistoriquePaiementView... params) {
        try {
            String url = WSUtil.getUrlServer() + "/souscription/payer";
            WSRequestModele wsRequestModele = new WSRequestModele();
            wsRequestModele.post(url, params[0]);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean res) {
        if (res) {
            Toast.makeText(context, "Le paiement a été effectué avec succès.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, AccueilActivity.class);
            intent.putExtra("tabid", R.id.tab_contrats);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Une erreur est survenue.", Toast.LENGTH_SHORT).show();
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Souscription getSouscription() {
        return souscription;
    }

    public void setSouscription(Souscription souscription) {
        this.souscription = souscription;
    }
}
