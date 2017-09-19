package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aro.misaina.smartassurance.GuichetFragment;
import com.aro.misaina.smartassurance.R;

import java.util.List;

import modeles.guichet.AttenteView;
import sqlite.GuichetDao;

/**
 * Created by misa on 8/12/2017.
 */

public class ListeAttenteAdapter extends RecyclerView.Adapter<ListeAttenteAdapter.ViewHolder> {
    private GuichetFragment guichetFragment;
    private List<AttenteView> attentes;

    @Override
    public ListeAttenteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_attente, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeAttenteAdapter.ViewHolder holder, final int position) {
        holder.numero.setText("N° " + attentes.get(position).getNumero());

        try {
            GuichetDao guichetDao = new GuichetDao(guichetFragment.getActivity().getApplicationContext());
            Integer numeroEnCours = guichetDao.getNumeroEnCours();
            if(numeroEnCours.equals(attentes.get(position).getNumero())) {
                holder.numero.setBackgroundColor(guichetFragment.getActivity().getApplicationContext().getResources().getColor(R.color.green_aro));
                holder.numero.setTextColor(guichetFragment.getActivity().getApplicationContext().getResources().getColor(R.color.colorWhite));
            }
            if (attentes.get(position).getTermine()==1) {
                holder.numero.setBackgroundColor(guichetFragment.getActivity().getApplicationContext().getResources().getColor(R.color.colorYellow));
                holder.numero.setTextColor(guichetFragment.getActivity().getApplicationContext().getResources().getColor(R.color.colorWhite));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return attentes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView numero;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            numero = ((TextView) itemView.findViewById(R.id.numero));

//            events
//            ex : item onclick

        }

    }

    public List<AttenteView> getAttentes() {
        return attentes;
    }

    public void setAttentes(List<AttenteView> attentes) {
        this.attentes = attentes;
    }

    public GuichetFragment getGuichetFragment() {
        return guichetFragment;
    }

    public void setGuichetFragment(GuichetFragment guichetFragment) {
        this.guichetFragment = guichetFragment;
    }
}


