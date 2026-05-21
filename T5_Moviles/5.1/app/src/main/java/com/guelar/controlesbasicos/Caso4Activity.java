package com.guelar.controlesbasicos;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class Caso4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso4);

        TextView tvCaso4 = findViewById(R.id.tvCaso4);

        // Cargamos la fuente desde res/font/ con ResourcesCompat
        Typeface miFuente = ResourcesCompat.getFont(this, R.font.pacifico);
        tvCaso4.setTypeface(miFuente);
    }
}