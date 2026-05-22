package com.guelar.multimedia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.Locale;

public class MainActividad11_5 extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO = 200;

    private String ficheroSalida;
    private MediaRecorder mediaRecorder;
    private MediaPlayer   mediaPlayer;

    private ImageButton btnGrabar, btnParar, btnReproducir;
    private TextView    tvEstado, tvCronometro;

    private boolean grabando      = false;
    private boolean reproduciendo = false;

    private final Handler  handlerCronometro = new Handler(Looper.getMainLooper());
    private int            segundosGrabacion = 0;

    private final Runnable runnableCronometro = new Runnable() {
        @Override
        public void run() {
            if (grabando) {
                segundosGrabacion++;
                int min = segundosGrabacion / 60;
                int seg = segundosGrabacion % 60;
                tvCronometro.setText(
                        String.format(Locale.getDefault(), "⏱ %02d:%02d", min, seg));
                handlerCronometro.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_5);

        ficheroSalida = getExternalFilesDir(null).getAbsolutePath()
                + "/grabacion_guelar.3gp";

        btnGrabar     = findViewById(R.id.btnGrabar);
        btnParar      = findViewById(R.id.btnParar);
        btnReproducir = findViewById(R.id.btnReproducir);
        tvEstado      = findViewById(R.id.tvEstado);
        tvCronometro  = findViewById(R.id.tvCronometro);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO);
        }

        btnGrabar.setOnClickListener(v     -> comenzarGrabacion());
        btnParar.setOnClickListener(v      -> pararAccion());
        btnReproducir.setOnClickListener(v -> comenzarReproduccion());
    }

    private void comenzarGrabacion() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(ficheroSalida);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();

            grabando = true;
            segundosGrabacion = 0;

            btnGrabar.setEnabled(false);
            btnParar.setEnabled(true);
            btnReproducir.setEnabled(false);

            tvEstado.setText("Grabando...");
            tvCronometro.setVisibility(View.VISIBLE);
            handlerCronometro.postDelayed(runnableCronometro, 1000);

            Toast.makeText(this, "Grabación iniciada 🔴", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.e("Grabadora", "Error en prepare()", e);
            tvEstado.setText("Error al iniciar la grabación");
        }
    }

    private void pararAccion() {
        if (grabando) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            grabando = false;

            handlerCronometro.removeCallbacks(runnableCronometro);
            tvCronometro.setVisibility(View.INVISIBLE);

            btnGrabar.setEnabled(true);
            btnParar.setEnabled(false);
            btnReproducir.setEnabled(true);

            tvEstado.setText("Grabación finalizada. Listo para reproducir.");

        } else if (reproduciendo) {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
            reproduciendo = false;

            btnGrabar.setEnabled(true);
            btnParar.setEnabled(false);
            btnReproducir.setEnabled(true);

            tvEstado.setText("Reproducción detenida.");
        }
    }

    private void comenzarReproduccion() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(ficheroSalida);
            mediaPlayer.prepare();
            mediaPlayer.start();

            reproduciendo = true;
            btnGrabar.setEnabled(false);
            btnParar.setEnabled(true);
            btnReproducir.setEnabled(false);
            tvEstado.setText("Reproduciendo...");

            Toast.makeText(this, "Reproduciendo grabación ▶", Toast.LENGTH_SHORT).show();

            mediaPlayer.setOnCompletionListener(mp -> btnParar.performClick());

        } catch (IOException e) {
            Log.e("Grabadora", "Error al reproducir", e);
            tvEstado.setText("Error al reproducir");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO) {
            if (grantResults.length == 0 ||
                    grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,
                        "Se necesita permiso de micrófono para grabar",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        handlerCronometro.removeCallbacks(runnableCronometro);
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}