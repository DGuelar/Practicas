package com.guelar.preferenciasdialogosnotificaciones;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad1 extends AppCompatActivity {

    private static final String TAG = "PREFERENCIAS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad1);

        Button btnDefinir    = findViewById(R.id.btnDefinirPreferencias);
        Button btnRecuperar  = findViewById(R.id.btnRecuperarPreferencias);

        btnDefinir.setOnClickListener(v ->
                startActivity(new Intent(this, PreferenciasAppActivity.class)));

        btnRecuperar.setOnClickListener(v -> mostrarPreferencias());
    }

    private void mostrarPreferencias() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        boolean soloUnSO  = prefs.getBoolean("clave_checkbox", false);
        String  sistemaOS = prefs.getString("clave_lista",     "No seleccionado");
        String  version   = prefs.getString("clave_version",   "No introducida");

        Log.d(TAG, "--- Mostrando Preferencias Guardadas ---");
        Log.d(TAG, "Único S.O.: "       + soloUnSO);
        Log.d(TAG, "Sistema Operativo: " + sistemaOS);
        Log.d(TAG, "Versión: "           + version);
    }
}