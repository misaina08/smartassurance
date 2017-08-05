package async.souscription;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabGarantiesSousAutoFragment;

import java.util.List;

import adapter.ListeGarantiesAdapter;
import modeles.automoto.GarantiVehiculeView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 8/5/2017.
 */

public class ListeGarantiesSouscriptionAsync extends AsyncTask<Integer, Void, List<GarantiVehiculeView>> {
    TabGarantiesSousAutoFragment fragment;

    @Override
    protected List<GarantiVehiculeView> doInBackground(Integer... params) {
        try {
            String url = WSUtil.getUrlServer() + "/vehicules/souscription/garanties/" + params[0];
            WSRequestModele request = new WSRequestModele();
            List<GarantiVehiculeView> res = (List<GarantiVehiculeView>) (List<?>) request.get(url, new GarantiVehiculeView());
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<GarantiVehiculeView> liste) {
        RecyclerView recycler = (RecyclerView) fragment.getView().findViewById(R.id.recGaranties);
        recycler.setLayoutManager(new LinearLayoutManager(getFragment().getActivity().getBaseContext()));
        ListeGarantiesAdapter adapter = new ListeGarantiesAdapter();
        adapter.setListeGaranties(liste);
        recycler.setAdapter(adapter);
    }

    public TabGarantiesSousAutoFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabGarantiesSousAutoFragment fragment) {
        this.fragment = fragment;
    }
}
