package com.guelar.controlesbasicos;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Caso2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso2);

        TextView tvCaso2 = findViewById(R.id.tvCaso2);

        // Asignamos todo desde código Java
        tvCaso2.setText("Texto construido desde Java\nTamaño 20sp, Itálic y color Blue");
        tvCaso2.setTextSize(20);                     // unidad sp por defecto
        tvCaso2.setTextColor(Color.BLUE);
        tvCaso2.setTypeface(null, Typeface.ITALIC);  // estilo cursiva
    }
}