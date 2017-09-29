package async.retraite;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabDepotsRetraiteFragment;

import java.text.DecimalFormat;
import java.util.List;

import adapter.ListeDepotsAdapter;
import modeles.retraite.RtContratView;
import modeles.retraite.RtDepot;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 8/7/2017.
 */

/**
 * integer[0] contient l'idsouscription pour trouver le rt_contrat
 */
public class ListeDepotsAsync extends AsyncTask<Integer, Void, List<RtDepot>> {
    private TabDepotsRetraiteFragment fragment;
    @Override
    protected List<RtDepot> doInBackground(Integer... params) {
        try{
            String urlRtContrat = WSUtil.getUrlServer()+"/retraite/contrat/"+params[0];
            WSRequestModele requestModele = new WSRequestModele();
            RtContratView rtContrat = (RtContratView)requestModele.getOne(urlRtContrat, new RtContratView());
            String urlListDepots = WSUtil.getUrlServer()+"/retraite/depots/"+rtContrat.getId();
            List<RtDepot> res = (List<RtDepot>)(List<?>)requestModele.get(urlListDepots, new RtDepot());
            return res;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(List<RtDepot> liste){
        if(liste != null){
            RecyclerView rec = (RecyclerView)fragment.getView().findViewById(R.id.recDepots);
            rec.setLayoutManager(new LinearLayoutManager(fragment.getActivity().getBaseContext()));
            ListeDepotsAdapter adapter = new ListeDepotsAdapter();
            adapter.setListeDepots(liste);
            rec.setAdapter(adapter);
            Double total = new Double(0);
            for(RtDepot r : liste) {
                total += r.getValeur();
            }
            fragment.getTotal().setText(new DecimalFormat("#,##0.00").format(total) + " Ariary");
        }
    }

    public TabDepotsRetraiteFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabDepotsRetraiteFragment fragment) {
        this.fragment = fragment;
    }
}
