package com.aro.misaina.smartassurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    public void initComponents() {
        Button listeButton = (Button) findViewById(R.id.listeButton);
        Button createButton = (Button) findViewById(R.id.createButton);
        final MainActivity mainActivity=this;
        listeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mainActivity, ListePersonneActivity.class);
                        mainActivity.startActivity(intent);
                    }
                }
        );
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, CreatePersonneActivity.class);
                mainActivity.startActivity(intent);
            }
        });

    }
}
