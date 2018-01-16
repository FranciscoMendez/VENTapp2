package com.example.francisco.ventapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import modelo.Propiedad;

public class propiedadMapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propiedad_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        importarInformacion();
    }

    public void importarInformacion() {
        Intent startingIntent = getIntent();
        if (startingIntent != null) {
            Bundle b = startingIntent.getBundleExtra("android.intent.extra.INTENT");
            String lat = (String) getIntent().getSerializableExtra("propiedadLat");
            String longitud = (String) getIntent().getSerializableExtra("propiedadLong");
            String nombreProp = (String) getIntent().getSerializableExtra("propiedadNombre");
            LatLng localizacion = new LatLng(Double.parseDouble(lat),Double.parseDouble(longitud));
            mMap.addMarker(new MarkerOptions().position(localizacion).title(nombreProp));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));

        }
    }

}

