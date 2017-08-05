package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import async.souscription.ListeGarantiesSouscriptionAsync;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabGarantiesSousAutoFragment extends Fragment {
    private Integer idSouscription;

    public TabGarantiesSousAutoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        idSouscription = bundle.getInt("idSouscription");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_garanties_sous_auto, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Integer[] params = {idSouscription};
        ListeGarantiesSouscriptionAsync async = new ListeGarantiesSouscriptionAsync();
        async.setFragment(this);
        async.execute(params);
    }
}
