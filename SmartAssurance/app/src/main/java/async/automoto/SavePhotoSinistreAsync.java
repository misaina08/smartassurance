package async.automoto;

import android.os.AsyncTask;

import com.aro.misaina.smartassurance.TabPhotosSinistreFragment;

import modeles.automoto.PhotosSinistreView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/13/2017.
 */

public class SavePhotoSinistreAsync extends AsyncTask<PhotosSinistreView, Void, String> {
    TabPhotosSinistreFragment fragment;

    @Override
    protected String doInBackground(PhotosSinistreView... params) {
        try {
            String url = WSUtil.getUrlServer() + "/vehicules/sinistres/photos/ajouter";
            WSRequestModele requestModele = new WSRequestModele();
            requestModele.post(url, params[0]);
            return "ok";
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(String res) {
        if (res != null) {
            fragment.initData();
        }
    }

    public TabPhotosSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabPhotosSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
