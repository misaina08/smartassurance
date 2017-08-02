package async.souscription;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.ListeContratsActivity;
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

public class ListeContratsAsync extends AsyncTask<Void, Void, List<Souscription>> {
    private ListeContratsActivity listeContratsActivity;
    @Override
    protected List<Souscription> doInBackground(Void... params) {
        List<Souscription> contrats = new ArrayList<Souscription>();
        SessionManager sessionManager = new SessionManager(listeContratsActivity);

        try{
            ClientView clientConnected = SessionManager.getClientConnected();
            String urlContrat = WSUtil.getUrlServer()+"/souscription/contrats/"+clientConnected.getId();
            WSRequestModele wsRequestModele = new WSRequestModele();
            contrats.addAll((List<Souscription>)(List<?>)wsRequestModele.get(urlContrat, new Souscription()));
            return contrats;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Souscription> contrats) {
        final RecyclerView recyclerView = (RecyclerView) listeContratsActivity.findViewById(R.id.recContrats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getListeContratsActivity().getBaseContext()));
        ListeContratsAdapter listeContratsAdapter = new ListeContratsAdapter();
        listeContratsAdapter.setContrats(contrats);
        recyclerView.setAdapter(listeContratsAdapter);
    }

    public ListeContratsActivity getListeContratsActivity() {
        return listeContratsActivity;
    }

    public void setListeContratsActivity(ListeContratsActivity listeContratsActivity) {
        this.listeContratsActivity = listeContratsActivity;
    }
}
