package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import async.retraite.RetraiteInfoAsync;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabInfoSousRetraiteFragment extends Fragment {
    private  Integer idsouscription;

    public TabInfoSousRetraiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        idsouscription = getArguments().getInt("idSouscription");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_info_sous_retraite, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view, bundle);

        Integer[] params = {idsouscription};
        RetraiteInfoAsync async = new RetraiteInfoAsync();
        async.setFragment(this);
        async.execute(params);

    }

}
