package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import services.SessionManager;

public class FormulaireSinistreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_sinistre);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulaire_sinistre, menu);

        MenuItem userAction = (MenuItem) menu.findItem(R.id.menuValiderCreationSinistre);
        SessionManager sessionManager = new SessionManager(this.getApplicationContext());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        return true;
    }

}
