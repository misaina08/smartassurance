package com.aro.misaina.smartassurance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

import modeles.Agence;
import services.ObjetsStatique;
import utilitaire.Coordonnee;

public class MapAgenceActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {
    private GoogleMap mMap;
    private Coordonnee currPos;
    private Agence agenceDest;
    LatLng latLngCurrentPos;
    LatLng latLngAgenceDest;
    private Button bToutAgences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_agence);
        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        currPos = gson.fromJson(bundle.getString("currPos"), Coordonnee.class);
        agenceDest = gson.fromJson(bundle.getString("agenceDest"), Agence.class);

        bToutAgences = (Button) findViewById(R.id.bToutAgences);
        bToutAgences.setOnClickListener(this);

        latLngCurrentPos = new LatLng(currPos.getLatitude(), currPos.getLongitude());
        latLngAgenceDest = new LatLng(agenceDest.getLatitude(), agenceDest.getLongitude());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        requestDirection();
    }

    public void requestDirection() {
        try {
            Toast.makeText(this, "Itinéraire en cours...", Toast.LENGTH_SHORT).show();
            GoogleDirection.withServerKey("AIzaSyDKD4SS2pCLwr-bD1Xz_Ru0_7elsmHHMzU")
                    .from(latLngCurrentPos)
                    .to(latLngAgenceDest)
                    .transportMode(TransportMode.DRIVING)
                    .execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        try {
//            Toast.makeText(this, "Success with status : " + direction.getStatus(), Toast.LENGTH_SHORT).show();
            if (direction.isOK()) {
                mMap.addMarker(new MarkerOptions().position(latLngCurrentPos).title("Moi"));
                mMap.addMarker(new MarkerOptions().position(latLngAgenceDest).title(agenceDest.getNom()));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));

                ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
                mMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 2, Color.RED));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngAgenceDest));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        try {
            Toast.makeText(this, "Faillure with status : " + t.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bToutAgences){
            afficherToutAgences();
        }
    }

    public void afficherToutAgences(){
        for(Agence agence : ObjetsStatique.getAgences()){
            LatLng posAgence = new LatLng(agence.getLatitude(), agence.getLongitude());
            mMap.addMarker(new MarkerOptions().position(posAgence).title(agence.getNom()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(posAgence));
        }
    }
}
