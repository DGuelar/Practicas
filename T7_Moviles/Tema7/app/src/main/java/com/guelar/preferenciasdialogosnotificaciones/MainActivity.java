package com.guelar.preferenciasdialogosnotificaciones;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn71 = findViewById(R.id.btn71);
        Button btn72 = findViewById(R.id.btn72);
        Button btn73 = findViewById(R.id.btn73);
        Button btn74 = findViewById(R.id.btn74);
        Button btn75 = findViewById(R.id.btn75);

        btn71.setOnClickListener(v -> startActivity(new Intent(this, MainActividad1.class)));
        btn72.setOnClickListener(v -> startActivity(new Intent(this, MainActividad2.class)));
        btn73.setOnClickListener(v -> startActivity(new Intent(this, MainActividad3.class)));
        btn74.setOnClickListener(v -> startActivity(new Intent(this, MainActividad4.class)));
        btn75.setOnClickListener(v -> startActivity(new Intent(this, MainActividad5.class)));
    }
}