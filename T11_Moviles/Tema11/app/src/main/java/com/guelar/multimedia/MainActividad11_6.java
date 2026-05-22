package com.guelar.multimedia;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad11_6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_6);

        FrameLayout contenedor = findViewById(R.id.contenedorPrincipal);

        VistaTactil vistaTactil = new VistaTactil(this);
        contenedor.addView(vistaTactil);
    }
}