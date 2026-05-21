package com.guelar.listadosymenus;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad3_layout);

        String[] paises = {
                "España",
                "Francia",
                "Italia",
                "Alemania",
                "Portugal",
                "Reino Unido",
                "Grecia",
                "Polonia",
                "Suecia",
                "Irlanda",
                "David Guelar"
        };

        GridView gridView           = findViewById(R.id.gridViewPaises);
        TextView tvPaisSeleccionado = findViewById(R.id.tvPaisSeleccionado);


        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                R.layout.list_item_paises,
                R.id.tvItemPais,
                paises
        );

        gridView.setAdapter(adaptador);


        gridView.setOnItemClickListener((parent, view, posicion, id) -> {
            String paisPulsado = (String) parent.getItemAtPosition(posicion);
            tvPaisSeleccionado.setText(paisPulsado);
        });
    }
}