package com.aro.misaina.smartassurance;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import services.ObjetsStatique;
import services.SessionManager;

public class AccueilActivity extends AppCompatActivity {
    private AccueilActivity accueilActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        accueilActivity = this;
        initBottomBar();

        ObjetsStatique objetsStatique = new ObjetsStatique();
        objetsStatique.init();
    }

    /**
     * Initialisation du bottom bar
     */
    public void initBottomBar() {
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = fragment = new BotFragment();
                if (tabId == R.id.tab_acueil) {

                }
                if (tabId == R.id.tab_ai) {
                    fragment = new BotFragment();
                }
                if (tabId == R.id.tab_parametres) {
                    fragment = new ParametresFragment();
                }
                if (tabId == R.id.tab_contrats) {
                    fragment = new ListeContratsFragment();
                }
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contentContainer, fragment)
                        .addToBackStack(fragment.getClass().getName())
                        .commit();
            }
        });
    }

    /**
     * Initialisation du menu sur la barre d'action en haut
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_accueil, menu);

        MenuItem userAction = (MenuItem) menu.findItem(R.id.user_action);
        MenuItem userSpace = (MenuItem) menu.findItem(R.id.user_space);
        SessionManager sessionManager = new SessionManager(this.getApplicationContext());
        if (SessionManager.estConnecte()) {
            userAction.setVisible(false);
            userSpace.setVisible(true);
        } else {
            userAction.setVisible(true);
            userSpace.setVisible(false);
        }
        return true;
    }

    /**
     * Evenement à appeler lorsqu'on clique les menus en haut
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.user_signup) {
            Intent intent = new Intent(AccueilActivity.this, SaisiInfoClientActivity.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.user_connect) {
            Intent intent = new Intent(AccueilActivity.this, ConnexionActivity.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.user_space) {
            Intent intent = new Intent(AccueilActivity.this, EspaceClientActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
