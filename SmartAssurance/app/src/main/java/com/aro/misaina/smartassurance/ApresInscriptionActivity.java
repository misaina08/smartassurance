package com.aro.misaina.smartassurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ApresInscriptionActivity extends AppCompatActivity {
    private TextView nomClient;
    private ApresInscriptionActivity thisActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apres_inscription);

        thisActivity = this;
        Bundle bundle = getIntent().getExtras();
        initComponents();
        nomClient.setText(bundle.getString("nomCompletClient"));
    }

    public void initComponents(){
        nomClient = (TextView)findViewById(R.id.nomClientInscrit);
        Button button = (Button)findViewById(R.id.buttonApresInscription);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, ConnexionActivity.class);
                thisActivity.startActivity(intent);
            }
        });

    }

    public ApresInscriptionActivity getThisActivity() {
        return thisActivity;
    }

    public void setThisActivity(ApresInscriptionActivity thisActivity) {
        this.thisActivity = thisActivity;
    }
}
