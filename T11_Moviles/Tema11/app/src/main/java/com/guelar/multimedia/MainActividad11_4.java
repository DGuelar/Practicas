package com.guelar.multimedia;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad11_4 extends AppCompatActivity {

    private SoundPool soundPool;
    private int idSonido1, idSonido2;

    private float volumenActual = 1.0f;
    private float rateActual    = 1.0f;
    private float volIzquierdo  = 1.0f;
    private float volDerecho    = 1.0f;

    private TextView tvLabelVolumen, tvLabelRate, tvLabelBalance;
    private SeekBar  sbVolumen, sbRate, sbBalance;
    private Button   btnSonido1, btnSonido2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_4);

        inicializarVistas();
        crearSoundPool();
        configurarListeners();
    }

    private void inicializarVistas() {
        tvLabelVolumen = findViewById(R.id.tvLabelVolumen);
        tvLabelRate    = findViewById(R.id.tvLabelRate);
        tvLabelBalance = findViewById(R.id.tvLabelBalance);
        sbVolumen      = findViewById(R.id.sbVolumen);
        sbRate         = findViewById(R.id.sbRate);
        sbBalance      = findViewById(R.id.sbBalance);
        btnSonido1     = findViewById(R.id.btnSonido1);
        btnSonido2     = findViewById(R.id.btnSonido2);
    }

    private void crearSoundPool() {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        soundPool.setOnLoadCompleteListener((pool, sampleId, status) -> {
            if (status == 0) {
                Toast.makeText(this, "Sonido cargado ✓", Toast.LENGTH_SHORT).show();
            }
        });

        idSonido1 = soundPool.load(this, R.raw.sonido1, 1);
        idSonido2 = soundPool.load(this, R.raw.sonido2, 1);
    }

    private void configurarListeners() {
        sbVolumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumenActual = progress / 100.0f;
                tvLabelVolumen.setText("Volumen: " + String.format("%.2f", volumenActual));
                recalcularBalance(sbBalance.getProgress());
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        sbRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rateActual = Math.max(0.1f, progress / 100.0f);
                tvLabelRate.setText("Velocidad (Rate): " + String.format("%.2f", rateActual) + "x");
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        sbBalance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                recalcularBalance(progress);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnSonido1.setOnClickListener(v -> reproducirSonido(idSonido1));
        btnSonido2.setOnClickListener(v -> reproducirSonido(idSonido2));
    }

    private void recalcularBalance(int progreso) {
        float balance = progreso / 100.0f;

        if (balance <= 1.0f) {
            volIzquierdo = volumenActual;
            volDerecho   = volumenActual * balance;
        } else {
            volIzquierdo = volumenActual * (2.0f - balance);
            volDerecho   = volumenActual;
        }

        tvLabelBalance.setText(String.format(
                "Balance: L=%.2f | R=%.2f", volIzquierdo, volDerecho));
    }

    private void reproducirSonido(int soundId) {
        if (soundId != 0) {
            soundPool.play(soundId, volIzquierdo, volDerecho, 1, 0, rateActual);
        } else {
            Toast.makeText(this, "El sonido no ha cargado aún", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}