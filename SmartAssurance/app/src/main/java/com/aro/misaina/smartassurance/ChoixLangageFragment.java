package com.aro.misaina.smartassurance;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import sqlite.LangueDao;
import utilitaire.LocaleHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoixLangageFragment extends DialogFragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private ParametresFragment parametresFragment;

    public ChoixLangageFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_choix_langage, null);

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(this);

        // buttons Cancel and OK
        builder.setView(view)

                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChoixLangageFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }


    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    public ParametresFragment getParametresFragment() {
        return parametresFragment;
    }

    public void setParametresFragment(ParametresFragment parametresFragment) {
        this.parametresFragment = parametresFragment;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        LangueDao langueDao = new LangueDao(this.getActivity());
        try {
            if (checkedId == R.id.radioEn) {
                langueDao.setLangue("en");
            } else if (checkedId == R.id.radioMg) {
                langueDao.setLangue("mg");
            } else if (checkedId == R.id.radioFr) {
                langueDao.setLangue("fr");
            }
            Toast.makeText(getActivity(), langueDao.getCurrentLangue(), Toast.LENGTH_SHORT).show();
            LocaleHelper localeHelper = new LocaleHelper(getActivity());
            localeHelper.setLocale();

            Intent inte= new Intent(getActivity(), AccueilActivity.class);
            inte.putExtra("tabid", 4);
            getActivity().startActivity(inte);

            ChoixLangageFragment.this.getDialog().cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
