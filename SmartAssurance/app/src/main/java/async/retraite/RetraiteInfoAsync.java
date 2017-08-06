package async.retraite;

import android.os.AsyncTask;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabInfoSousRetraiteFragment;

import modeles.retraite.RtContratView;
import utilitaire.Util;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 8/6/2017.
 */

/**
 * params[0] contient l'idsouscription
 */
public class RetraiteInfoAsync extends AsyncTask<Integer, Void, RtContratView> {
    TabInfoSousRetraiteFragment fragment;

    @Override
    protected RtContratView doInBackground(Integer... params) {
        try {
            WSRequestModele request = new WSRequestModele();
            String url = WSUtil.getUrlServer() + "/retraite/contrat/" + params[0];
            RtContratView res = (RtContratView) request.getOne(url, new RtContratView());
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(RtContratView contrat) {
        if (contrat != null) {
            TextView vNopolice = (TextView) fragment.getView().findViewById(R.id.vNopolice);
            TextView vDatesouscription = (TextView) fragment.getView().findViewById(R.id.vDatesouscription);
            TextView vSouscripteur = (TextView) fragment.getView().findViewById(R.id.vSouscripteur);
            TextView vType = (TextView) fragment.getView().findViewById(R.id.vType);
            TextView vAge = (TextView) fragment.getView().findViewById(R.id.vAge);
            TextView vBeneficiaire = (TextView) fragment.getView().findViewById(R.id.vBeneficiaire);
            TextView vOption = (TextView) fragment.getView().findViewById(R.id.vOption);
            TextView vCotisation = (TextView) fragment.getView().findViewById(R.id.vCotisation);

            vNopolice.setText(contrat.getNoPolice());
            vDatesouscription.setText(Util.dateToString(contrat.getDateSouscription()));
            vSouscripteur.setText(contrat.getNomSouscripteur() + " " + contrat.getPrenomSouscripteur());
            vType.setText(contrat.getTypeRt());
            vAge.setText(contrat.getAgeRetraite().toString());
            vBeneficiaire.setText(contrat.getBeneficiaires());
            vOption.setText(contrat.getOptionRetraite());
            vCotisation.setText(contrat.getPeriodicite());
        }
    }

    public TabInfoSousRetraiteFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabInfoSousRetraiteFragment fragment) {
        this.fragment = fragment;
    }
}
