package async.client;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.ListeComptePaiementActivity;
import com.aro.misaina.smartassurance.R;

import java.util.List;

import adapter.ListeMoyensPaiementAdapter;
import modeles.paiement.MoyensPaiementClientView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 8/8/2017.
 * params[0] contient l'id du clients
 * params[1] = 1 si simple liste
 * params[1] = 2 si paiement contrat
 */

public class ListeComptePaiementAsync extends AsyncTask<Integer, Void, List<MoyensPaiementClientView>> {
    private ListeComptePaiementActivity activity;
    private Integer action;
    private String dataJsonsouscription;
    @Override
    protected List<MoyensPaiementClientView> doInBackground(Integer... params) {
        try {

            String url = WSUtil.getUrlServer() + "/moyenspaiement/" + params[0];
            action = params[1];
            WSRequestModele request = new WSRequestModele();
            List<MoyensPaiementClientView> res = (List<MoyensPaiementClientView>) (List<?>) request.get(url, new MoyensPaiementClientView());
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<MoyensPaiementClientView> liste) {
        ListeMoyensPaiementAdapter adapter = new ListeMoyensPaiementAdapter();
        adapter.setListe(liste);
        adapter.setDataJsonSouscrpition(dataJsonsouscription);
        adapter.setAction(action);
        adapter.setActivity(activity);
        RecyclerView rec = (RecyclerView)activity.findViewById(R.id.recMoyenspaiement);
        rec.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        rec.setAdapter(adapter);
    }

    public ListeComptePaiementActivity getActivity() {
        return activity;
    }

    public void setActivity(ListeComptePaiementActivity activity) {
        this.activity = activity;
    }

    public String getDataJsonsouscription() {
        return dataJsonsouscription;
    }

    public void setDataJsonsouscription(String dataJsonsouscription) {
        this.dataJsonsouscription = dataJsonsouscription;
    }
}
