package com.guelar.multimedia;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad11_3 extends AppCompatActivity {

    private CanvasApellidosView canvasView;
    private float anguloActual = 0f;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_3);

        canvasView = findViewById(R.id.canvasView);

        handler.post(animacionRunnable);
    }

    private final Runnable animacionRunnable = new Runnable() {
        @Override
        public void run() {
            anguloActual = (anguloActual + 1f) % 360f;
            canvasView.setAnguloRotacion(anguloActual);
            handler.postDelayed(this, 16);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(animacionRunnable);
    }
}