package adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.ResumePaiementContratFragment;
import com.google.gson.Gson;

import java.util.List;

import modeles.paiement.MoyensPaiementClientView;
import modeles.souscription.Souscription;
import utilitaire.Util;

/**
 * Created by LENOVO on 8/8/2017.
 */

public class ListeMoyensPaiementAdapter extends RecyclerView.Adapter<ListeMoyensPaiementAdapter.ViewHolder> {
    private Integer action;
    private List<MoyensPaiementClientView> liste;
    Activity activity;
    private String dataJsonSouscrpition;

    @Override
    public ListeMoyensPaiementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_moyenspaiement, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeMoyensPaiementAdapter.ViewHolder holder, int position) {
        holder.vTitulaire.setText(liste.get(position).getNomComplet());
        holder.vNoCpt.setText(liste.get(position).getNumeroCompte());
        holder.vDateExp.setText(Util.dateToString(liste.get(position).getDateExpiration()));
        holder.vType.setText(liste.get(position).getLibelle());
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView vTitulaire;
        public TextView vNoCpt;
        public TextView vDateExp;
        public TextView vType;


        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            vTitulaire = ((TextView) itemView.findViewById(R.id.vTitulaire));
            vNoCpt = ((TextView) itemView.findViewById(R.id.vNoCpt));
            vDateExp = ((TextView) itemView.findViewById(R.id.vDateExp));
            vType = ((TextView) itemView.findViewById(R.id.vtype));

//            events
//            ex : item onclick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MoyensPaiementClientView paiement = liste.get(getAdapterPosition());
                    // action = 1 => modification du compte
                    if (action == 1) {
                        System.out.println("simple liste");
                    }
                    // action = 2 => payer un contrat
                    else if (action == 2) {
                        Gson gson = new Gson();
                        Souscription souscription = gson.fromJson(dataJsonSouscrpition, Souscription.class);
//                        if(souscription.getIdProduit() == 3){
                            ResumePaiementContratFragment popupResume = new ResumePaiementContratFragment();
                            Bundle b = new Bundle();
                            b.putString("dataJson", dataJsonSouscrpition);
                            b.putString("moyenPaiementJson", gson.toJson(paiement, MoyensPaiementClientView.class));
                            popupResume.setArguments(b);
                            popupResume.show(activity.getFragmentManager(), "resume");
//                        }

                    }
                }
            });
        }

    }

    public List<MoyensPaiementClientView> getListe() {
        return liste;
    }

    public void setListe(List<MoyensPaiementClientView> liste) {
        this.liste = liste;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDataJsonSouscrpition() {
        return dataJsonSouscrpition;
    }

    public void setDataJsonSouscrpition(String dataJsonSouscrpition) {
        this.dataJsonSouscrpition = dataJsonSouscrpition;
    }
}
