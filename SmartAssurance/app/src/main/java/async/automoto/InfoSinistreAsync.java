package async.automoto;

import android.os.AsyncTask;

import com.aro.misaina.smartassurance.TabInfoSinistreFragment;

import modeles.automoto.AmSinistreView;
import utilitaire.Util;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/12/2017.
 */

public class InfoSinistreAsync extends AsyncTask<Integer, Void, AmSinistreView> {
    private TabInfoSinistreFragment fragment;

    @Override
    protected AmSinistreView doInBackground(Integer... params) {
        try {
            String url = WSUtil.getUrlServer() + "/vehicules/sinistres/" + params[0];
            WSRequestModele requestModele = new WSRequestModele();
            return (AmSinistreView) requestModele.getOne(url, new AmSinistreView());
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    protected void onPostExecute(AmSinistreView res) {
        if (res != null) {
            fragment.gettAdresse().setText(res.getAdresseConducteur());
            fragment.gettCats().setText(res.getCatPermis());
            fragment.gettCatValidees().setText(res.getCatValidees());
            fragment.gettConducteur().setText("Moi");
            fragment.gettDateDeliv().setText(Util.dateToString(res.getDateDelivrance()));
            fragment.gettDn().setText("Né(e) le " + Util.dateToString(res.getDnConducteur()));
            fragment.gettDupl().setText(res.getNoduplicata());
            fragment.gettLieuDeliv().setText(res.getLieuDelivrance());
            fragment.gettValDeb().setText(Util.dateToString(res.getDebValidite()));
            fragment.gettValFin().setText(Util.dateToString(res.getFinValidite()));
            fragment.gettNo().setText(res.getNopermis());
            fragment.gettNom().setText(res.getNomConducteur()+" "+res.getPrenomConducteur());

        }
    }

    public TabInfoSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabInfoSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
