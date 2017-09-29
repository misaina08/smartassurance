package async.guichet;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.GuichetFragment;
import com.aro.misaina.smartassurance.R;

import java.util.List;

import adapter.ListeAttenteAdapter;
import modeles.guichet.AttenteView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/6/2017.
 */

public class DevantAsync extends AsyncTask<Integer, Void, List<AttenteView>> {
    GuichetFragment guichetFragment;
    @Override
    protected List<AttenteView> doInBackground(Integer... params) {
        try {
            String url = WSUtil.getUrlServer() + "/guichet/devant/" + params[0];
            WSRequestModele requestModele = new WSRequestModele();
            List<AttenteView> res = (List<AttenteView>) (List<?>) requestModele.get(url, new AttenteView());
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(List<AttenteView> attenteViews) {
        if (attenteViews != null) {
            RecyclerView recAttente = (RecyclerView) guichetFragment.getActivity().findViewById(R.id.recAttente);
            recAttente.setLayoutManager(new LinearLayoutManager(guichetFragment.getActivity().getApplicationContext(),  LinearLayoutManager.HORIZONTAL, false));
            ListeAttenteAdapter listeAttenteAdapter = new ListeAttenteAdapter();
            listeAttenteAdapter.setGuichetFragment(guichetFragment);
            listeAttenteAdapter.setAttentes(attenteViews);
            recAttente.setAdapter(listeAttenteAdapter);
        }

    }

    public GuichetFragment getGuichetFragment() {
        return guichetFragment;
    }

    public void setGuichetFragment(GuichetFragment guichetFragment) {
        this.guichetFragment = guichetFragment;
    }
}


