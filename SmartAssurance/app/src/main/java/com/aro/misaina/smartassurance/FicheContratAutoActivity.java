package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import adapter.TabContratAutoAdapter;

public class FicheContratAutoActivity extends AppCompatActivity implements ActionBar.TabListener {
    private ViewPager viewPager;
    private TabContratAutoAdapter adapter;
    private android.support.v7.app.ActionBar actionBar;

    private String[] tabNames = {"Informations", "Véhicule", "Garanties"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_contrat_auto);

//        get souscription from async
        Integer idSouscription = getIntent().getExtras().getInt("idSouscription");
        initSouscription(idSouscription);
    }


    public void initSouscription(Integer idSouscription){
        System.out.println("--------------"+idSouscription);
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        adapter = new TabContratAutoAdapter(getSupportFragmentManager());
        adapter.setIdSouscription(idSouscription);

        viewPager.setAdapter(adapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabNames) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

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
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

}
