package com.aro.misaina.smartassurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import async.automoto.InfoSinistreAsync;
import modeles.automoto.AmSinistreView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabInfoSinistreFragment extends Fragment {
    AmSinistreView sinistreView;
    private TextView tConducteur;
    private TextView tNom;
    private TextView tPrenom;
    private TextView tDn;
    private TextView tAdresse;
    private TextView tNo;
    private TextView tDupl;
    private TextView tCats;
    private TextView tCatValidees;
    private TextView tValDeb;
    private TextView tValFin;
    private TextView tDateDeliv;
    private TextView tLieuDeliv;

    public TabInfoSinistreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle b = getArguments();
        Gson gson = new Gson();
        sinistreView = gson.fromJson(b.getString("dataJson"), AmSinistreView.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_info_sinistre, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle b) {
        super.onViewCreated(v, b);
        tConducteur = (TextView) getView().findViewById(R.id.tConducteur);
        tNom = (TextView) getView().findViewById(R.id.tNom);
        tPrenom = (TextView) getView().findViewById(R.id.tPrenom);
        tDn = (TextView) getView().findViewById(R.id.tDn);
        tAdresse = (TextView) getView().findViewById(R.id.tAdresse);
        tNo = (TextView) getView().findViewById(R.id.tNo);
        tDupl = (TextView) getView().findViewById(R.id.tDupl);
        tCats = (TextView) getView().findViewById(R.id.tCats);
        tCatValidees = (TextView) getView().findViewById(R.id.tCatValidees);
        tValDeb = (TextView) getView().findViewById(R.id.tValDeb);
        tValFin = (TextView) getView().findViewById(R.id.tValFin);
        tDateDeliv = (TextView) getView().findViewById(R.id.tDateDeliv);
        tLieuDeliv = (TextView) getView().findViewById(R.id.tLieuDeliv);

        Integer[] params = new Integer[1];
        params[0] = sinistreView.getId();
        InfoSinistreAsync async = new InfoSinistreAsync();
        async.setFragment(this);
        async.execute(params);
    }

    public TextView gettConducteur() {
        return tConducteur;
    }

    public void settConducteur(TextView tConducteur) {
        this.tConducteur = tConducteur;
    }

    public TextView gettNom() {
        return tNom;
    }

    public void settNom(TextView tNom) {
        this.tNom = tNom;
    }

    public TextView gettPrenom() {
        return tPrenom;
    }

    public void settPrenom(TextView tPrenom) {
        this.tPrenom = tPrenom;
    }

    public TextView gettDn() {
        return tDn;
    }

    public void settDn(TextView tDn) {
        this.tDn = tDn;
    }

    public TextView gettAdresse() {
        return tAdresse;
    }

    public void settAdresse(TextView tAdresse) {
        this.tAdresse = tAdresse;
    }

    public TextView gettNo() {
        return tNo;
    }

    public void settNo(TextView tNo) {
        this.tNo = tNo;
    }

    public TextView gettDupl() {
        return tDupl;
    }

    public void settDupl(TextView tDupl) {
        this.tDupl = tDupl;
    }

    public TextView gettCats() {
        return tCats;
    }

    public void settCats(TextView tCats) {
        this.tCats = tCats;
    }

    public TextView gettCatValidees() {
        return tCatValidees;
    }

    public void settCatValidees(TextView tCatValidees) {
        this.tCatValidees = tCatValidees;
    }

    public TextView gettValDeb() {
        return tValDeb;
    }

    public void settValDeb(TextView tValDeb) {
        this.tValDeb = tValDeb;
    }

    public TextView gettValFin() {
        return tValFin;
    }

    public void settValFin(TextView tValFin) {
        this.tValFin = tValFin;
    }

    public TextView gettDateDeliv() {
        return tDateDeliv;
    }

    public void settDateDeliv(TextView tDateDeliv) {
        this.tDateDeliv = tDateDeliv;
    }

    public TextView gettLieuDeliv() {
        return tLieuDeliv;
    }

    public void settLieuDeliv(TextView tLieuDeliv) {
        this.tLieuDeliv = tLieuDeliv;
    }
}
