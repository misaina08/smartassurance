package async.souscription;

import android.os.AsyncTask;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabInfoSousAutoFragment;

import java.text.DecimalFormat;

import modeles.souscription.Souscription;
import utilitaire.Util;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 8/5/2017.
 */

public class FicheSouscriptionAsync extends AsyncTask<Integer, Void, Souscription> {
    private TabInfoSousAutoFragment fragment;
    TextView vSouscripteur;
    TextView vDateDebut;
    TextView vDuree;
    TextView vNopolice;
    TextView vPrimeNet;
    TextView vPrimeTot;
    @Override
    protected Souscription doInBackground(Integer... params) {
        try {
            String url = WSUtil.getUrlServer()+"/souscription/"+params[0];
            WSRequestModele ws = new WSRequestModele();
            Souscription res = (Souscription)ws.getOne(url, new Souscription());
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Souscription souscription){
        vSouscripteur = (TextView) fragment.getView().findViewById(R.id.vSouscripteur);
        vDateDebut = (TextView) fragment.getView().findViewById(R.id.vDateDebut);
        vDuree = (TextView) fragment.getView().findViewById(R.id.vDuree);
        vNopolice = (TextView) fragment.getView().findViewById(R.id.vNopolice);
        vPrimeTot = (TextView) fragment.getView().findViewById(R.id.vPrimeTot);
        vPrimeNet = (TextView) fragment.getView().findViewById(R.id.vPrimeNet);

        vSouscripteur.setText(souscription.getNomSouscripteur() + " " + souscription.getPrenomSouscripteur());
        vDateDebut.setText(Util.dateToString(souscription.getDateSouscription()));
        vDuree.setText(souscription.getDuree() + " mois");
        vNopolice.setText(souscription.getNoPolice());
        vPrimeNet.setText(new DecimalFormat("#,##0.00").format(souscription.getPrimenet()) + " Ar");
        vPrimeTot.setText(new DecimalFormat("#,##0.00").format(souscription.getPrimetotal()) + " Ar");
    }

    public TabInfoSousAutoFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabInfoSousAutoFragment fragment) {
        this.fragment = fragment;
    }
}
