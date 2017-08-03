package com.aro.misaina.smartassurance;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import async.souscription.ListeContratsAsync;

public class ListeContratsFragment extends Fragment {
    private LinearLayout contentContainer;
    public ListeContratsFragment(){

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListeContratsAsync listeContratsAsync = new ListeContratsAsync();
        listeContratsAsync.setListeContratsFragment(this);

        listeContratsAsync.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_liste_contrats, container, false);
    }
}
