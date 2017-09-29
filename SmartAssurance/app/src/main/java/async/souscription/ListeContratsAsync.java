package async.souscription;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.ListeContratsFragment;
import com.aro.misaina.smartassurance.R;

import java.util.ArrayList;
import java.util.List;

import adapter.ListeContratsAdapter;
import modeles.client.ClientView;
import modeles.souscription.Souscription;
import services.SessionManager;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 8/2/2017.
 */

public class ListeContratsAsync extends AsyncTask<Integer, Void, List<Souscription>> {
    private ListeContratsFragment listeContratsFragment;

    @Override
    protected List<Souscription> doInBackground(Integer... params) {
        List<Souscription> contrats = new ArrayList<Souscription>();
        SessionManager sessionManager = new SessionManager(listeContratsFragment.getActivity());

        try {
            ClientView clientConnected = SessionManager.getClientConnected();
            String urlContrat = WSUtil.getUrlServer() + "/souscription/contrats/" + clientConnected.getId();
            WSRequestModele wsRequestModele = new WSRequestModele();
            List<Souscription> res = (List<Souscription>) (List<?>) wsRequestModele.get(urlContrat, new Souscription());
            if (params[0] == 0) { //tout
                contrats.addAll(res);
            } else if (params[0] == 2) { // validés
                for (Souscription s : res) {
                    if (s.getValide() == 1) {
                        contrats.add(s);
                    }
                }
            } else { //non validés
                for (Souscription s : res) {
                    if (s.getValide() == 0) {
                        contrats.add(s);
                    }
                }
            }

            return contrats;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Souscription> contrats) {
        final RecyclerView recyclerView = (RecyclerView) listeContratsFragment.getActivity().findViewById(R.id.recContrats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getListeContratsFragment().getActivity().getBaseContext()));
        ListeContratsAdapter listeContratsAdapter = new ListeContratsAdapter();
        listeContratsAdapter.setListeContratsFragment(listeContratsFragment);
        listeContratsAdapter.setContrats(contrats);
        recyclerView.setAdapter(listeContratsAdapter);
    }

    public ListeContratsFragment getListeContratsFragment() {
        return listeContratsFragment;
    }

    public void setListeContratsFragment(ListeContratsFragment listeContratsFragment) {
        this.listeContratsFragment = listeContratsFragment;
    }
}
