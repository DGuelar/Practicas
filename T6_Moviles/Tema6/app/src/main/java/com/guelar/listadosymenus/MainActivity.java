package com.guelar.listadosymenus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn61 = findViewById(R.id.btn61);
        Button btn62 = findViewById(R.id.btn62);
        Button btn63 = findViewById(R.id.btn63);
        Button btn64 = findViewById(R.id.btn64);
        Button btn65 = findViewById(R.id.btn65);
        Button btn66 = findViewById(R.id.btn66);
        Button btn67 = findViewById(R.id.btn67);
        Button btn68 = findViewById(R.id.btn68);
        Button btn69 = findViewById(R.id.btn69);

        btn61.setOnClickListener(v -> startActivity(new Intent(this, MainActividad1.class)));
        btn62.setOnClickListener(v -> startActivity(new Intent(this, MainActividad2.class)));
        btn63.setOnClickListener(v -> startActivity(new Intent(this, MainActividad3.class)));
        btn64.setOnClickListener(v -> startActivity(new Intent(this, MainActividad4.class)));
        btn65.setOnClickListener(v -> startActivity(new Intent(this, MainActividad5.class)));
        btn66.setOnClickListener(v -> startActivity(new Intent(this, MainActividad6.class)));
        btn67.setOnClickListener(v -> startActivity(new Intent(this, MainActividad7.class)));
        btn68.setOnClickListener(v -> startActivity(new Intent(this, MainActividad8.class)));
        btn69.setOnClickListener(v -> startActivity(new Intent(this, MainActividad9.class)));
    }
}