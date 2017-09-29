package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import modeles.Actualite;
import utilitaire.Util;
import utilitaire.WSUtil;

public class FicheActusActivity extends AppCompatActivity {
    private TextView titre;
    private TextView contenu;
    private TextView daty;
    private ImageView photo;

    String jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_actus);

        getSupportActionBar().setTitle("Fiche de l'actualité");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        jsonData = getIntent().getExtras().getString("dataJson");
        Gson gson = new Gson();
        Actualite actu = gson.fromJson(jsonData, Actualite.class);

        titre = (TextView) findViewById(R.id.titre);
        contenu = (TextView) findViewById(R.id.contenu);
        daty = (TextView) findViewById(R.id.daty);
        photo = (ImageView) findViewById(R.id.photo);

        titre.setText(actu.getTitre());
        contenu.setText(actu.getContenu());
        daty.setText(Util.dateToString(actu.getDaty()) + " à " + actu.getHeure());
        Picasso.with(photo.getContext()).load(WSUtil.getUrlPhoto()+actu.getPhoto()).into(photo);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
