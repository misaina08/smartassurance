package com.aro.misaina.smartassurance;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Date;

import async.souscription.PayerContratAsync;
import modeles.paiement.HistoriquePaiementView;
import modeles.paiement.MoyensPaiementClientView;
import modeles.souscription.Souscription;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResumePaiementContratFragment extends DialogFragment {
    private CheckBox cAccept;
    private EditText eCodeSecret;
    private EditText eMtDepot;
    private TextView vContrat;
    private TextInputLayout tinput;
    private TextInputLayout mtInput;
    private Souscription souscription;
    private MoyensPaiementClientView paiementClientView;
    ResumePaiementContratFragment fragment;

    public ResumePaiementContratFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_resume_paiement_contrat, null);
        Gson gson = new Gson();
        souscription = gson.fromJson(getArguments().getString("dataJson"), Souscription.class);
        paiementClientView = gson.fromJson(getArguments().getString("moyenPaiementJson"), MoyensPaiementClientView.class);
        initComponents(view);
        fragment = this;

        builder.setView(view)
                .setPositiveButton("Valider le paiement", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            PayerContratAsync async = new PayerContratAsync();
                            async.setContext(fragment.getActivity().getApplicationContext());
                            async.setSouscription(souscription);
                            HistoriquePaiementView[] params = new HistoriquePaiementView[1];
                            HistoriquePaiementView historique = new HistoriquePaiementView();
                            historique.setIdSouscription(souscription.getId());
                            historique.setDaty(souscription.getDateSouscription());
                            historique.setDateOperation(new Date());
                            historique.setIdMoyenPaiementClient(paiementClientView.getId());
                            historique.setMt(souscription.getPrimetotal());
                            historique.setMotif("Paiement de la souscription au produit " + souscription.getNomProduit());
                            historique.setIdProduit(souscription.getIdProduit());
                            if (souscription.getIdProduit() == 3) {
                                historique.setMt(new Double(eMtDepot.getText().toString()));
                            }
                            params[0] = historique;
                            async.execute(params);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ResumePaiementContratFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    public void initComponents(View view) {
        cAccept = (CheckBox) view.findViewById(R.id.cAccept);
        eCodeSecret = (EditText) view.findViewById(R.id.eCodeSecret);
        eMtDepot = (EditText) view.findViewById(R.id.eMtDepot);
        vContrat = (TextView) view.findViewById(R.id.vContrat);
        tinput = (TextInputLayout) view.findViewById(R.id.tInputLayout);
        mtInput = (TextInputLayout) view.findViewById(R.id.mtInput);
        cAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cAccept.isChecked()) {
                    tinput.setVisibility(View.VISIBLE);
                    if (souscription.getIdProduit() == 3) {
                        mtInput.setVisibility(View.VISIBLE);
                    }
                } else {
                    tinput.setVisibility(View.INVISIBLE);
                    mtInput.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

}
