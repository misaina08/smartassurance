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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import async.souscription.SouscriptionAutoMotoAsync;
import modeles.AmGaranti;
import modeles.client.ClientView;
import modeles.souscription.SaisiGaranti;
import modeles.souscription.VehiculeWS;
import services.AutoMotoService;
import services.SessionManager;
import utilitaire.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class SRAutoMotoPopFragment extends DialogFragment {
    private BotFragment botFragment;

    private LinearLayout content;
    private EditText eNoImmatr;
    private EditText eNoSerie;
    private EditText eMarque;
    private Spinner spSe;
    private EditText eDateCirc;
    private EditText ePuissance;
    private EditText eNbRoues;
    private EditText eNbPlace;
    private List<EditText> eListGaranties;

    public SRAutoMotoPopFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_srauto_moto_pop, null);

        content = (LinearLayout) view.findViewById(R.id.content);
        eNoImmatr = (EditText) view.findViewById(R.id.eNoImmatr);
        eNoSerie = (EditText) view.findViewById(R.id.eNoSerie);
        eMarque = (EditText) view.findViewById(R.id.eMarque);
        eDateCirc = (EditText) view.findViewById(R.id.eDateCirc);
        ePuissance = (EditText) view.findViewById(R.id.ePuissance);
        eNbRoues = (EditText) view.findViewById(R.id.eNbRoues);
        eNbPlace = (EditText) view.findViewById(R.id.eNbPlace);
        spSe = (Spinner) view.findViewById(R.id.spSe);

        List<String> es = new ArrayList<String>(2);
        es.add("Essence");
        es.add("Diesel");

        ArrayAdapter<String> adapterDrOption = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, es);

        spSe.setAdapter(adapterDrOption);

//        initialisation des saisis de garanties
        initSaisiGaraties();
        for (EditText editTextGarantie : eListGaranties) {
            TextInputLayout textInputLayout = new TextInputLayout(getBotFragment().getActivity());
            textInputLayout.addView(editTextGarantie);
            content.addView(textInputLayout);
        }
        // buttons Cancel and OK
        builder.setView(view)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            souscrire();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SRAutoMotoPopFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    public void souscrire() throws Exception {
        try {
            VehiculeWS vehiculeWS = new VehiculeWS();
            vehiculeWS.setDateCirculation(Util.stringToDate(eDateCirc.getText().toString()));
            vehiculeWS.setMarque(eMarque.getText().toString());
            vehiculeWS.setType(eMarque.getText().toString());
            vehiculeWS.setNbPlaces(new Integer(eNbPlace.getText().toString()));
            vehiculeWS.setPuissance(new Integer(ePuissance.getText().toString()));
            vehiculeWS.setSe(spSe.getSelectedItem().toString());
            vehiculeWS.setNbRoues(new Integer(eNbRoues.getText().toString()));
            vehiculeWS.setNoSerie(eNoSerie.getText().toString());
            vehiculeWS.setNoImm(eNoImmatr.getText().toString());
            SessionManager sessionManager = new SessionManager(botFragment.getActivity().getApplicationContext());
            vehiculeWS.setIdClient(((ClientView) SessionManager.getClientConnected()).getId());

            AutoMotoService autoMotoService = new AutoMotoService();
            List<AmGaranti> listeGaranties = autoMotoService.getGaranties();
            List<SaisiGaranti> listeGarantiesVehicu = new ArrayList<SaisiGaranti>();

            int i = 0;
            for (EditText etextGaranties : eListGaranties) {
//                System.out.println("/"+etextGaranties.getText().toString()+"-");
                if (!etextGaranties.getText().toString().isEmpty()) {
                    SaisiGaranti gar = new SaisiGaranti();
                    gar.setId(listeGaranties.get(i).getId());
                    gar.setLibelle(listeGaranties.get(i).getLibelle());
                    gar.setCode(listeGaranties.get(i).getCode());
                    gar.setLimite(new Double(etextGaranties.getText().toString()));
                    listeGarantiesVehicu.add(gar);
                }
                i++;
            }
            vehiculeWS.setGaranties(listeGarantiesVehicu);
            SouscriptionAutoMotoAsync async = new SouscriptionAutoMotoAsync();
            async.setBotFragment(getBotFragment());
            async.setNbMois(12);
            VehiculeWS[] param = new VehiculeWS[1];
            param[0] = vehiculeWS;
            async.execute(param);

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Création des saisis de garanties
     */
    public void initSaisiGaraties() {
        AutoMotoService autoMotoService = new AutoMotoService();
        List<AmGaranti> listeGaranties = autoMotoService.getGaranties();
        eListGaranties = new ArrayList<EditText>(listeGaranties.size());
        for (AmGaranti garanti : listeGaranties) {
            EditText editText = new EditText(getBotFragment().getActivity());
            editText.setHint(garanti.getLibelle());
            eListGaranties.add(editText);
        }
    }

    public BotFragment getBotFragment() {
        return botFragment;
    }

    public void setBotFragment(BotFragment botFragment) {
        this.botFragment = botFragment;
    }
}
