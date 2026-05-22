package com.guelar.androidavanzado;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad910 extends AppCompatActivity {

    private EditText etUrl;
    private ProgressBar progressBateria;
    private TextView tvBateria;

    private static final String ACTION_MI_MENSAJE =
            "com.guelar.androidavanzado.ACTION_VER_URL";

    private final BroadcastReceiver bateriaReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int nivel  = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int escala = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int porcentaje = (int) ((nivel * 100) / (float) escala);

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean cargando = status == BatteryManager.BATTERY_STATUS_CHARGING
                    || status == BatteryManager.BATTERY_STATUS_FULL;

            String estado = cargando ? "Cargando ⚡" : "Descargando";

            Toast.makeText(context,
                    "Batería: " + porcentaje + "% (" + estado + ")",
                    Toast.LENGTH_LONG).show();

            progressBateria.setProgress(porcentaje);
            tvBateria.setText("Nivel: " + porcentaje + "% — " + estado);

            if (porcentaje <= 20) {
                progressBateria.getProgressDrawable()
                        .setColorFilter(Color.parseColor("#C62828"),
                                android.graphics.PorterDuff.Mode.SRC_IN);
            } else if (porcentaje <= 50) {
                progressBateria.getProgressDrawable()
                        .setColorFilter(Color.parseColor("#E65100"),
                                android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                progressBateria.getProgressDrawable()
                        .setColorFilter(Color.parseColor("#2E7D32"),
                                android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }
    };

    private final BroadcastReceiver mensajeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_MI_MENSAJE.equals(intent.getAction())) {
                String urlRecibida = intent.getStringExtra("url_extra");

                Toast.makeText(context,
                        "Broadcast recibido!\nAbriendo: " + urlRecibida,
                        Toast.LENGTH_SHORT).show();

                if (urlRecibida != null && !urlRecibida.isEmpty()) {
                    Intent navegador = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(urlRecibida));
                    navegador.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(navegador);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad910);

        etUrl           = findViewById(R.id.etUrl);
        progressBateria = findViewById(R.id.progressBateria);
        tvBateria       = findViewById(R.id.tvBateria);
        Button btnEnviar = findViewById(R.id.btnEnviarBroadcast);

        btnEnviar.setOnClickListener(v -> {
            String urlTexto = etUrl.getText().toString().trim();
            if (urlTexto.isEmpty()) {
                Toast.makeText(this, "Escribe una URL primero", Toast.LENGTH_SHORT).show();
                return;
            }

            final String urlFinal = urlTexto.startsWith("http://") || urlTexto.startsWith("https://")
                    ? urlTexto
                    : "https://" + urlTexto;

            Intent intent = new Intent();
            intent.setAction(ACTION_MI_MENSAJE);
            intent.putExtra("url_extra", urlFinal);
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filtroBateria = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(bateriaReceiver, filtroBateria);

        IntentFilter filtroMensaje = new IntentFilter(ACTION_MI_MENSAJE);
        androidx.core.content.ContextCompat.registerReceiver(
                this,
                mensajeReceiver,
                filtroMensaje,
                androidx.core.content.ContextCompat.RECEIVER_NOT_EXPORTED
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(bateriaReceiver);
        unregisterReceiver(mensajeReceiver);
    }
}