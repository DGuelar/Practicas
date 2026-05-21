package com.guelar.controlesbasicos;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimacionesActivity extends AppCompatActivity {
    private final int[] animaciones = {
            R.anim.alfa1,
            R.anim.alfa2,
            R.anim.escala1,
            R.anim.escala2,
            R.anim.mueve1,
            R.anim.mueve2,
            R.anim.rotar1,
            R.anim.rotar2,
            R.anim.rotar3,
            R.anim.varios1,
            R.anim.varios2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso_animaciones);

        TextView tvAnimado       = findViewById(R.id.tvAnimado);
        Spinner spinnerAnimaciones = findViewById(R.id.spinnerAnimaciones);
        Button btnReproducir     = findViewById(R.id.btnReproducir);

        btnReproducir.setOnClickListener(v -> {

            // Averiguamos qué posición del Spinner está seleccionada
            int posicion = spinnerAnimaciones.getSelectedItemPosition();

            // Cargamos la animación correspondiente desde res/anim/
            Animation anim = AnimationUtils.loadAnimation(this, animaciones[posicion]);

            // La aplicamos al TextView y la arrancamos
            tvAnimado.startAnimation(anim);
        });
    }
}