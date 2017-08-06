package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;

import java.text.DecimalFormat;
import java.util.List;

import modeles.retraite.RtDepot;
import utilitaire.Util;

/**
 * Created by LENOVO on 8/6/2017.
 */

public class ListeDepotsAdapter extends RecyclerView.Adapter<ListeDepotsAdapter.ViewHolder> {

    private List<RtDepot> listeDepots;

    @Override
    public ListeDepotsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_depots, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeDepotsAdapter.ViewHolder holder, int position) {
        holder.vDate.setText(Util.dateToString(getListeDepots().get(position).getDaty()));
        holder.vMt.setText(new DecimalFormat("#,##0.00").format(getListeDepots().get(position).getValeur()));
    }

    @Override
    public int getItemCount() {
        return getListeDepots().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView vDate;
        public final TextView vMt;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            vDate = ((TextView) itemView.findViewById(R.id.vDate));
            vMt = ((TextView) itemView.findViewById(R.id.vMt));

//            events
//            ex : item onclick

        }

    }

    public List<RtDepot> getListeDepots() {
        return listeDepots;
    }

    public void setListeDepots(List<RtDepot> listeDepots) {
        this.listeDepots = listeDepots;
    }
}
