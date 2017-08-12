package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabDommagesSinistreFragment;

import java.util.List;

import async.automoto.SupprimerDommagesAsync;
import modeles.automoto.DommagesSinistreView;

/**
 * Created by misa on 8/12/2017.
 */

public class ListeDommagesAdapter extends RecyclerView.Adapter<ListeDommagesAdapter.ViewHolder> {
    TabDommagesSinistreFragment fragment;
    private List<DommagesSinistreView> dommages;

    @Override
    public ListeDommagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.liste_dommagessinistre, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListeDommagesAdapter.ViewHolder holder, final int position) {
        holder.tDommage.setText(dommages.get(position).getDommage());

        holder.bDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupprimerDommagesAsync ayncSuppr = new SupprimerDommagesAsync();
                ayncSuppr.setFragment(fragment);
                Integer[] params = new Integer[1];
                params[0] = dommages.get(position).getId();
                ayncSuppr.execute(params);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dommages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tDommage;
        public final Button bDel;

        public ViewHolder(View itemView) {
            super(itemView);

//            initialisation des composants graphiques
            tDommage = ((TextView) itemView.findViewById(R.id.tDommage));
            bDel = ((Button) itemView.findViewById(R.id.bDel));


//            events
//            ex : item onclick

        }

    }

    public List<DommagesSinistreView> getDommages() {
        return dommages;
    }

    public void setDommages(List<DommagesSinistreView> dommages) {
        this.dommages = dommages;
    }

    public TabDommagesSinistreFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabDommagesSinistreFragment fragment) {
        this.fragment = fragment;
    }
}


