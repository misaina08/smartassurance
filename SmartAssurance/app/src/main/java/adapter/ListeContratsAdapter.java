package adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aro.misaina.smartassurance.FicheContratAutoActivity;
import com.aro.misaina.smartassurance.FicheContratRetraiteActivity;
import com.aro.misaina.smartassurance.ListeContratsFragment;
import com.aro.misaina.smartassurance.R;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.List;

import modeles.souscription.Souscription;

/**
 * Created by LENOVO on 8/2/2017.
 */

public class ListeContratsAdapter extends RecyclerView.Adapter<ListeContratsAdapter.ViewHolder> {
    List<Souscription> contrats;
    private ListeContratsFragment listeContratsFragment;

    @Override
    public ListeContratsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_contrats, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeContratsAdapter.ViewHolder holder, int position) {
        holder.tTitreContrat.setText(contrats.get(position).getNomProduit());
        holder.tMt.setText(new DecimalFormat("#,##0.00").format(contrats.get(position).getPrimetotal()) + " Ar");
        if (contrats.get(position).getIdProduit() == 2) { //auto moto
            holder.iconeContrat.setImageDrawable(listeContratsFragment.getActivity().getResources().getDrawable(R.mipmap.ic_car_filled));
        }
        else if (contrats.get(position).getIdProduit() == 3) { //retraite
            holder.iconeContrat.setImageDrawable(listeContratsFragment.getActivity().getResources().getDrawable(R.mipmap.ic_trekking_filled));
        }
        if(contrats.get(position).getValide() == 1) {
            holder.iconeEtat.setImageDrawable(listeContratsFragment.getActivity().getResources().getDrawable(R.drawable.ic_checked));
        }
        if(contrats.get(position).getValide() != 1) {
            holder.iconeEtat.setImageDrawable(listeContratsFragment.getActivity().getResources().getDrawable(R.drawable.ic_radio));
        }

    }

    @Override
    public int getItemCount() {
        return contrats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tTitreContrat;

        public final TextView tMt;
        public final ImageView iconeContrat;
        public final ImageView iconeEtat;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            tTitreContrat = ((TextView) itemView.findViewById(R.id.tTitreContrat));

            tMt = ((TextView) itemView.findViewById(R.id.tMt));
            iconeContrat = ((ImageView) itemView.findViewById(R.id.iconeContrat));
            iconeEtat = ((ImageView) itemView.findViewById(R.id.iconeEtat));

//            events
//            ex : item onclick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gson gson = new Gson();
                    Souscription contratSelectionne = getContrats().get(getAdapterPosition());
                    String dataJson = gson.toJson(contratSelectionne);
                    if (contratSelectionne.getIdProduit() == 2) {
                        Intent intent = new Intent(listeContratsFragment.getActivity(), FicheContratAutoActivity.class);
                        intent.putExtra("idSouscription", contratSelectionne.getId());
                        intent.putExtra("idtab", 0);
                        intent.putExtra("dataJson", dataJson);
                        listeContratsFragment.getActivity().startActivity(intent);

                    }
                    else if(contratSelectionne.getIdProduit() == 3) {
                        Intent intent = new Intent(listeContratsFragment.getActivity(), FicheContratRetraiteActivity.class);
                        intent.putExtra("idSouscription", contratSelectionne.getId());
                        intent.putExtra("valide", contratSelectionne.getValide());
                        intent.putExtra("dataJson", dataJson);
                        listeContratsFragment.getActivity().startActivity(intent);
                    }
                }
            });

        }

    }

    public List<Souscription> getContrats() {
        return contrats;
    }

    public void setContrats(List<Souscription> contrats) {
        this.contrats = contrats;
    }

    public ListeContratsFragment getListeContratsFragment() {
        return listeContratsFragment;
    }

    public void setListeContratsFragment(ListeContratsFragment listeContratsFragment) {
        this.listeContratsFragment = listeContratsFragment;
    }
}
