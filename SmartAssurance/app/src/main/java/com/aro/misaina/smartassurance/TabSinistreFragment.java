package com.aro.misaina.smartassurance;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private SwipeRefreshLayout swipeRefresh;
    TabSinistreFragment fragment;
    private String datajson;
    public TabSinistreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = this;
        Bundle bundle = this.getArguments();
        idSouscription = bundle.getInt("idSouscription");
        datajson = bundle.getString("dataJson");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_sinistre, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view, bundle);

        fCreateSinistre= (FloatingActionButton)getView().findViewById(R.id.fCreateSinistre);
        swipeRefresh= (SwipeRefreshLayout)getView().findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
            }
        });
        fCreateSinistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragment.getActivity(), FormulaireSinistreActivity.class);
                intent.putExtra("idsouscription", idSouscription);
                intent.putExtra("datajson", datajson);
                fragment.getActivity().startActivity(intent);
            }
        });

        init();
    }

    public void init(){
        Integer[] params = new Integer[1];
        params[0] = idSouscription;
        ListeSinistresAsync async = new ListeSinistresAsync();
        async.setFragment(this);
        async.execute(params);
        swipeRefresh.setRefreshing(false);
    }

    public Integer getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Integer idSouscription) {
        this.idSouscription = idSouscription;
    }

}
