package com.aro.misaina.smartassurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;

import adapter.TabContratAutoAdapter;
import modeles.souscription.Souscription;

    public class FicheContratAutoActivity extends AppCompatActivity implements ActionBar.TabListener {
        private ViewPager viewPager;
        private TabContratAutoAdapter adapter;
        private android.support.v7.app.ActionBar actionBar;
        private Souscription souscription;
        private String dataJsonsouscription;
        private FicheContratAutoActivity activity;
        Integer idTab;
        private String[] tabNames = {"Informations", "Véhicule", "Garanties", "Sinistres"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_contrat_auto);
        getSupportActionBar().setTitle("Contrat auto-moto");
        activity = this;

//        get souscription from async
        Integer idSouscription = getIntent().getExtras().getInt("idSouscription");
        idTab = getIntent().getExtras().getInt("idtab");
        dataJsonsouscription = getIntent().getExtras().getString("dataJson");
        initSouscription(idSouscription);
        Gson gson = new Gson();
        souscription = gson.fromJson(getIntent().getExtras().getString("dataJson"), Souscription.class);
    }


    public void initSouscription(Integer idSouscription) {

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        adapter = new TabContratAutoAdapter(getSupportFragmentManager());
        adapter.setIdSouscription(idSouscription);
//        System.out.println("fiche contrat =" +dataJsonsouscription);
        adapter.setDataJson(dataJsonsouscription);


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
        inflater.inflate(R.menu.menu_etatretraite, menu);
        MenuItem menuPayer = (MenuItem) menu.findItem(R.id.menuPayer);
        if (souscription.getValide() == 0 || souscription.getValide() == null) {
            menuPayer.setTitle("Payer");
            menuPayer.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = new Intent(FicheContratAutoActivity.this, ListeComptePaiementActivity.class);
                    intent.putExtra("action", "paiement");
                    intent.putExtra("dataJson", dataJsonsouscription);
                    activity.startActivity(intent);
                    return false;
                }
            });
        } else {
            menuPayer.setTitle("Validé");
        }
        return true;
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
