package com.aro.misaina.smartassurance;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import async.souscription.ListeContratsAsync;

public class ListeContratsFragment extends Fragment {
//    private SwipeRefreshLayout swipeRefreshLayout;
    public ListeContratsFragment(){

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            initData();
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
        initData();
//        swipeRefreshLayout.setRefreshing(false);
    }
    public void initData(){
        ListeContratsAsync listeContratsAsync = new ListeContratsAsync();
        listeContratsAsync.setListeContratsFragment(this);
        listeContratsAsync.execute();
    }

}
