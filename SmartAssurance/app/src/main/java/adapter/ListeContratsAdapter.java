package adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.FicheContratAutoActivity;
import com.aro.misaina.smartassurance.FicheContratRetraiteActivity;
import com.aro.misaina.smartassurance.ListeContratsFragment;
import com.aro.misaina.smartassurance.R;

import java.text.DecimalFormat;
import java.util.List;

import modeles.souscription.Souscription;
import utilitaire.Util;

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
        holder.tTitreContrat.setText("Assurances " + contrats.get(position).getNomProduit());
        holder.eDate.setText(Util.dateToString(contrats.get(position).getDateSouscription()));
        holder.tMt.setText("Prime total à payer : " + new DecimalFormat("#,##0.00").format(contrats.get(position).getPrimetotal()) + " Ar");
    }

    @Override
    public int getItemCount() {
        return contrats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tTitreContrat;
        public final TextView eDate;
        public final TextView tMt;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            tTitreContrat = ((TextView) itemView.findViewById(R.id.tTitreContrat));
            eDate = ((TextView) itemView.findViewById(R.id.eDate));
            tMt = ((TextView) itemView.findViewById(R.id.tMt));

//            events
//            ex : item onclick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Souscription contratSelectionne = getContrats().get(getAdapterPosition());
                    if (contratSelectionne.getIdProduit() == 2) {
                        Intent intent = new Intent(listeContratsFragment.getActivity(), FicheContratAutoActivity.class);
                        intent.putExtra("idSouscription", contratSelectionne.getId());
                        listeContratsFragment.getActivity().startActivity(intent);

                    }
                    else if(contratSelectionne.getIdProduit() == 3) {
                        Intent intent = new Intent(listeContratsFragment.getActivity(), FicheContratRetraiteActivity.class);
                        intent.putExtra("idSouscription", contratSelectionne.getId());
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
