package com.aro.misaina.smartassurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import modeles.client.ClientView;
import services.SessionManager;

public class EspaceClientActivity extends AppCompatActivity {
    private TextView nomUser;
    private ClientView client;
    private EspaceClientActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espace_client);

        try {
            activity = this;
            initData();
            initComponents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initComponents() {
        nomUser = (TextView) findViewById(R.id.nomUser);
        nomUser.setText(client.getNom() + " " + client.getPrenom());

        Button deconnect = (Button) findViewById(R.id.deconnectButton);
        deconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SessionManager sessionManager = new SessionManager(activity);
                    sessionManager.destroyUserSession();
                    Intent intent = new Intent(activity, AccueilActivity.class);
                    activity.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initData() throws Exception {
        SessionManager sessionManager = new SessionManager(this.getApplicationContext());
        client = SessionManager.getClientConnected();
    }
}
