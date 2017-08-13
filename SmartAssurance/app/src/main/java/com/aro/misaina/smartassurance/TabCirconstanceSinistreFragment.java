package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import async.automoto.CreerCirconstanceAsync;
import async.automoto.ListeCirconstancesAsync;
import modeles.automoto.AmSinistreView;
import modeles.automoto.SinCirconstanceView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabCirconstanceSinistreFragment extends Fragment implements View.OnClickListener {
    private EditText eDescription;
    private Button bAjouter;
    private AmSinistreView sinistreView;

    public TabCirconstanceSinistreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bund = getArguments();
        Gson gson = new Gson();
        sinistreView = gson.fromJson(bund.getString("dataJson"), AmSinistreView.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_circonstance_sinistre, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);


        eDescription = (EditText) getView().findViewById(R.id.eDescription);
        bAjouter = (Button) getView().findViewById(R.id.bAjouter);

        bAjouter.setOnClickListener(this);

        initData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bAjouter) {
            SinCirconstanceView circ =  new SinCirconstanceView();
            circ.setIdsinistre(sinistreView.getId());
            circ.setDescription(eDescription.getText().toString());
            SinCirconstanceView[] par = new SinCirconstanceView[1];
            par[0] = circ;
            CreerCirconstanceAsync async = new CreerCirconstanceAsync();
            async.setFragment(this);
            async.execute(par);
        }
    }

    public void initData() {
        Integer[] params = new Integer[1];
        params[0] = sinistreView.getId();
        ListeCirconstancesAsync async = new ListeCirconstancesAsync();
        async.setFragment(this);
        async.execute(params);
    }
}
