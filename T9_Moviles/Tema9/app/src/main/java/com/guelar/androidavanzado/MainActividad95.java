package com.guelar.androidavanzado;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActividad95 extends AppCompatActivity {

    private TextView txtCronometro;
    private Button btnIniciar, btnPausar, btnReiniciar, btnVuelta;
    private LinearLayout contenedorVueltas;
    private ScrollView scrollVueltas;

    private int segundos = 0;
    private boolean corriendo = false;
    private int numeroVuelta = 1;

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad95);

        txtCronometro    = findViewById(R.id.txtCronometro);
        btnIniciar       = findViewById(R.id.btnIniciar);
        btnPausar        = findViewById(R.id.btnPausar);
        btnReiniciar     = findViewById(R.id.btnReiniciar);
        btnVuelta        = findViewById(R.id.btnVuelta);
        contenedorVueltas = findViewById(R.id.contenedorVueltas);
        scrollVueltas    = (ScrollView) contenedorVueltas.getParent();

        btnIniciar.setOnClickListener(v -> corriendo = true);

        btnPausar.setOnClickListener(v -> corriendo = false);

        btnReiniciar.setOnClickListener(v -> {
            corriendo = false;
            segundos = 0;
            numeroVuelta = 1;
            contenedorVueltas.removeAllViews();
            actualizarTextoCronometro();
        });

        btnVuelta.setOnClickListener(v -> {
            if (corriendo) {
                registrarVuelta();
            }
        });

        ejecutarCronometro();
    }

    private void ejecutarCronometro() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                actualizarTextoCronometro();
                if (corriendo) {
                    segundos++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void actualizarTextoCronometro() {
        int horas    = segundos / 3600;
        int minutos  = (segundos % 3600) / 60;
        int secc     = segundos % 60;

        String tiempo = String.format(Locale.getDefault(),
                "%d:%02d:%02d", horas, minutos, secc);
        txtCronometro.setText(tiempo);

        if (segundos == 0) {
            txtCronometro.setTextColor(Color.parseColor("#212121"));
        } else if (corriendo) {
            txtCronometro.setTextColor(Color.parseColor("#2E7D32"));
        } else {
            txtCronometro.setTextColor(Color.parseColor("#C62828"));
        }
    }

    private void registrarVuelta() {
        int horas   = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int secc    = segundos % 60;

        String tiempoVuelta = String.format(Locale.getDefault(),
                "Vuelta %d — %d:%02d:%02d", numeroVuelta, horas, minutos, secc);

        TextView tvVuelta = new TextView(this);
        tvVuelta.setText(tiempoVuelta);
        tvVuelta.setTextSize(16f);
        tvVuelta.setPadding(16, 12, 16, 12);
        tvVuelta.setBackgroundColor(numeroVuelta % 2 == 0
                ? Color.parseColor("#E3F2FD")
                : Color.parseColor("#FFFFFF"));

        contenedorVueltas.addView(tvVuelta);
        numeroVuelta++;

        scrollVueltas.post(() ->
                scrollVueltas.fullScroll(View.FOCUS_DOWN));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("segundos", segundos);
        savedInstanceState.putBoolean("corriendo", corriendo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        segundos  = savedInstanceState.getInt("segundos");
        corriendo = savedInstanceState.getBoolean("corriendo");
    }
}