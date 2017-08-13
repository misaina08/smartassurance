package async.automoto;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabPhotosSinistreFragment;

import java.util.List;

import adapter.ListePhotosSinistreAdapter;
import modeles.automoto.PhotosSinistreView;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/13/2017.
 */

public class ListePhotosSinistreAsync extends AsyncTask<Integer, Void, List<PhotosSinistreView>> {
    TabPhotosSinistreFragment fragment;
    @Override
    protected List<PhotosSinistreView> doInBackground(Integer... params) {
        try{
            String url = WSUtil.getUrlServer()+"/vehicules/sinistres/"+params[0]+"/photos";
            WSRequestModele reqest = new WSRequestModele();
            return (List<PhotosSinistreView>)(List<?>)reqest.get(url, new PhotosSinistreView());
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<PhotosSinistreView> res) {
        if(res != null){
            RecyclerView rec = (RecyclerView)fragment.getView().findViewById(R.id.recPhotos);
            rec.setLayoutManager(new LinearLayoutManager(fragment.getActivity().getBaseContext()));
            ListePhotosSinistreAdapter adapter = new ListePhotosSinistreAdapter();
            adapter.setFragment(fragment);
            adapter.setListePhotos(res);
            rec.setAdapter(adapter);
        }
    }

    public TabPhotosSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabPhotosSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
