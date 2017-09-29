package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;

import adapter.TabSinistreAdapter;
import async.automoto.CheckDemandeSinistreAsync;
import modeles.automoto.AmSinistreView;

public class FicheSinistreActivity extends AppCompatActivity implements ActionBar.TabListener {
    AmSinistreView sinistreView;
    String dataJson;
    Integer idTab;
    ViewPager viewPager;
    TabSinistreAdapter adapter;
    private android.support.v7.app.ActionBar actionBar;
    private String[] tabNames = {"Informations", "Dommages", "Circonstances", "Photos", "Rapports"};
    private MenuItem sendSinistre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_sinistre);
        getSupportActionBar().setTitle(R.string.sinistre);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idTab = getIntent().getExtras().getInt("idtab");
        dataJson = getIntent().getExtras().getString("dataJson");
        Gson gson = new Gson();
        sinistreView = (AmSinistreView) gson.fromJson(dataJson, AmSinistreView.class);

        init();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    public void init() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sinistre, menu);

        sendSinistre = (MenuItem) menu.findItem(R.id.sendSinistre);
        Integer[] param = {sinistreView.getId()};
        CheckDemandeSinistreAsync checkDemandeSinistreAsync = new CheckDemandeSinistreAsync();
        checkDemandeSinistreAsync.setPrec(0);
        checkDemandeSinistreAsync.setFicheSinistreActivity(this);
        checkDemandeSinistreAsync.execute(param);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sendSinistre) {
            Integer[] param = {sinistreView.getId()};
            CheckDemandeSinistreAsync checkDemandeSinistreAsync = new CheckDemandeSinistreAsync();
            checkDemandeSinistreAsync.setPrec(1);
            checkDemandeSinistreAsync.setFicheSinistreActivity(this);
            checkDemandeSinistreAsync.execute(param);
        }
        return super.onOptionsItemSelected(item);
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

    public MenuItem getSendSinistre() {
        return sendSinistre;
    }

    public void setSendSinistre(MenuItem sendSinistre) {
        this.sendSinistre = sendSinistre;
    }
}
