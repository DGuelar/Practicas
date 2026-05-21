package com.guelar.listadosymenus;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2_layout);

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

        ListView listView        = findViewById(R.id.listViewPaises);
        TextView tvPaisSeleccionado = findViewById(R.id.tvPaisSeleccionado);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                R.layout.list_item_paises,
                R.id.tvItemPais,
                paises
        );

        listView.setAdapter(adaptador);

        listView.setOnItemClickListener((parent, view, posicion, id) -> {

            String paisPulsado = (String) parent.getItemAtPosition(posicion);

            tvPaisSeleccionado.setText(paisPulsado);
        });
    }
}