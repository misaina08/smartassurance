package async.automoto;

import android.os.AsyncTask;

import com.aro.misaina.smartassurance.TabDommagesSinistreFragment;

import modeles.automoto.DommagesSinistreView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/12/2017.
 */

/**
 * params[0] => idamsinistre
 * params[1] => idamdommage
 */
public class CreerDommagesAsync extends AsyncTask<Integer, Void, String> {
    TabDommagesSinistreFragment fragment;
    @Override
    protected String doInBackground(Integer... params) {
        try{
            String url = WSUtil.getUrlServer()+"/vehicules/sinistres/save-dommages";
            DommagesSinistreView dommagesSinistreView = new DommagesSinistreView();
            dommagesSinistreView.setAmDommageId(params[1]);
            dommagesSinistreView.setAmSinistreId(params[0]);
            WSRequestModele request = new WSRequestModele();
            request.post(url, dommagesSinistreView);
            return "";
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String res){
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
