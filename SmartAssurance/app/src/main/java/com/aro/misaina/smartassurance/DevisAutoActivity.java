package com.aro.misaina.smartassurance;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import async.automoto.DevisAsync;
import modeles.AmGaranti;
import modeles.client.ClientView;
import modeles.souscription.SaisiGaranti;
import modeles.souscription.VehiculeWS;
import services.AutoMotoService;
import services.ObjetsStatique;
import services.SessionManager;

public class DevisAutoActivity extends DialogFragment {

    private BotFragment botFragment;
    private Spinner spinMois;
    private Spinner spinUsage;
    private Spinner spinSe;
    private EditText ePf;
    private EditText eNbPlace;
    private EditText eNbRoues;
    private LinearLayout contentGaranties;

    private List<EditText> eListGaranties;
    private List<CheckBox> chBoxGaranties;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_devis_auto, null);

        contentGaranties = (LinearLayout) view.findViewById(R.id.contentGaranties);
        ePf = (EditText) view.findViewById(R.id.ePf);
        eNbRoues = (EditText) view.findViewById(R.id.eNbRoues);
        eNbPlace = (EditText) view.findViewById(R.id.eNbPlace);
        spinSe = (Spinner) view.findViewById(R.id.spinSe);
        spinMois = (Spinner) view.findViewById(R.id.spinMois);
        spinUsage = (Spinner) view.findViewById(R.id.spinUsage);

        List<String> es = new ArrayList<String>(2);
        es.add("Essence");
        es.add("Diesel");

        ArrayAdapter<String> adapterDrOption = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, es);

        ArrayAdapter<String> adapterDrUsage = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, ObjetsStatique.getUsagesVehicule());
        spinSe.setAdapter(adapterDrOption);
        spinUsage.setAdapter(adapterDrUsage);

        List<String> mois = new ArrayList<String>(12);
        for (int i = 1; i <= 12; i++) {
            mois.add(i + "");
        }

        ArrayAdapter<String> adapterMois = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, mois);
        spinMois.setAdapter(adapterMois);

//        initialisation des saisis de garanties
        initCheckBoxGaranties();
        initSaisiGaraties();
        for (int i = 0; i < ObjetsStatique.getGaranties().size(); i++) {
            final EditText editTextGarantie = eListGaranties.get(i);
            final CheckBox checkBoxGarantie = chBoxGaranties.get(i);

            final AmGaranti garanti = ObjetsStatique.getGaranties().get(i);
            checkBoxGarantie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBoxGarantie.isChecked()) {
                        editTextGarantie.setText(garanti.getLimiteMin().toString());
                    } else {
                        editTextGarantie.setText("");
                    }
                    editTextGarantie.setEnabled(checkBoxGarantie.isChecked());
                }
            });
            editTextGarantie.setEnabled(checkBoxGarantie.isChecked());
            editTextGarantie.setWidth(200);

            if (checkBoxGarantie.isChecked()) {
                editTextGarantie.setText(garanti.getLimiteMin().toString());
            }
            TextInputLayout textInputLayout = new TextInputLayout(getBotFragment().getActivity());
            textInputLayout.addView(editTextGarantie);
            textInputLayout.setMinimumWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            textInputLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            LinearLayout linearLayout = new LinearLayout(getBotFragment().getActivity());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(checkBoxGarantie);
            linearLayout.addView(textInputLayout);


            contentGaranties.addView(linearLayout);
        }
        // buttons Cancel and OK
        builder.setView(view)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            valider();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DevisAutoActivity.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    public void valider() throws Exception{
        try {
            VehiculeWS vehiculeWS = new VehiculeWS();
            vehiculeWS.setNbPlaces(new Integer(eNbPlace.getText().toString()));
            vehiculeWS.setPuissance(new Integer(ePf.getText().toString()));
            vehiculeWS.setSe(spinSe.getSelectedItem().toString());
            vehiculeWS.setIdUsage(spinUsage.getSelectedItemPosition() + 1);
            vehiculeWS.setNbRoues(new Integer(eNbRoues.getText().toString()));
            SessionManager sessionManager = new SessionManager(botFragment.getActivity().getApplicationContext());
            vehiculeWS.setIdClient(((ClientView) SessionManager.getClientConnected()).getId());

            AutoMotoService autoMotoService = new AutoMotoService();
            List<AmGaranti> listeGaranties = autoMotoService.getGaranties();
            List<SaisiGaranti> listeGarantiesVehicu = new ArrayList<SaisiGaranti>();


            for (int i = 0; i < eListGaranties.size(); i++) {
                EditText etextGaranties = eListGaranties.get(i);
                if (chBoxGaranties.get(i).isChecked()) {
                    SaisiGaranti gar = new SaisiGaranti();
                    gar.setId(listeGaranties.get(i).getId());
                    gar.setLibelle(listeGaranties.get(i).getLibelle());
                    gar.setCode(listeGaranties.get(i).getCode());
                    Double valSaisie = new Double(etextGaranties.getText().toString());
                    if (valSaisie < ObjetsStatique.getGaranties().get(i).getLimiteMin()) {

                    }
                    else {
                        gar.setLimite(valSaisie);
                    }
                    listeGarantiesVehicu.add(gar);
                }
            }
            vehiculeWS.setGaranties(listeGarantiesVehicu);
            DevisAsync async = new DevisAsync();
            async.setBotFragment(getBotFragment());
            async.setNbMois(new Integer(spinMois.getSelectedItem().toString()));
            VehiculeWS[] param = new VehiculeWS[1];
            param[0] = vehiculeWS;
            async.execute(param);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void initSaisiGaraties() {
        AutoMotoService autoMotoService = new AutoMotoService();
        List<AmGaranti> listeGaranties = autoMotoService.getGaranties();
        eListGaranties = new ArrayList<EditText>(listeGaranties.size());
        for (AmGaranti garanti : listeGaranties) {
            EditText editText = new EditText(getBotFragment().getActivity());
            editText.setHint(garanti.getLibelle());
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            editText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            eListGaranties.add(editText);
        }
    }

    public void initCheckBoxGaranties() {
        chBoxGaranties = new ArrayList<CheckBox>(ObjetsStatique.getGaranties().size());
        for (int i = 0; i < ObjetsStatique.getGaranties().size(); i++) {
            CheckBox checkBox = new CheckBox(getBotFragment().getActivity());
            checkBox.setSelected(false);
            if (i == 0) {

                checkBox.setChecked(true);
                checkBox.setEnabled(false);
            }
            chBoxGaranties.add(checkBox);
        }
    }

    public BotFragment getBotFragment() {
        return botFragment;
    }

    public void setBotFragment(BotFragment botFragment) {
        this.botFragment = botFragment;
    }
}
