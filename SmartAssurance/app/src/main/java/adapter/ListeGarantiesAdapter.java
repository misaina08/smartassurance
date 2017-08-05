package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;

import java.text.DecimalFormat;
import java.util.List;

import modeles.automoto.GarantiVehiculeView;

/**
 * Created by LENOVO on 8/5/2017.
 */

public class ListeGarantiesAdapter extends RecyclerView.Adapter<ListeGarantiesAdapter.ViewHolder> {

    private List<GarantiVehiculeView> listeGaranties;

    @Override
    public ListeGarantiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_garanties, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeGarantiesAdapter.ViewHolder holder, int position) {
        holder.vLibGarantie.setText(listeGaranties.get(position).getLibelle());
        holder.vMtGarantie.setText(new DecimalFormat("#,##0.00").format(listeGaranties.get(position).getMtLimite()));
    }

    @Override
    public int getItemCount() {
        return listeGaranties.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView vLibGarantie;
        public final TextView vMtGarantie;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            vLibGarantie = ((TextView) itemView.findViewById(R.id.vLibGarantie));
            vMtGarantie = ((TextView) itemView.findViewById(R.id.vMtGarantie));

//            events
//            ex : item onclick
        }
    }

    public List<GarantiVehiculeView> getListeGaranties() {
        return listeGaranties;
    }

    public void setListeGaranties(List<GarantiVehiculeView> listeGaranties) {
        this.listeGaranties = listeGaranties;
    }
}