package com.guelar.androidavanzado;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActividad96 extends AppCompatActivity {

    private TextView txtLog;
    private TextView txtContador1, txtContador2, txtContador3, txtContador4;
    private ScrollView scrollLog;
    private Button btnIniciar, btnDetener, btnLimpiar;

    private ScheduledThreadPoolExecutor miExecutor;

    private final AtomicInteger contador1 = new AtomicInteger(0);
    private final AtomicInteger contador2 = new AtomicInteger(0);
    private final AtomicInteger contador3 = new AtomicInteger(0);
    private final AtomicInteger contador4 = new AtomicInteger(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad96);

        txtLog       = findViewById(R.id.txtLog);
        txtContador1 = findViewById(R.id.txtContador1);
        txtContador2 = findViewById(R.id.txtContador2);
        txtContador3 = findViewById(R.id.txtContador3);
        txtContador4 = findViewById(R.id.txtContador4);
        scrollLog    = findViewById(R.id.scrollLog);
        btnIniciar   = findViewById(R.id.btnIniciar);
        btnDetener   = findViewById(R.id.btnDetener);
        btnLimpiar   = findViewById(R.id.btnLimpiar);

        btnIniciar.setOnClickListener(v -> iniciarTareas());
        btnDetener.setOnClickListener(v -> detenerTareas());
        btnLimpiar.setOnClickListener(v -> txtLog.setText("Log limpiado.\n"));
    }

    private void iniciarTareas() {
        if (miExecutor != null && !miExecutor.isShutdown()) {
            Toast.makeText(this, "Las tareas ya están corriendo", Toast.LENGTH_SHORT).show();
            return;
        }

        contador1.set(0);
        contador2.set(0);
        contador3.set(0);
        contador4.set(0);

        txtLog.setText("--- INICIANDO POOL DE 4 HILOS ---\n");

        miExecutor = new ScheduledThreadPoolExecutor(4);

        miExecutor.scheduleAtFixedRate(new Tarea1(), 0, 2, TimeUnit.SECONDS);
        miExecutor.scheduleAtFixedRate(new Tarea2(), 1, 3, TimeUnit.SECONDS);
        miExecutor.scheduleAtFixedRate(new Tarea3(), 2, 5, TimeUnit.SECONDS);
        miExecutor.schedule(new Tarea4(), 4, TimeUnit.SECONDS);
    }

    private void detenerTareas() {
        if (miExecutor != null) {
            miExecutor.shutdownNow();
            imprimirEnPantalla("--- POOL DETENIDO ---");
        }
    }

    private void imprimirEnPantalla(String mensaje) {
        runOnUiThread(() -> {
            txtLog.append(mensaje + "\n");
            scrollLog.post(() -> scrollLog.fullScroll(ScrollView.FOCUS_DOWN));
        });
    }

    private void actualizarContador(int tarea, AtomicInteger contador) {
        int valor = contador.incrementAndGet();
        runOnUiThread(() -> {
            switch (tarea) {
                case 1: txtContador1.setText(String.valueOf(valor)); break;
                case 2: txtContador2.setText(String.valueOf(valor)); break;
                case 3: txtContador3.setText(String.valueOf(valor)); break;
                case 4: txtContador4.setText(String.valueOf(valor)); break;
            }
        });
    }

    class Tarea1 implements Runnable {
        @Override
        public void run() {
            actualizarContador(1, contador1);
            imprimirEnPantalla("Tarea 1 (Rápida): Ejecutada en "
                    + Thread.currentThread().getName());
        }
    }

    class Tarea2 implements Runnable {
        @Override
        public void run() {
            actualizarContador(2, contador2);
            imprimirEnPantalla("Tarea 2 (Media): Ejecutada en "
                    + Thread.currentThread().getName());
        }
    }

    class Tarea3 implements Runnable {
        @Override
        public void run() {
            actualizarContador(3, contador3);
            imprimirEnPantalla("Tarea 3 (Lenta): Ejecutada en "
                    + Thread.currentThread().getName());
        }
    }

    class Tarea4 implements Runnable {
        @Override
        public void run() {
            actualizarContador(4, contador4);
            imprimirEnPantalla(">> Tarea 4 (FINAL): ¡Solo me ejecuto una vez! <<");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (miExecutor != null) {
            miExecutor.shutdownNow();
        }
    }
}