package async.automoto;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabDommagesSinistreFragment;

import java.util.List;

import adapter.ListeDommagesAdapter;
import modeles.automoto.DommagesSinistreView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/12/2017.
 */

/**
 * params[0] => idsinistre
 */
public class ListeDommagesAsync extends AsyncTask<Integer, Void, List<DommagesSinistreView>> {
    TabDommagesSinistreFragment fragment;
    @Override
    protected List<DommagesSinistreView> doInBackground(Integer... params) {
        try{
            String url = WSUtil.getUrlServer()+"/vehicules/sinistres/"+params[0]+"/dommages";
            WSRequestModele request = new WSRequestModele();
            return (List<DommagesSinistreView>)(List<?>)request.get(url, new DommagesSinistreView());
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<DommagesSinistreView> res){
        if(res != null){
            RecyclerView rec = (RecyclerView)fragment.getView().findViewById(R.id.recDommages);
            rec.setLayoutManager(new LinearLayoutManager(fragment.getActivity().getBaseContext()));
            ListeDommagesAdapter adapter = new ListeDommagesAdapter();
            adapter.setFragment(getFragment());
            adapter.setDommages(res);
            rec.setAdapter(adapter);
        }
    }

    public TabDommagesSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabDommagesSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
