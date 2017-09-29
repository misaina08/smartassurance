package com.aro.misaina.smartassurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import modeles.client.ClientView;
import services.SessionManager;

public class EspaceClientActivity extends AppCompatActivity {
    private ClientView client;
    private EspaceClientActivity activity;
    private ListView menu;
    private String[] menus = {"Profil", "Mes comptes de paiement", "Se déconnecter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espace_client);
        getSupportActionBar().setTitle("Espace client");
        try {
            activity = this;
            initData();
            initComponents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initComponents() {
        menu = (ListView) findViewById(R.id.listViewClient);
        ArrayAdapter<String> adapterMenu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menus);
        menu.setAdapter(adapterMenu);
        registerForContextMenu(menu);

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(activity, ProfilActivity.class);
                    activity.startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(EspaceClientActivity.this, ListeComptePaiementActivity.class);
                    intent.putExtra("action", "liste");
                    activity.startActivity(intent);
                } else if (position == 2) {
                    try {
                        SessionManager sessionManager = new SessionManager(activity);
                        sessionManager.destroyUserSession();
                        Intent intent = new Intent(activity, AccueilActivity.class);
                        activity.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void initData() throws Exception {
        SessionManager sessionManager = new SessionManager(this.getApplicationContext());
        client = SessionManager.getClientConnected();
    }


}
