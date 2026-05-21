package com.guelar.listadosymenus;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1_layout);

        String[] planetas = {
                "Mercurio",
                "Venus",
                "Tierra",
                "Marte",
                "Júpiter",
                "Saturno",
                "Urano",
                "Neptuno",
                "Powered by Guelar"
        };

        ListView listView = findViewById(R.id.listViewPlanetas);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                planetas
        );

        TextView cabecera = new TextView(this);
        cabecera.setText("Planetas del Sistema Solar");
        cabecera.setTextSize(18);
        cabecera.setPadding(16, 24, 16, 24);
        cabecera.setBackgroundColor(0xFFE0E0E0);
        listView.addHeaderView(cabecera);

        TextView pie = new TextView(this);
        pie.setText("Powered by David Guelar");
        pie.setTextSize(14);
        pie.setPadding(16, 16, 16, 16);
        pie.setBackgroundColor(0xFFE0E0E0);
        listView.addFooterView(pie);

        listView.setAdapter(adaptador);
    }
}