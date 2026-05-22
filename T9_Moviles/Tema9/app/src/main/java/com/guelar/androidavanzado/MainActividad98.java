package com.guelar.androidavanzado;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActividad98 extends AppCompatActivity {

    private TextView tvTiempo, tvEstado, tvMaximo;
    private Button btnIniciar, btnPausar, btnReiniciar;

    private CronometroService cronometroService;
    private boolean isBound = false;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private double tiempoMaximo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad98);

        tvTiempo   = findViewById(R.id.tvTiempo);
        tvEstado   = findViewById(R.id.tvEstado);
        tvMaximo   = findViewById(R.id.tvMaximo);
        btnIniciar  = findViewById(R.id.btnIniciar);
        btnPausar   = findViewById(R.id.btnPausar);
        btnReiniciar = findViewById(R.id.btnReiniciar);

        btnIniciar.setOnClickListener(v -> {
            if (isBound) {
                cronometroService.iniciarCronometro();
                actualizarEstado("CORRIENDO", Color.parseColor("#2E7D32"));
            }
        });

        btnPausar.setOnClickListener(v -> {
            if (isBound) {
                cronometroService.pausarCronometro();
                actualizarEstado("PAUSADO", Color.parseColor("#C62828"));
            }
        });

        btnReiniciar.setOnClickListener(v -> {
            if (isBound) {
                double tiempoActual = cronometroService.obtenerTiempoTranscurrido();
                if (tiempoActual > tiempoMaximo) {
                    tiempoMaximo = tiempoActual;
                    tvMaximo.setText("Máximo: " + formatearTiempo(tiempoMaximo));
                }
                cronometroService.reiniciarCronometro();
                actualizarUI(0);
                actualizarEstado("DETENIDO", Color.parseColor("#9E9E9E"));
            }
        });
    }

    private final Runnable actualizarTiempoRunnable = new Runnable() {
        @Override
        public void run() {
            if (isBound && cronometroService != null) {
                actualizarUI(cronometroService.obtenerTiempoTranscurrido());
                handler.postDelayed(this, 10);
            }
        }
    };

    private void actualizarUI(double tiempoMilis) {
        tvTiempo.setText(formatearTiempo(tiempoMilis));
    }

    private String formatearTiempo(double tiempoMilis) {
        int secs  = (int) (tiempoMilis / 1000);
        int mins  = secs / 60;
        secs      = secs % 60;
        int millis = (int) (tiempoMilis % 1000);
        return String.format(Locale.getDefault(), "%02d:%02d:%03d", mins, secs, millis);
    }

    private void actualizarEstado(String texto, int color) {
        tvEstado.setText(texto);
        tvEstado.setBackgroundColor(color);
    }

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            CronometroService.LocalBinder localBinder = (CronometroService.LocalBinder) service;
            cronometroService = localBinder.getService();
            isBound = true;
            handler.post(actualizarTiempoRunnable);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, CronometroService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            handler.removeCallbacks(actualizarTiempoRunnable);
            unbindService(connection);
            isBound = false;
        }
    }
}