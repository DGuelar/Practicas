package com.guelar.controlesbasicos;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CasoOnClickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso_onclick);
    }
    public void alPulsar(View view) {
        TextView tvEstado = findViewById(R.id.tvEstado);
        tvEstado.setText(getString(R.string.etiqueta_pulsado));
    }
}