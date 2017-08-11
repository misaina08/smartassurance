package async.automoto;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabSinistreFragment;

import java.util.List;

import adapter.ListeSinistresAdapter;
import modeles.automoto.AmSinistreView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 8/10/2017.
 */

            public class ListeSinistresAsync extends AsyncTask<Integer, Void, List<AmSinistreView>> {
                TabSinistreFragment fragment;
                @Override
                protected List<AmSinistreView> doInBackground(Integer... params) {
                    try{
                        String url = WSUtil.getUrlServer()+"/vehicules/sinistres/"+params[0];
                        WSRequestModele requestModele = new WSRequestModele();
                        List<AmSinistreView> res = (List<AmSinistreView>)(List<?>)requestModele.get(url, new AmSinistreView());
                        return res;
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                        return  null;
        }
    }

    @Override
    protected void onPostExecute(List<AmSinistreView> liste){
        if(liste != null){
            RecyclerView rec = (RecyclerView)getFragment().getActivity().findViewById(R.id.recSinistres);
            rec.setLayoutManager(new LinearLayoutManager(getFragment().getActivity().getBaseContext()));
            ListeSinistresAdapter adapter = new ListeSinistresAdapter();
            adapter.setListeSinistres(liste);
            rec.setAdapter(adapter);
        }
    }

    public TabSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
