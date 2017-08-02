package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import async.souscription.ListeContratsAsync;

public class ListeContratsActivity extends AppCompatActivity {
    private LinearLayout contentContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_liste_contrats);



        contentContainer = (LinearLayout) findViewById(R.id.contentContainer);
        View viewListCont = getLayoutInflater().inflate(R.layout.activity_liste_contrats, null);
        System.out.println("))))))))))"+contentContainer);
        System.out.println("================="+viewListCont);
        contentContainer.addView(viewListCont);

        ListeContratsAsync listeContratsAsync = new ListeContratsAsync();
        listeContratsAsync.setListeContratsActivity(this);

        listeContratsAsync.execute();
    }
}
