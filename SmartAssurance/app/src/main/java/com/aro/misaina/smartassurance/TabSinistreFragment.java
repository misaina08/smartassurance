package com.aro.misaina.smartassurance;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import async.automoto.ListeSinistresAsync;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabSinistreFragment extends Fragment {
    private Integer idSouscription;
    private FloatingActionButton fCreateSinistre;
    TabSinistreFragment fragment;
    public TabSinistreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = this;
        Bundle bundle = this.getArguments();
        idSouscription = bundle.getInt("idSouscription");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_sinistre, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view, bundle);
        Integer[] params = new Integer[1];
        params[0] = idSouscription;
        ListeSinistresAsync async = new ListeSinistresAsync();
        async.setFragment(this);
        async.execute(params);

        fCreateSinistre= (FloatingActionButton)getView().findViewById(R.id.fCreateSinistre);
        fCreateSinistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragment.getActivity(), FormulaireSinistreActivity.class);
                fragment.getActivity().startActivity(intent);
            }
        });
    }

    public Integer getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Integer idSouscription) {
        this.idSouscription = idSouscription;
    }
}
