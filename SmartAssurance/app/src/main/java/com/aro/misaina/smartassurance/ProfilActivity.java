package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import async.client.ProfilClient;

public class ProfilActivity extends AppCompatActivity {
    public TextView nomUser;
    public TextView prenomUser;
    public TextView cin;
    public TextView dateNaiss;
    public TextView lieuNaiss;
    public TextView profession;
    public TextView email;
    public TextView tel;
    public TextView adresse;
    public TextView noclient;
    public TextView datesouscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        getSupportActionBar().setTitle("Profil");
        nomUser = (TextView) findViewById(R.id.nomUser);
        prenomUser = (TextView) findViewById(R.id.prenomUser);
        cin = (TextView) findViewById(R.id.cin);
        dateNaiss = (TextView) findViewById(R.id.dateNaiss);
        lieuNaiss = (TextView) findViewById(R.id.lieuNaiss);
        profession = (TextView) findViewById(R.id.profession);
        email = (TextView) findViewById(R.id.email);
        tel = (TextView) findViewById(R.id.tel);
        adresse = (TextView) findViewById(R.id.adresse);
        noclient = (TextView) findViewById(R.id.noclient);
        datesouscription = (TextView) findViewById(R.id.datesouscription);

        init();
    }

    public void init() {
        ProfilClient profilClient = new ProfilClient();
        profilClient.setProfilActivity(this);
        profilClient.execute();
    }
}
