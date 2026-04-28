package com.example.actividad4_1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {

    private TextView txtXml;
    private TextView txtJava;
    private TextView txtAppend;
    private TextView txtFuente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_5_1);

        txtXml = findViewById(R.id.txtXml);
        txtJava = findViewById(R.id.txtJava);
        txtAppend = findViewById(R.id.txtAppend);
        txtFuente = findViewById(R.id.txtFuente);

        // Ejemplo 2: texto construido desde Java
        txtJava.setText("Texto construido desde Java\nTamaño 20sp, Italic y color Blue");
        txtJava.setTextSize(20);
        txtJava.setTypeface(null, Typeface.ITALIC);
        txtJava.setTextColor(Color.BLUE);

        // Ejemplo 3: texto creado en XML y ampliado desde Java
        txtAppend.append("\nTexto añadido con append desde Java");

        // Ejemplo 4: texto con fuente personalizada
        Typeface fuentePersonalizada = ResourcesCompat.getFont(this, R.font.lobster_regular);
        txtFuente.setTypeface(fuentePersonalizada);
    }
}