package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import adapter.TabSinistreAdapter;
import modeles.automoto.AmSinistreView;

public class FicheSinistreActivity extends AppCompatActivity implements ActionBar.TabListener{
    AmSinistreView sinistreView;
    String dataJson;
    Integer idTab;
    ViewPager viewPager;
    TabSinistreAdapter adapter;
    private android.support.v7.app.ActionBar actionBar;
    private String[] tabNames = {"Informations", "Dommages", "Circonstances", "Photos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_sinistre);

        idTab = getIntent().getExtras().getInt("idtab");
        dataJson = getIntent().getExtras().getString("dataJson");
        Gson gson = new Gson();
        sinistreView = (AmSinistreView) gson.fromJson(dataJson, AmSinistreView.class);

        init();
    }


    public void init(){
        System.out.println("initu");
        viewPager = (ViewPager) findViewById(R.id.viewPagerSinistre);
        actionBar = getSupportActionBar();
        adapter = new TabSinistreAdapter(getSupportFragmentManager());
        adapter.setSinistre(sinistreView);

        viewPager.setAdapter(adapter);

        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabNames) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        viewPager.setCurrentItem(idTab);

        actionBar.setSelectedNavigationItem(idTab);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
