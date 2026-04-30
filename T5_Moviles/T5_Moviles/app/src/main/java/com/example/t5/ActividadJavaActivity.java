package com.example.t5;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadJavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        TextView txtJava = findViewById(R.id.txtJava);
        txtJava.setText("Texto construido desde Java\nTamaño 20sp, italic y color Blue");
        txtJava.setTextSize(20);
        txtJava.setTextColor(Color.BLUE);
        txtJava.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);

        Button btnSiguienteJava = findViewById(R.id.btnSiguienteJava);

        btnSiguienteJava.setOnClickListener(v -> {
            Intent intent = new Intent(ActividadJavaActivity.this, ActividadAppendActivity.class);
            startActivity(intent);
        });
    }
}