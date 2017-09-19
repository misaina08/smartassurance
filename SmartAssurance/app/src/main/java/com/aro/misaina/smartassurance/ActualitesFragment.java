package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import async.ListActusAsync;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActualitesFragment extends Fragment {

    public ActualitesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actualites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    public void init() {
        ListActusAsync listActusAsync = new ListActusAsync();
        listActusAsync.setActualitesFragment(this);
        listActusAsync.execute();
    }

}
