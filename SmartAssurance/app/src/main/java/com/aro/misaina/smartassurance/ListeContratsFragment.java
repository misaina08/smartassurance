package com.aro.misaina.smartassurance;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import async.souscription.ListeContratsAsync;

public class ListeContratsFragment extends Fragment {
//    private SwipeRefreshLayout swipeRefreshLayout;
    private Spinner spinnerFilter;
    public ListeContratsFragment(){

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            spinnerFilter = (Spinner) getActivity().findViewById(R.id.spinnerFilter);
            List<String> filtres = new ArrayList<String>(3);
            filtres.add(getActivity().getResources().getString(R.string.tout));
            filtres.add(getActivity().getResources().getString(R.string.non_valide));
            filtres.add(getActivity().getResources().getString(R.string.courant));
            ArrayAdapter<String> adapterFiltre = new ArrayAdapter<String>(this.getActivity(),
                    android.R.layout.simple_spinner_dropdown_item, filtres);
            spinnerFilter.setAdapter(adapterFiltre);
            initData(spinnerFilter.getSelectedItemPosition());
            spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    initData(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
//            swipeRefreshLayout = (SwipeRefreshLayout)getView().findViewById(R.id.swipeContrats);
//            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

//                @Override
//                public void onRefresh() {
//                    refresh();
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_liste_contrats, container, false);
    }

    public void refresh(){
        initData(spinnerFilter.getSelectedItemPosition());
//        swipeRefreshLayout.setRefreshing(false);
    }
    public void initData(Integer filtre){
        Integer[] param = {filtre};
        ListeContratsAsync listeContratsAsync = new ListeContratsAsync();
        listeContratsAsync.setListeContratsFragment(this);
        listeContratsAsync.execute(param);
    }


}
