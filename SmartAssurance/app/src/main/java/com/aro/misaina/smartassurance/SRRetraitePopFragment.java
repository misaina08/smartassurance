package com.aro.misaina.smartassurance;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import async.souscription.SouscriptionRetraiteAsync;
import modeles.client.ClientView;
import modeles.souscription.RtSouscription;
import services.ObjetsStatique;
import services.SessionManager;


/**
 *
 */
public class SRRetraitePopFragment extends DialogFragment {
    private BotFragment botFragment;

    private EditText eAgeRetraite;
    private EditText eBeneficiaire;
    private EditText eMt;
    private Spinner drOption;
    private Spinner drType;
    private Spinner drCotisation;
    private Button bSouscrireRetraite;
    private Button bAnnuler;

    public SRRetraitePopFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_srretraite_pop, null);

        eAgeRetraite = (EditText)view.findViewById(R.id.eAgeRetraite);
        eBeneficiaire = (EditText)view.findViewById(R.id.eBeneficiaire);
        eMt = (EditText)view.findViewById(R.id.eMt);
        drOption = (Spinner)view.findViewById(R.id.drOption);
        drType = (Spinner)view.findViewById(R.id.drType);
        drCotisation = (Spinner)view.findViewById(R.id.drCotisation);

        ArrayAdapter<String> adapterDrOption = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, ObjetsStatique.getRtOptions());
        ArrayAdapter<String> adapterDrType = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, ObjetsStatique.getRtTypes());
        ArrayAdapter<String> adapterDrCotisation = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, ObjetsStatique.getRtCotisations());

        drType.setAdapter(adapterDrType);
        drCotisation.setAdapter(adapterDrCotisation);
        drOption.setAdapter(adapterDrOption);

        // buttons Cancel and OK
        builder.setView(view)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        try{
                            onClickSouscrire();
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SRRetraitePopFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    /**
     * Souscription à la retraite
     */
    public void onClickSouscrire() throws Exception{

        try {
            RtSouscription rtSouscription = new RtSouscription();
            rtSouscription.setAgeRetraite(new Integer(eAgeRetraite.getText().toString()));
            rtSouscription.setBeneficiare(eBeneficiaire.getText().toString());
            rtSouscription.setIdCotisation(1);
            rtSouscription.setIdOption(1);
            rtSouscription.setIdType(1);
            rtSouscription.setDateSouscription(new Date());
            rtSouscription.setDuree(12);
            rtSouscription.setPrimetotal(new Double(eMt.getText().toString()));
            rtSouscription.setValide(0);
            rtSouscription.setIdProduit(3);
            SessionManager sessionManager = new SessionManager(botFragment.getActivity().getApplicationContext());
            ClientView cl = (ClientView) SessionManager.getClientConnected();
            rtSouscription.setIdClient(cl.getId());
            rtSouscription.setIdClientSouscripteur(cl.getId());

            SouscriptionRetraiteAsync souscriptionAsync = new SouscriptionRetraiteAsync();
            souscriptionAsync.setBotFragment(getBotFragment());
            RtSouscription[] params = new RtSouscription[1];
            params[0] = rtSouscription;
            souscriptionAsync.execute(params);
        } catch (NumberFormatException e){
            throw e;
        }
        catch (Exception e) {
            throw e;
        }

    }

    public BotFragment getBotFragment() {
        return botFragment;
    }

    public void setBotFragment(BotFragment botFragment) {
        this.botFragment = botFragment;
    }
}
