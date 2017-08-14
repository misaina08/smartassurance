package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import modeles.Agence;
import services.ObjetsStatique;

public class MapAgenceActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_agence);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker on all Agency
        for(Agence agence : ObjetsStatique.getAgences()){
            LatLng posAgence = new LatLng(agence.getLatitude(), agence.getLongitude());
            mMap.addMarker(new MarkerOptions().position(posAgence).title(agence.getNom()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(posAgence));
        }

    }
}
