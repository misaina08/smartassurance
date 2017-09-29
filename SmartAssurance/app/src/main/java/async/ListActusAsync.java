package async;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.ActualitesFragment;
import com.aro.misaina.smartassurance.R;

import java.util.List;

import adapter.ListeActusAdapter;
import modeles.Actualite;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/16/2017.
 */

public class ListActusAsync extends AsyncTask<Void, Void, List<Actualite>> {
    private ActualitesFragment actualitesFragment;
    @Override
    protected List<Actualite> doInBackground(Void... params) {
        try {
            String url = WSUtil.getUrlServer() + "/actualites";
            WSRequestModele requestModele = new WSRequestModele();
            return (List<Actualite>) (List<?>) requestModele.get(url, new Actualite());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Actualite> actualites) {
        if (actualites != null) {
            RecyclerView recActus = (RecyclerView) actualitesFragment.getView().findViewById(R.id.recActus);
            recActus.setLayoutManager(new LinearLayoutManager(actualitesFragment.getActivity()));
            ListeActusAdapter listeActusAdapter = new ListeActusAdapter();
            listeActusAdapter.setActualites(actualites);
            listeActusAdapter.setFragment(actualitesFragment);
            recActus.setAdapter(listeActusAdapter);
        }
    }

    public ActualitesFragment getActualitesFragment() {
        return actualitesFragment;
    }

    public void setActualitesFragment(ActualitesFragment actualitesFragment) {
        this.actualitesFragment = actualitesFragment;
    }
}
