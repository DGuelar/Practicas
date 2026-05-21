package com.guelar.controlesbasicos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Caso3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso3);

        TextView tvCaso3 = findViewById(R.id.tvCaso3);

        // append() agrega texto al final sin borrar lo que ya había en el XML
        tvCaso3.append(getString(R.string.c3_append));
    }
}