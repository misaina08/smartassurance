package async.personne;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.ListePersonneActivity;
import com.aro.misaina.smartassurance.R;

import java.util.List;

import adapter.ListePersonneAdapter;
import modeles.Personne;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by Misaina on 15/07/2017.
 */

public class ListePersonneAsync extends AsyncTask<Void, Void, List<Personne>> {
    ListePersonneActivity activity;

    public ListePersonneAsync(ListePersonneActivity ac) {
        setActivity(ac);
    }

    public ListePersonneAsync() {

    }

    @Override
    protected List<Personne> doInBackground(Void... params) {
        WSRequestModele wsRequestModele;
        try {
            wsRequestModele = new WSRequestModele();
            String url = WSUtil.getUrlServer() + "/personnes";
            List<Personne> liste = (List<Personne>) (List<?>) wsRequestModele.get(url, new Personne());
            return liste;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Personne> liste) {
        try {
            final RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recyclPersonne);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
            ListePersonneAdapter listePersonneAdapter = new ListePersonneAdapter();
            listePersonneAdapter.setListePersonne(liste);
            recyclerView.setAdapter(listePersonneAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListePersonneActivity getActivity() {
        return activity;
    }

    public void setActivity(ListePersonneActivity activity) {
        this.activity = activity;
    }
}
