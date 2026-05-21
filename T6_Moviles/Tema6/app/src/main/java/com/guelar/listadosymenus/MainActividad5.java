package com.guelar.listadosymenus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.guelar.listadosymenus.adapters.PlanetaAdapter;
import com.guelar.listadosymenus.pojos.Planeta;

import java.util.ArrayList;

public class MainActividad5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad5_layout);

        ArrayList<Planeta> planetas = new ArrayList<>();
        planetas.add(new Planeta("Mercurio", "El más cercano al Sol",
                R.drawable.mercurio, false, "https://es.wikipedia.org/wiki/Mercurio_(planeta)"));
        planetas.add(new Planeta("Venus", "El gemelo tóxico de la Tierra",
                R.drawable.venus, false, "https://es.wikipedia.org/wiki/Venus_(planeta)"));
        planetas.add(new Planeta("Tierra", "Nuestro planeta hogar",
                R.drawable.tierra, true, "https://es.wikipedia.org/wiki/Tierra"));
        planetas.add(new Planeta("Marte", "El planeta rojo",
                R.drawable.marte, false, "https://es.wikipedia.org/wiki/Marte_(planeta)"));
        planetas.add(new Planeta("Júpiter", "El gigante gaseoso",
                R.drawable.jupiter, false, "https://es.wikipedia.org/wiki/J%C3%BApiter_(planeta)"));
        planetas.add(new Planeta("Saturno", "El señor de los anillos",
                R.drawable.saturno, false, "https://es.wikipedia.org/wiki/Saturno_(planeta)"));
        planetas.add(new Planeta("Urano", "El gigante de hielo inclinado",
                R.drawable.urano, false, "https://es.wikipedia.org/wiki/Urano_(planeta)"));
        planetas.add(new Planeta("Neptuno", "El planeta más lejano",
                R.drawable.neptuno, false, "https://es.wikipedia.org/wiki/Neptuno_(planeta)"));

        ListView listView = findViewById(R.id.listViewPlanetas);

        PlanetaAdapter adaptador = new PlanetaAdapter(this, planetas);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener((parent, view, posicion, id) -> {
            Planeta planetaPulsado = planetas.get(posicion);
            String url = planetaPulsado.getUrlInfo();

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}