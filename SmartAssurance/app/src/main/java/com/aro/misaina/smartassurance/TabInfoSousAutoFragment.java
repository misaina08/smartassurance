package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import async.souscription.FicheSouscriptionAsync;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabInfoSousAutoFragment extends Fragment {
//    private Souscription souscription;

    Integer idSouscription;

    public TabInfoSousAutoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        idSouscription = bundle.getInt("idSouscription");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_souscription_auto, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        Integer[] params = {idSouscription};
        FicheSouscriptionAsync async = new FicheSouscriptionAsync();
        async.setFragment(this);
        async.execute(params);

        System.out.println("onview created");
    }

//    public Souscription getSouscription() {
//        return souscription;
//    }
//
//    public void setSouscription(Souscription souscription) {
//        this.souscription = souscription;
//    }
}
