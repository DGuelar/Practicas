package com.guelar.controlesbasicos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCaso1 = findViewById(R.id.btnCaso1);
        Button btnCaso2 = findViewById(R.id.btnCaso2);
        Button btnCaso3 = findViewById(R.id.btnCaso3);
        Button btnCaso4 = findViewById(R.id.btnCaso4);

        // Cada botón abre su Activity correspondiente
        btnCaso1.setOnClickListener(v ->
                startActivity(new Intent(this, Caso1Activity.class)));

        btnCaso2.setOnClickListener(v ->
                startActivity(new Intent(this, Caso2Activity.class)));

        btnCaso3.setOnClickListener(v ->
                startActivity(new Intent(this, Caso3Activity.class)));

        btnCaso4.setOnClickListener(v ->
                startActivity(new Intent(this, Caso4Activity.class)));

        Button btnAnimaciones = findViewById(R.id.btnAnimaciones);
        btnAnimaciones.setOnClickListener(v ->
                startActivity(new Intent(this, AnimacionesActivity.class)));

        Button btnCaso53OnClick  = findViewById(R.id.btnCasoOnClick);
        Button btnCaso53Listener = findViewById(R.id.btnCasoListener);

        btnCaso53OnClick.setOnClickListener(v ->
                startActivity(new Intent(this, CasoOnClickActivity.class)));

        btnCaso53Listener.setOnClickListener(v ->
                startActivity(new Intent(this, CasoListenerActivity.class)));

        Button btnCaso54 = findViewById(R.id.btnCaso5_4);
        btnCaso54.setOnClickListener(v ->
                startActivity(new Intent(this, Caso5_4Activity.class)));

        Button btnCaso55 = findViewById(R.id.btnCaso5_5);
        btnCaso55.setOnClickListener(v ->
                startActivity(new Intent(this, Caso5_5Activity.class)));

        Button btnCaso56 = findViewById(R.id.btnCaso56);
        btnCaso56.setOnClickListener(v ->
                startActivity(new Intent(this, Caso56Activity.class)));
    }
}