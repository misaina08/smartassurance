package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabCirconstanceSinistreFragment;

import java.util.List;

import modeles.automoto.SinCirconstanceView;

/**
 * Created by misa on 8/13/2017.
 */

public class ListeCirconstanceAdapter extends RecyclerView.Adapter<ListeCirconstanceAdapter.ViewHolder> {
    TabCirconstanceSinistreFragment fragment;
    private List<SinCirconstanceView> listeCirconstance;

    @Override
    public ListeCirconstanceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_circonstances, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeCirconstanceAdapter.ViewHolder holder, int position) {
        holder.eDesc.setText(listeCirconstance.get(position).getDescription());
        Integer etape = position + 1;
        holder.etape.setText(etape+"");
    }

    @Override
    public int getItemCount() {
        return listeCirconstance.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView eDesc;
        public final TextView etape;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            eDesc = ((TextView) itemView.findViewById(R.id.eDesc));
            etape = ((TextView) itemView.findViewById(R.id.etape));
            etape.setBackground(fragment.getResources().getDrawable(R.drawable.shape_grey_doux_fill));
//            etape.setTextColor(fragment.getResources().getColor(R.color.colorWhite));


//            events
//            ex : item onclick

        }

    }

    public List<SinCirconstanceView> getListeCirconstance() {
        return listeCirconstance;
    }

    public void setListeCirconstance(List<SinCirconstanceView> listeCirconstance) {
        this.listeCirconstance = listeCirconstance;
    }

    public TabCirconstanceSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabCirconstanceSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
