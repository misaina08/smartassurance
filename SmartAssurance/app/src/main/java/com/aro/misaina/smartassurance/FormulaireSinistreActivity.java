package com.aro.misaina.smartassurance;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import async.automoto.CreerSinistreAsync;
import modeles.automoto.AmSinistreView;
import modeles.automoto.SinCategorie;
import services.ObjetsStatique;
import services.SessionManager;
import utilitaire.Coordonnee;
import utilitaire.Util;

public class FormulaireSinistreActivity extends AppCompatActivity {
    private Spinner sCategorie;
    private EditText eLieu;
    private EditText eConducteur;
    private EditText eNomCond;
    private EditText ePrenomCond;
    private EditText eDn;
    private EditText eNopermis;
    private EditText eDuplic;
    private EditText eCatPermis;
    private EditText eCatvalidees;
    private EditText eDebValidite;
    private EditText eFinValidite;
    private EditText eDateDeliv;
    private EditText eAdresse;
    private EditText eLieuDelivrance;
    private Button bCurrentPos;

    private String datajson;
    private Coordonnee currPos;

    FormulaireSinistreActivity activity;
    Integer idsouscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_sinistre);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.sinistre);

        idsouscription = getIntent().getExtras().getInt("idsouscription");
        datajson = getIntent().getExtras().getString("datajson");
        activity = this;
        initData();
    }

    @Override
    public boolean onSupportNavigateUp(){
        System.out.println("precedent");
        finish();
        return true;
    }

    public void initData() {
        currPos = new Coordonnee();
        sCategorie = (Spinner) findViewById(R.id.sCategorie);
        eLieu = (EditText) findViewById(R.id.eLieu);
        eConducteur = (EditText) findViewById(R.id.eConducteur);
        eNomCond = (EditText) findViewById(R.id.eNomCond);
        ePrenomCond = (EditText) findViewById(R.id.ePrenomCond);
        eDn = (EditText) findViewById(R.id.eDn);
        eNopermis = (EditText) findViewById(R.id.eNopermis);
        eDuplic = (EditText) findViewById(R.id.eDuplic);
        eCatPermis = (EditText) findViewById(R.id.eCatPermis);
        eCatvalidees = (EditText) findViewById(R.id.eCatvalidees);
        eDebValidite = (EditText) findViewById(R.id.eDebValidite);
        eFinValidite = (EditText) findViewById(R.id.eFinValidite);
        eDateDeliv = (EditText) findViewById(R.id.eDateDeliv);
        eLieuDelivrance = (EditText) findViewById(R.id.eLieuDelivrance);
        eAdresse = (EditText) findViewById(R.id.eAdresse);
        bCurrentPos = (Button) findViewById(R.id.bCurrentPos);

        bCurrentPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FusedLocationProviderClient mFusedLocationClient;
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("no permissions");
                }
                mFusedLocationClient.getLastLocation()
                        .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
//                                    Coordonnees de la position actuelle
                                    currPos.setLatitude(location.getLatitude());
                                    currPos.setLongitude(location.getLongitude());
                                    Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
                                    List<Address> addresses = null;
                                    try {
                                        addresses = geocoder.getFromLocation(
                                                location.getLatitude(),
                                                location.getLongitude(),
                                                // In this sample, get just a single address.
                                                1);
//                                         nom du lieu : Rue, Ville, Pays
                                        String cityName = addresses.get(0).getAddressLine(0);
                                        eLieu.setText(cityName);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
            }
        });
        eDn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(eDn);
            }
        });
        eDebValidite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(eDebValidite);
            }
        });
        eFinValidite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(eFinValidite);
            }
        });
        eDateDeliv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(eDateDeliv);
            }
        });

        List<String> catString = new ArrayList<String>(ObjetsStatique.getSinCategories().size());
        for (SinCategorie s : ObjetsStatique.getSinCategories()) {
            catString.add(s.toString());
        }
        ArrayAdapter<String> adapterCategorie = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, catString);
        sCategorie.setAdapter(adapterCategorie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulaire_sinistre, menu);

        MenuItem createMenu = (MenuItem) menu.findItem(R.id.menuValiderCreationSinistre);
        createMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                try {

                    AmSinistreView sinistre = new AmSinistreView();
                    sinistre.setLatitude(currPos.getLatitude());
                    sinistre.setLongitude(currPos.getLongitude());
                    sinistre.setAdresseConducteur(eAdresse.getText().toString());
                    sinistre.setCatPermis(eCatPermis.getText().toString());
                    sinistre.setCatValidees(eCatvalidees.getText().toString());
                    sinistre.setConducteur(eConducteur.getText().toString());
                    sinistre.setDateDelivrance(Util.stringToDate(eDateDeliv.getText().toString()));
                    sinistre.setDebValidite(Util.stringToDate(eDebValidite.getText().toString()));
                    sinistre.setFinValidite(Util.stringToDate(eFinValidite.getText().toString()));
                    sinistre.setDemande(0);
                    sinistre.setDnConducteur(Util.stringToDate(eDn.getText().toString()));
                    sinistre.setLieu(eLieu.getText().toString());
                    sinistre.setLieuDelivrance(eLieuDelivrance.getText().toString());
                    sinistre.setNoduplicata(eDuplic.getText().toString());
                    sinistre.setNopermis(eNopermis.getText().toString());
                    sinistre.setNomConducteur(eNomCond.getText().toString());
                    sinistre.setPrenomConducteur(ePrenomCond.getText().toString());
                    sinistre.setTermine(0);
                    sinistre.setSinCategorieId(ObjetsStatique.getSinCategories().get(sCategorie.getSelectedItemPosition()).getId());
                    sinistre.setSouscriptionProduitId(idsouscription);

                    CreerSinistreAsync async = new CreerSinistreAsync();
                    async.setActivity(activity);
                    async.setDatajson(datajson);
                    AmSinistreView[] params = new AmSinistreView[1];
                    params[0] = sinistre;
                    async.execute(sinistre);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        });
        SessionManager sessionManager = new SessionManager(this.getApplicationContext());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        return true;
    }
    public void showDatePickerDialog(EditText toFill) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setEditTextToFill(toFill);
        newFragment.show(getFragmentManager().beginTransaction(), "Date");
    }

}
