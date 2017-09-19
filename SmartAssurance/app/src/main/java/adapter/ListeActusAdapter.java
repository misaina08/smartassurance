package adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aro.misaina.smartassurance.ActualitesFragment;
import com.aro.misaina.smartassurance.FicheActusActivity;
import com.aro.misaina.smartassurance.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import modeles.Actualite;
import utilitaire.Util;
import utilitaire.WSUtil;

/**
 * Created by misa on 8/13/2017.
 */

public class ListeActusAdapter extends RecyclerView.Adapter<ListeActusAdapter.ViewHolder> {
    ActualitesFragment fragment;
    private List<Actualite> actualites;

    @Override
    public ListeActusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_actus, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeActusAdapter.ViewHolder holder, int position) {

        try {
            Picasso.with(holder.photo.getContext()).load(WSUtil.getUrlPhoto()+actualites.get(position).getPhoto()).into(holder.photo);
            holder.titre.setText(actualites.get(position).getTitre());
            holder.daty.setText(Util.dateToString(actualites.get(position).getDaty()) + " - "+actualites.get(position).getHeure());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return getActualites().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView photo;
        public final TextView titre;
        public final TextView daty;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            photo = ((ImageView) itemView.findViewById(R.id.photo));
            photo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            daty = ((TextView) itemView.findViewById(R.id.daty));
            titre = ((TextView) itemView.findViewById(R.id.titre));

//            events
//            ex : item onclick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fragment.getActivity(), FicheActusActivity.class);
                    Gson gson =new Gson();
                    intent.putExtra("dataJson", gson.toJson(actualites.get(getAdapterPosition())));
                    fragment.getActivity().startActivity(intent);
                }
            });
        }

    }

    public List<Actualite> getActualites() {
        return actualites;
    }

    public void setActualites(List<Actualite> actualites) {
        this.actualites = actualites;
    }

    public ActualitesFragment getFragment() {
        return fragment;
    }

    public void setFragment(ActualitesFragment fragment) {
        this.fragment = fragment;
    }
}
