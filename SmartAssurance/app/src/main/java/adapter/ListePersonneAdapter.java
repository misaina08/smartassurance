package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;

import java.util.List;

import modeles.Personne;

/**
 * Created by Misaina on 15/07/2017.
 */

public class ListePersonneAdapter extends RecyclerView.Adapter<ListePersonneAdapter.ViewHolder> {

    private List<Personne> listePersonne;

    @Override
    public ListePersonneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_personnes, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListePersonneAdapter.ViewHolder holder, int position) {
        holder.nomPers.setText(getListePersonne().get(position).getNom());
    }

    @Override
    public int getItemCount() {
        return listePersonne.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nomPers;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            nomPers = ((TextView) itemView.findViewById(R.id.nomPers));

//            events
//            ex : item onclick

        }

    }

    public List<Personne> getListePersonne() {
        return listePersonne;
    }

    public void setListePersonne(List<Personne> listePersonne) {
        this.listePersonne = listePersonne;
    }
}
