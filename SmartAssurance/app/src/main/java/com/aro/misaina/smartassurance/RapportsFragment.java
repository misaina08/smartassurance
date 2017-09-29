package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import async.automoto.ListeRapportAsync;
import modeles.automoto.AmSinistreView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RapportsFragment extends Fragment {
    private AmSinistreView sinistre;

    public RapportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle b = getArguments();
        Gson gson = new Gson();
        sinistre = gson.fromJson(b.getString("dataJson"), AmSinistreView.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rapports, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Integer[] param = {sinistre.getId()};
        ListeRapportAsync listeRapportAsync = new ListeRapportAsync();
        listeRapportAsync.setRapportsFragment(this);
        listeRapportAsync.execute(param);
    }
}
