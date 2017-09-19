package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import async.client.ConnexionClientAsync;

public class ConnexionActivity extends AppCompatActivity {
    private ConnexionActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        getSupportActionBar().setTitle(R.string.connexion);
        thisActivity = this;
        initComponents();
    }

    public void initComponents() {
        final EditText emailText = (EditText) findViewById(R.id.emailConnection);
        final EditText mdpText = (EditText) findViewById(R.id.mdpConnection);
        Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnexionClientAsync async = new ConnexionClientAsync();
                async.setConnexionActivity(thisActivity);
                String[] params = new String[2];
                params[0] = emailText.getText().toString();
                params[1] = mdpText.getText().toString();
                async.execute(params);

            }
        });
    }
}
