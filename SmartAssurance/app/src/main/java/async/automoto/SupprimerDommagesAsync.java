package async.automoto;

import android.os.AsyncTask;

import com.aro.misaina.smartassurance.TabDommagesSinistreFragment;

import modeles.automoto.DommagesSinistreView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/13/2017.
 */

public class SupprimerDommagesAsync extends AsyncTask<Integer, Void, String> {
    TabDommagesSinistreFragment fragment;
    @Override
    protected String doInBackground(Integer... params) {

        try {
            String url = WSUtil.getUrlServer()+"/vehicules/sinistres/dommages/delete/"+params[0];
            WSRequestModele requestModele = new WSRequestModele();
            requestModele.getOne(url, new DommagesSinistreView());
            return "ok";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ok";
        }
    }

    @Override
    protected void onPostExecute(String res) {
        if(res != null){
            fragment.initDommages();
        }
    }

    public TabDommagesSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabDommagesSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
