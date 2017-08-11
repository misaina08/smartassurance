package com.aro.misaina.smartassurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;

import adapter.TabContratRetraiteAdapter;
import modeles.souscription.Souscription;

public class FicheContratRetraiteActivity extends AppCompatActivity implements ActionBar.TabListener {
    private ViewPager pager;
    private String[] tabs = {"Info", "Dépots", "Retraits"};
    private android.support.v7.app.ActionBar actionBar;
    private TabContratRetraiteAdapter adapter;
    private Integer valide = 0;
    Integer idSouscription;
    FicheContratRetraiteActivity activity;
    private Souscription souscription;
    private String dataJsonsouscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_contrat_retraite);
         activity = this;
        //        get souscription from async
        idSouscription = getIntent().getExtras().getInt("idSouscription");
        valide = getIntent().getExtras().getInt("valide");
        Gson gson = new Gson();
        dataJsonsouscription = getIntent().getExtras().getString("dataJson");
        souscription = gson.fromJson(getIntent().getExtras().getString("dataJson"), Souscription.class);

        pager = (ViewPager) findViewById(R.id.pagerRetraite);
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

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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
        inflater.inflate(R.menu.menu_etatretraite, menu);
        MenuItem menuPayer = (MenuItem) menu.findItem(R.id.menuPayer);
//        if (valide == 0 || valide == null) {
            menuPayer.setTitle("Déposer");
            menuPayer.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = new Intent(FicheContratRetraiteActivity.this, ListeComptePaiementActivity.class);
                    intent.putExtra("action", "paiement");
                    intent.putExtra("dataJson", dataJsonsouscription);
                    activity.startActivity(intent);
                    return false;
                }
            });
//        }else{
//            menuPayer.setTitle("Payé");
//        }
        return true;
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
