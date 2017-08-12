package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import async.automoto.CreerDommagesAsync;
import async.automoto.ListeDommagesAsync;
import modeles.automoto.AmDommage;
import modeles.automoto.AmSinistreView;
import services.ObjetsStatique;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabDommagesSinistreFragment extends Fragment {
    private Spinner spDommages;
    private Button bAdd;
    private SwipeRefreshLayout swDommages;
    private AmSinistreView sinistre;
    TabDommagesSinistreFragment fragmentThis;

    public TabDommagesSinistreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentThis = this;
        Bundle b = getArguments();
        Gson gson = new Gson();
        sinistre = gson.fromJson(b.getString("dataJson"), AmSinistreView.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_dommages_sinistre, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        spDommages = (Spinner) getView().findViewById(R.id.spDommages);
        bAdd = (Button) getView().findViewById(R.id.bAdd);
        swDommages = (SwipeRefreshLayout) getView().findViewById(R.id.swDommages);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer[] params = new Integer[2];
                params[0] = sinistre.getId();
                params[1] = ObjetsStatique.getAmDommages().get(spDommages.getSelectedItemPosition()).getId();
                CreerDommagesAsync async = new CreerDommagesAsync();
                async.setFragment(fragmentThis);
                async.execute(params);
            }
        });

        swDommages.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initDommages();
            }
        });
        initData();
        initDommages();
    }

    public void initData() {
        List<String> dommagesString = new ArrayList<String>(ObjetsStatique.getAmDommages().size());
        for (AmDommage d : ObjetsStatique.getAmDommages()) {
            dommagesString.add(d.toString());
        }
        ArrayAdapter<String> adapterDommages = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, dommagesString);
        spDommages.setAdapter(adapterDommages);


    }

    public void initDommages(){
        Integer[] params = new Integer[1];
        params[0] = sinistre.getId();
        ListeDommagesAsync asyncliste = new ListeDommagesAsync();
        asyncliste.setFragment(this);
        asyncliste.execute(params);
        swDommages.setRefreshing(false);
    }

}
