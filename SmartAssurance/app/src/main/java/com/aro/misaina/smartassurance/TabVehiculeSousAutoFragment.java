package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import async.souscription.VehiculeSouscriptionAsync;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabVehiculeSousAutoFragment extends Fragment {
    private Integer idSouscription;

    public TabVehiculeSousAutoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        idSouscription = bundle.getInt("idSouscription");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_vehicule, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Integer[] params = {idSouscription};
        VehiculeSouscriptionAsync async = new VehiculeSouscriptionAsync();
        async.setFragment(this);
        async.execute(params);
    }


    public Integer getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Integer idSouscription) {
        this.idSouscription = idSouscription;
    }
}
