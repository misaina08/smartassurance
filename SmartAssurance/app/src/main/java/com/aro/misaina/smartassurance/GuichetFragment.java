package com.aro.misaina.smartassurance;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import async.guichet.DevantAsync;
import sqlite.GuichetDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuichetFragment extends Fragment {
    public GuichetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guichet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    public void initData() {

        try {

            GuichetDao guichetDao = new GuichetDao(getActivity());
            Integer[] param = {guichetDao.getNumeroEnCours()};
            DevantAsync devantAsync = new DevantAsync();
            devantAsync.setGuichetFragment(this);
            devantAsync.execute(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
