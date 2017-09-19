package adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.FicheSinistreActivity;
import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabSinistreFragment;
import com.google.gson.Gson;

import java.util.List;

import modeles.automoto.AmSinistreView;
import utilitaire.Util;

/**
 * Created by misa on 8/10/2017.
 */

public class ListeSinistresAdapter extends RecyclerView.Adapter<ListeSinistresAdapter.ViewHolder> {
    private TabSinistreFragment fragment;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fragment.getActivity(), FicheSinistreActivity.class);
                    intent.putExtra("idtab", 0);
                    Gson gson = new Gson();
                    intent.putExtra("dataJson", gson.toJson(listeSinistres.get(getAdapterPosition())));
                    fragment.getActivity().startActivity(intent);
                }
            });

        }

    }

    public List<AmSinistreView> getListeSinistres() {
        return listeSinistres;
    }

    public void setListeSinistres(List<AmSinistreView> listeSinistres) {
        this.listeSinistres = listeSinistres;
    }

    public TabSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabSinistreFragment fragment) {
        this.fragment = fragment;
    }
}
