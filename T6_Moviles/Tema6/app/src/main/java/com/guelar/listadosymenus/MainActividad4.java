package com.guelar.listadosymenus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad4 extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {
    private TextView tvPaisSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad4_layout);

        String[] paises = {
                "España",
                "Francia",
                "Italia",
                "Alemania",
                "Portugal",
                "Reino Unido",
                "Grecia",
                "Polonia",
                "Suecia",
                "Irlanda",
                "David Guelar"
        };

        Spinner spinner         = findViewById(R.id.spinnerPaises);
        tvPaisSeleccionado      = findViewById(R.id.tvPaisSeleccionado);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                paises
        );

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {
        String paisSeleccionado = (String) parent.getItemAtPosition(posicion);
        tvPaisSeleccionado.setText(paisSeleccionado);
    }
    public void onNothingSelected(AdapterView<?> parent) {
        tvPaisSeleccionado.setText("Ningún país seleccionado");
    }
}