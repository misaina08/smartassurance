package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import async.retraite.EstimationRetraiteAsync;
import async.retraite.ListeDepotsAsync;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabDepotsRetraiteFragment extends Fragment {
    private TextView total;
    private TextView estimation;

    public TabDepotsRetraiteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_depots_retraite, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        total = (TextView) getView().findViewById(R.id.total);
        estimation = (TextView) getView().findViewById(R.id.estimation);

        Bundle b = this.getArguments();
        Integer[] params = new Integer[1];
        params[0] = b.getInt("idSouscription");
        ListeDepotsAsync async = new ListeDepotsAsync();
        async.setFragment(this);
        async.execute(params);

        EstimationRetraiteAsync estimationRetraiteAsync =new EstimationRetraiteAsync();
        estimationRetraiteAsync.setTextView(estimation);
        estimationRetraiteAsync.execute();
    }

    public TextView getTotal() {
        return total;
    }

    public void setTotal(TextView total) {
        this.total = total;
    }
}
