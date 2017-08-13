package async.automoto;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabCirconstanceSinistreFragment;

import java.util.List;

import adapter.ListeCirconstanceAdapter;
import modeles.automoto.SinCirconstanceView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/13/2017.
 */

public class ListeCirconstancesAsync extends AsyncTask<Integer, Void, List<SinCirconstanceView>> {
    TabCirconstanceSinistreFragment fragment;
    @Override
    protected List<SinCirconstanceView> doInBackground(Integer... params) {
        try{
            String url = WSUtil.getUrlServer()+"/vehicules/sinistres/"+params[0]+"/circonstances";
            WSRequestModele requestModele = new WSRequestModele();
            return (List<SinCirconstanceView>)(List<?>)requestModele.get(url, new SinCirconstanceView());
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<SinCirconstanceView> res){
        if(res != null){
            RecyclerView rec = (RecyclerView)fragment.getView().findViewById(R.id.recCirconstance);
            rec.setLayoutManager(new LinearLayoutManager(fragment.getActivity().getBaseContext()));
            ListeCirconstanceAdapter adapter = new ListeCirconstanceAdapter();
            adapter.setFragment(getFragment());
            adapter.setListeCirconstance(res);
            rec.setAdapter(adapter);
        }
    }

    public TabCirconstanceSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabCirconstanceSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
