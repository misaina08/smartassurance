package com.aro.misaina.smartassurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import services.SessionManager;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


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
