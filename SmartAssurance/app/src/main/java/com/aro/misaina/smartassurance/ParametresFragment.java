package com.aro.misaina.smartassurance;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sqlite.LangueDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParametresFragment extends Fragment {
    private ListView lvParametre;
    private String[] menus = {"Langue", "A propos"};
    ParametresFragment parametresFragment;
    public ParametresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parametres, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvParametre = (ListView) getView().findViewById(R.id.lvParametre);
        ArrayAdapter<String> adapterMenu = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menus);
        lvParametre.setAdapter(adapterMenu);
        registerForContextMenu(lvParametre);

        parametresFragment = this;
        lvParametre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ChoixLangageFragment amPopFragment = new ChoixLangageFragment();
                    amPopFragment.setParametresFragment(parametresFragment);
                    amPopFragment.show(getActivity().getFragmentManager(), "choix langue popup");
                }
                else if(position == 1){

                }

            }
        });

        init();
    }



    public void init() {
        try {
            LangueDao langueDao = new LangueDao(getActivity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
