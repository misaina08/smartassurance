package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import async.personne.CreatePersonneAsync;
import modeles.Personne;

public class CreatePersonneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personne);

        initComponents();
    }

    public void initComponents() {
        Button createButton = (Button) findViewById(R.id.createButton);
        final EditText nom = (EditText) findViewById((R.id.nom));
        final EditText prenom = (EditText) findViewById((R.id.prenom));
        final EditText datenaiss = (EditText) findViewById((R.id.dateNaiss));
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personne personne = new Personne();
                personne.setNom(nom.getText().toString());
                personne.setPrenom(prenom.getText().toString());
                personne.setDateNaiss(new Date(datenaiss.getText().toString()));

                Personne[] param = new Personne[1];
                param[0] = personne;

                CreatePersonneAsync createPersonneAsync = new CreatePersonneAsync();

                createPersonneAsync.execute(param);
            }
        });
    }
}
