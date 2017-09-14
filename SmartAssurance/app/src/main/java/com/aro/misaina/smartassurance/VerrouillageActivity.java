package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VerrouillageActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bDeverrouiller;
    private EditText eCodeSecret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verrouillage);

        bDeverrouiller = (Button) findViewById(R.id.bDeverrouiller);
        eCodeSecret = (EditText) findViewById(R.id.eCodeSecret);

        bDeverrouiller.setOnClickListener(this);
    }

    public Button getbDeverrouiller() {
        return bDeverrouiller;
    }

    public void setbDeverrouiller(Button bDeverrouiller) {
        this.bDeverrouiller = bDeverrouiller;
    }

    public EditText geteCodeSecret() {
        return eCodeSecret;
    }

    public void seteCodeSecret(EditText eCodeSecret) {
        this.eCodeSecret = eCodeSecret;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        System.out.println("onclick");
        if (id == R.id.bDeverrouiller) {
            System.out.println("onclick dev");
            this.finish();
        }
    }
}
