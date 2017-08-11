package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;

import java.util.List;

import modeles.automoto.AmSinistreView;
import utilitaire.Util;

/**
 * Created by LENOVO on 8/10/2017.
 */

public class ListeSinistresAdapter  extends RecyclerView.Adapter<ListeSinistresAdapter.ViewHolder> {

    private List<AmSinistreView> listeSinistres;

    @Override
    public ListeSinistresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_sinistres, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeSinistresAdapter.ViewHolder holder, int position) {
        holder.vDate.setText(Util.dateToString(getListeSinistres().get(position).getDaty()));
        holder.vCategorie.setText(getListeSinistres().get(position).getCategorie());
    }

    @Override
    public int getItemCount() {
        return getListeSinistres().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView vCategorie;
        public TextView vDate;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            vCategorie = ((TextView) itemView.findViewById(R.id.vCategorie));
            vDate = ((TextView) itemView.findViewById(R.id.vDate));

//            events
//            ex : item onclick

        }

    }

    public List<AmSinistreView> getListeSinistres() {
        return listeSinistres;
    }

    public void setListeSinistres(List<AmSinistreView> listeSinistres) {
        this.listeSinistres = listeSinistres;
    }
}
