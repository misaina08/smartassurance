package async.automoto;

import android.os.AsyncTask;

import com.aro.misaina.smartassurance.TabCirconstanceSinistreFragment;

import modeles.automoto.SinCirconstanceView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/13/2017.
 */

public class CreerCirconstanceAsync extends AsyncTask<SinCirconstanceView, Integer, String> {
    TabCirconstanceSinistreFragment fragment;
    @Override
    protected String doInBackground(SinCirconstanceView... params) {
        try {
            String url = WSUtil.getUrlServer()+"/vehicules/sinistres/circonstances/ajouter";
            WSRequestModele req = new WSRequestModele();
            req.post(url, params[0]);
            return "ok";
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String res){
        if(res != null){
            fragment.initData();
        }
    }

    public TabCirconstanceSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabCirconstanceSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
