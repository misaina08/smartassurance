package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import adapter.TabContratRetraiteAdapter;

public class FicheContratRetraiteActivity extends AppCompatActivity implements ActionBar.TabListener{
    private ViewPager pager;
    private String[] tabs = {"Info", "Dépots", "Retraits"};
    private android.support.v7.app.ActionBar actionBar;
    private TabContratRetraiteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_contrat_retraite);

        //        get souscription from async
        Integer idSouscription = getIntent().getExtras().getInt("idSouscription");

        pager = (ViewPager)findViewById(R.id.pagerRetraite);
        actionBar = getSupportActionBar();
        adapter = new TabContratRetraiteAdapter(getSupportFragmentManager());
        adapter.setIdSouscription(idSouscription);

        pager.setAdapter(adapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
