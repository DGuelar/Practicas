package com.guelar.controlesbasicos;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Caso56Activity extends AppCompatActivity {
    private ProgressBar pbCircular;
    private ProgressBar pbLineal;
    private TextView    tvProgreso;
    private Button      btnSimularCarga;
    private Button      btnIniciarTarea;

    private final Handler handler = new Handler();
    private int porcentaje = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso56);

        pbCircular      = findViewById(R.id.pbCircular);
        pbLineal        = findViewById(R.id.pbLineal);
        tvProgreso      = findViewById(R.id.tvProgreso);
        btnSimularCarga = findViewById(R.id.btnSimularCarga);
        btnIniciarTarea = findViewById(R.id.btnIniciarTarea);

        btnSimularCarga.setOnClickListener(v -> simularCarga());

        btnIniciarTarea.setOnClickListener(v -> iniciarTarea());
    }
    private void simularCarga() {

        btnSimularCarga.setEnabled(false);

        pbCircular.setVisibility(View.VISIBLE);

        handler.postDelayed(() -> {
            pbCircular.setVisibility(View.GONE);
            btnSimularCarga.setEnabled(true);
        }, 3000);
    }

    private void iniciarTarea() {

        porcentaje = 0;
        pbLineal.setProgress(0);
        btnIniciarTarea.setEnabled(false);

        Runnable actualizarBarra = new Runnable() {
            @Override
            public void run() {
                if (porcentaje <= 100) {

                    pbLineal.setProgress(porcentaje);
                    tvProgreso.setText("Tarea programada: " + porcentaje + "%");

                    porcentaje++;

                    handler.postDelayed(this, 50);

                } else {
                    tvProgreso.setText(getString(R.string.tarea_completada));
                    btnIniciarTarea.setEnabled(true);
                }
            }
        };

        handler.post(actualizarBarra);
    }
}