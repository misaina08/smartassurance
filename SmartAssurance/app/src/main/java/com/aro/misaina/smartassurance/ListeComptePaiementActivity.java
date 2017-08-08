package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import async.client.ListeComptePaiementAsync;
import services.SessionManager;

public class ListeComptePaiementActivity extends AppCompatActivity {
    String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_compte_paiement);

        action = getIntent().getExtras().getString("action");
        String dataJson = getIntent().getExtras().getString("dataJson");

        try {
            ListeComptePaiementAsync async = new ListeComptePaiementAsync();
            async.setDataJsonsouscription(dataJson);
            async.setActivity(this);
            SessionManager sessionManager = new SessionManager(this);

            // params[1] = 1 si simple liste
            // params[1] = 2 si paiement contrat
            Integer[] params = new Integer[2];
            params[0] = SessionManager.getClientConnected().getId();
            if(action.compareToIgnoreCase("liste") == 0){
                params[1] = 1;
            }
            else if(action.compareToIgnoreCase("paiement")==0){
                params[1] = 2;
            }
            async.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
