package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import async.personne.ListePersonneAsync;

public class ListePersonneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_personne);

        ListePersonneAsync listePersonneAsync = new ListePersonneAsync();
        listePersonneAsync.setActivity(this);
        listePersonneAsync.execute();
    }

}
