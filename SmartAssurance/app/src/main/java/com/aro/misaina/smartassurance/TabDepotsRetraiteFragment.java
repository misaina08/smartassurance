package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import async.retraite.ListeDepotsAsync;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabDepotsRetraiteFragment extends Fragment {

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
    public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view, bundle);

        Bundle b = this.getArguments();
        Integer[] params = new Integer[1];
        params[0]  = b.getInt("idSouscription");
        ListeDepotsAsync async = new ListeDepotsAsync();
        async.setFragment(this);
        async.execute(params);
    }

}
