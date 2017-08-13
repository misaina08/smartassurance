package adapter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabPhotosSinistreFragment;

import java.io.IOException;
import java.util.List;

import modeles.automoto.PhotosSinistreView;
import utilitaire.FileUtil;

/**
 * Created by misa on 8/13/2017.
 */

public class ListePhotosSinistreAdapter  extends RecyclerView.Adapter<ListePhotosSinistreAdapter.ViewHolder> {
    TabPhotosSinistreFragment fragment;
    private List<PhotosSinistreView> listePhotos;

    @Override
    public ListePhotosSinistreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_photossinistre, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListePhotosSinistreAdapter.ViewHolder holder, int position) {

        try {
            FileUtil util = new FileUtil();

            Bitmap bitmap = util.getBitmapFromUri(fragment.getActivity().getApplicationContext(), Uri.parse(listePhotos.get(position).getPhoto()));

            //display the returned cropped image
            holder.iPhoto.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return getListePhotos().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView iPhoto;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            iPhoto = ((ImageView) itemView.findViewById(R.id.iPhoto));

//            events
//            ex : item onclick

        }

    }

    public List<PhotosSinistreView> getListePhotos() {
        return listePhotos;
    }

    public void setListePhotos(List<PhotosSinistreView> listePhotos) {
        this.listePhotos = listePhotos;
    }

    public TabPhotosSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabPhotosSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
