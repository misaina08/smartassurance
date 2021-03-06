package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import async.client.ListeComptePaiementAsync;
import services.SessionManager;

public class ListeComptePaiementActivity extends AppCompatActivity {
    String action;
    BottomSheetBehavior bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_compte_paiement);
        getSupportActionBar().setTitle("Mes comptes de paiement");
        action = getIntent().getExtras().getString("action");
        String dataJson = getIntent().getExtras().getString("dataJson");

        try {
            initBottomSheet();

            ListeComptePaiementAsync async = new ListeComptePaiementAsync();
            async.setDataJsonsouscription(dataJson);
            async.setActivity(this);
            SessionManager sessionManager = new SessionManager(this);

            // params[1] = 1 si simple liste
            // params[1] = 2 si paiement contrat
            Integer[] params = new Integer[2];
            params[0] = SessionManager.getClientConnected().getId();
            if (action.compareToIgnoreCase("liste") == 0) {
                params[1] = 1;
            } else if (action.compareToIgnoreCase("paiement") == 0) {
                params[1] = 2;
            }
            async.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_crud, menu);

        MenuItem add = (MenuItem) menu.findItem(R.id.add);
        MenuItem update = (MenuItem) menu.findItem(R.id.update);
        MenuItem delete = (MenuItem) menu.findItem(R.id.delete);
        add.setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            bottomSheet.setPeekHeight(300);
        } else if (id == R.id.update) {

        } else if (id == R.id.delete) {

        }

        return super.onOptionsItemSelected(item);
    }

    public void initBottomSheet() {
        FrameLayout parentBottomSheet = (FrameLayout) findViewById(R.id.parentBottomSheet);
        bottomSheet = BottomSheetBehavior.from(parentBottomSheet);


        if (bottomSheet != null) {
//            setStateText(bottomSheet.getState());
            bottomSheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                    setStateText(newState);
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                    setOffsetText(slideOffset);

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (bottomSheet.getState() != BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}