package com.example.t5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadAppendActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_append);

        TextView txtAppend = findViewById(R.id.txtAppend);
        txtAppend.append("\nTexto añadido con append desde Java");

        Button btnSiguienteAppend = findViewById(R.id.btnSiguienteAppend);

        btnSiguienteAppend.setOnClickListener(v -> {
            Intent intent = new Intent(ActividadAppendActivity.this, ActividadFuenteActivity.class);
            startActivity(intent);
        });
    }
}