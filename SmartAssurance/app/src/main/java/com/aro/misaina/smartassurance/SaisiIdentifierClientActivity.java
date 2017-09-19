package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import async.client.InscriptionClientAsync;
import modeles.Agence;
import modeles.ClType;
import modeles.Client;
import utilitaire.Util;

public class SaisiIdentifierClientActivity extends AppCompatActivity {
    private Client client;
    private SaisiIdentifierClientActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisi_identifier_client);
        getSupportActionBar().setTitle(R.string.inscription);
        try {
            client = new Client();
            thisActivity = this;
            Bundle bundle = getIntent().getExtras();
            client.setNom(bundle.getString("nomClient"));
            client.setPrenom(bundle.getString("prenomClient"));
            client.setAdresse(bundle.getString("adresseClient"));
            client.setProfession(bundle.getString("professionClient"));
            client.setDateNaissance(Util.stringToDate(bundle.getString("dateNaissClient")));
            client.setLieuNaissance(bundle.getString("lieuNaissClient"));
            client.setCin(bundle.getString("cinClient"));
            client.setTel(bundle.getString("telClient"));

            initComponents();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initComponents() {
        final EditText emailClient = (EditText) findViewById(R.id.newEmailClient);
        final EditText mdpClient = (EditText) findViewById(R.id.newMdpClient);
        final EditText cmdpClient = (EditText) findViewById(R.id.newCmdpClient);
        Button inscrireButton = (Button) findViewById(R.id.inscrireButton);
        inscrireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("_______________huhu123");
                try {
                    if (TextUtils.isEmpty(emailClient.getText().toString())) {
                        emailClient.setError("Requis");
                        throw new Exception("Login vide");
                    }
                    if (TextUtils.isEmpty(mdpClient.getText().toString())) {
                        mdpClient.setError("Requis");
                        throw new Exception("Mot de passe vide");
                    }
                    if (TextUtils.isEmpty(cmdpClient.getText().toString())) {
                        cmdpClient.setError("Requis");
                        throw new Exception("Confirmation du mot de passe non valide");
                    }
                    if (mdpClient.getText().toString().compareTo(cmdpClient.getText().toString()) != 0) {
                        mdpClient.setError("");
                        mdpClient.setError("");
                        throw new Exception("Mots de passe non identiques");
                    }

                    client.setEmail(emailClient.getText().toString());
                    client.setMdp(mdpClient.getText().toString());
                    ClType typeClient = new ClType();
                    typeClient.setId(1);
                    Agence agenceClient = new Agence();
                    agenceClient.setId(1);
                    client.setClType(typeClient);
                    client.setAgence(agenceClient);


                    InscriptionClientAsync inscriptionClientAsync = new InscriptionClientAsync();
                    inscriptionClientAsync.setActivity(thisActivity);
                    inscriptionClientAsync.setIdSouscripteur(1);
                    Client[]  params = new Client[1];
                    params[0] = client;

                    inscriptionClientAsync.execute(params);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(thisActivity.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
