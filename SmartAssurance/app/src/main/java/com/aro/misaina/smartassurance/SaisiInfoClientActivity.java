package com.aro.misaina.smartassurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SaisiInfoClientActivity extends AppCompatActivity {

    private SaisiInfoClientActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisiinfo_client);
        getSupportActionBar().setTitle(R.string.inscription);
        thisActivity = this;
        // initialisation des composants
        initComponents();
    }

    public void initComponents() {
        final EditText nomClient = (EditText) findViewById(R.id.newNomClient);
        final EditText prenomClient = (EditText) findViewById(R.id.newPrenomClient);
        final EditText adresseClient = (EditText) findViewById(R.id.newAdresseClient);
        final EditText professionClient = (EditText) findViewById(R.id.newProfessionClient);
        final EditText dateNaissClient = (EditText) findViewById(R.id.newDateNaissClient);
        final EditText lieuNaissClient = (EditText) findViewById(R.id.newLieuNaissClient);
        final EditText cinClient = (EditText) findViewById(R.id.newCinClient);
        final EditText telClient = (EditText) findViewById(R.id.newTelClient);
        final Button suivantButton = (Button) findViewById(R.id.suivantButton);

        dateNaissClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(dateNaissClient);
            }
        });

        suivantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent intent = new Intent(thisActivity, SaisiIdentifierClientActivity.class);
                    intent.putExtra("nomClient", nomClient.getText().toString());
                    intent.putExtra("prenomClient", prenomClient.getText().toString());
                    intent.putExtra("adresseClient", adresseClient.getText().toString());
                    intent.putExtra("professionClient", professionClient.getText().toString());
                    intent.putExtra("dateNaissClient", dateNaissClient.getText().toString());
                    intent.putExtra("lieuNaissClient", lieuNaissClient.getText().toString());
                    intent.putExtra("cinClient", cinClient.getText().toString());
                    intent.putExtra("telClient", telClient.getText().toString());

                    thisActivity.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showDatePickerDialog(EditText toFill) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setEditTextToFill(toFill);
        newFragment.show(getFragmentManager().beginTransaction(), "Date");
    }
}
