package com.guelar.controlesbasicos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CasoListenerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvEstado2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso_listener);

        tvEstado2 = findViewById(R.id.tvEstado2);

        Button btnBoton1 = findViewById(R.id.btnBoton1);
        Button btnBoton2 = findViewById(R.id.btnBoton2);

        btnBoton1.setOnClickListener(this);
        btnBoton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnBoton1) {
            tvEstado2.setText(getString(R.string.etiqueta_boton1));
        } else if (id == R.id.btnBoton2) {
            tvEstado2.setText(getString(R.string.etiqueta_boton2));
        }
    }
}