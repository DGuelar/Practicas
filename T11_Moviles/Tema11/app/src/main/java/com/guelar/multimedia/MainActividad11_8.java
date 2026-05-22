package com.guelar.multimedia;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActividad11_8 extends AppCompatActivity implements LocationListener {

    private static final int CODIGO_PERMISOS = 101;

    private TextView lblLatitud, lblLongitud, lblPrecision;
    private TextView lblVelocidad, lblDireccion;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_8);

        lblLatitud   = findViewById(R.id.lblLatitud);
        lblLongitud  = findViewById(R.id.lblLongitud);
        lblPrecision = findViewById(R.id.lblPrecision);
        lblVelocidad = findViewById(R.id.lblVelocidad);
        lblDireccion = findViewById(R.id.lblDireccion);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        verificarPermisosYComenzar();
    }

    private void verificarPermisosYComenzar() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    CODIGO_PERMISOS);
        } else {
            iniciarRastreo();
        }
    }

    private void iniciarRastreo() {
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    5000,
                    5,
                    this);

            Toast.makeText(this, "Rastreo iniciado...", Toast.LENGTH_SHORT).show();

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        float  precision  = location.getAccuracy();
        float  velocidadMs = location.getSpeed();
        float  velocidadKmh = velocidadMs * 3.6f;

        lblLatitud.setText(String.format(Locale.getDefault(),
                "Latitud: %.8f", lat));
        lblLongitud.setText(String.format(Locale.getDefault(),
                "Longitud: %.8f", lon));
        lblPrecision.setText(String.format(Locale.getDefault(),
                "Precisión: +/- %.1f metros", precision));
        lblVelocidad.setText(String.format(Locale.getDefault(),
                "Velocidad: %.1f km/h", velocidadKmh));

        obtenerDireccion(lat, lon);
    }

    private void obtenerDireccion(double lat, double lon) {
        if (!Geocoder.isPresent()) {
            lblDireccion.setText("Geocodificación no disponible");
            return;
        }

        Thread hilo = new Thread(() -> {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);

                if (addresses != null && !addresses.isEmpty()) {
                    Address dir = addresses.get(0);
                    String linea1 = dir.getThoroughfare() != null
                            ? dir.getThoroughfare() : "";
                    String linea2 = dir.getLocality() != null
                            ? dir.getLocality() : "";
                    String pais   = dir.getCountryName() != null
                            ? dir.getCountryName() : "";
                    String direccionCompleta = linea1 + "\n" + linea2 + "\n" + pais;

                    runOnUiThread(() ->
                            lblDireccion.setText("📍 " + direccionCompleta.trim()));
                } else {
                    runOnUiThread(() ->
                            lblDireccion.setText("Dirección no encontrada"));
                }

            } catch (IOException e) {
                runOnUiThread(() ->
                        lblDireccion.setText("Error al obtener la dirección"));
            }
        });

        hilo.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PERMISOS) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                iniciarRastreo();
            } else {
                Toast.makeText(this,
                        "Se necesitan permisos de ubicación",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Toast.makeText(this, "Proveedor habilitado: " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Toast.makeText(this, "Por favor activa la ubicación",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            iniciarRastreo();
        }
    }
}