package com.guelar.multimedia;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActividad11_9 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap miMapa;
    private final LatLng miDomicilio = new LatLng(40.55198, -3.90554);

    private final LatLng miCentroEstudios = new LatLng(40.44088, -3.71107);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_9);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        miMapa = googleMap;

        miMapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        miMapa.getUiSettings().setZoomControlsEnabled(true);
        miMapa.getUiSettings().setCompassEnabled(true);

        miMapa.addMarker(new MarkerOptions()
                .position(miDomicilio)
                .title("Mi Domicilio")
                .snippet("Aquí es donde vivo")
                .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_RED)));

        miMapa.addMarker(new MarkerOptions()
                .position(miCentroEstudios)
                .title("Mi Centro de Estudios")
                .snippet("Aquí estudio DAM")
                .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_BLUE)));

        miMapa.addPolyline(new PolylineOptions()
                .add(miDomicilio, miCentroEstudios)
                .color(Color.RED)
                .width(8f));

        LatLng puntoMedio = new LatLng(
                (miDomicilio.latitude  + miCentroEstudios.latitude)  / 2,
                (miDomicilio.longitude + miCentroEstudios.longitude) / 2);

        miMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(puntoMedio, 12f));

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            CameraPosition posicionFinal = new CameraPosition.Builder()
                    .target(miDomicilio)
                    .zoom(17f)
                    .tilt(45f)
                    .build();

            miMapa.animateCamera(
                    CameraUpdateFactory.newCameraPosition(posicionFinal),
                    2000,
                    null);
        }, 2000);
    }
}