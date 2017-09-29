package async.automoto;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.RapportsFragment;

import java.util.List;

import adapter.ListeRapportsSinistresAdapter;
import modeles.automoto.SinRapportView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/20/2017.
 */

public class ListeRapportAsync extends AsyncTask<Integer, Void, List<SinRapportView>> {
    private RapportsFragment rapportsFragment;
    @Override
    protected List<SinRapportView> doInBackground(Integer... params) {
        try{
            String url = WSUtil.getUrlServer() + "/vehicules/sinistres/"+params[0]+"/rapports";
            WSRequestModele requestModele = new WSRequestModele();
            return (List<SinRapportView>)(List<?>) requestModele.get(url, new SinRapportView());
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<SinRapportView> sinRapportViews) {
        if (sinRapportViews!=null){
            RecyclerView rec = (RecyclerView) rapportsFragment.getActivity().findViewById(R.id.recRapports);
            rec.setLayoutManager(new LinearLayoutManager(rapportsFragment.getActivity()));
            ListeRapportsSinistresAdapter listeRapportsSinistresAdapter = new ListeRapportsSinistresAdapter();
            listeRapportsSinistresAdapter.setFragment(rapportsFragment);
            listeRapportsSinistresAdapter.setRapports(sinRapportViews);
            rec.setAdapter(listeRapportsSinistresAdapter);
        }
    }

    public RapportsFragment getRapportsFragment() {
        return rapportsFragment;
    }

    public void setRapportsFragment(RapportsFragment rapportsFragment) {
        this.rapportsFragment = rapportsFragment;
    }
}
