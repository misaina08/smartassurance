package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.RapportsFragment;

import java.util.List;

import modeles.automoto.SinRapportView;

/**
 * Created by misa on 8/10/2017.
 */

public class ListeRapportsSinistresAdapter extends RecyclerView.Adapter<ListeRapportsSinistresAdapter.ViewHolder> {
    private RapportsFragment fragment;
    private List<SinRapportView> rapports;

    @Override
    public ListeRapportsSinistresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_rapportssinistre, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeRapportsSinistresAdapter.ViewHolder holder, int position) {
        Integer num = position+1;
        holder.numeroRapport.setText(num.toString());
        holder.vCategorie.setText(rapports.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return getRapports().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView vCategorie;
        public TextView numeroRapport;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            vCategorie = ((TextView) itemView.findViewById(R.id.details));
            numeroRapport = ((TextView) itemView.findViewById(R.id.numeroRapport));
            numeroRapport.setBackground(fragment.getResources().getDrawable(R.drawable.shape_grey_doux_fill));

//            events
//            ex : item onclick
        }

    }

    public RapportsFragment getFragment() {
        return fragment;
    }

    public void setFragment(RapportsFragment fragment) {
        this.fragment = fragment;
    }

    public List<SinRapportView> getRapports() {
        return rapports;
    }

    public void setRapports(List<SinRapportView> rapports) {
        this.rapports = rapports;
    }
}
