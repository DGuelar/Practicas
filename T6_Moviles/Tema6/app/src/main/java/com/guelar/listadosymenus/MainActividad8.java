package com.guelar.listadosymenus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.guelar.listadosymenus.adapters.VersionAdapterSpinner;
import com.guelar.listadosymenus.pojos.Encapsulador;

import java.util.ArrayList;

public class MainActividad8 extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private ArrayList<Encapsulador> datos;
    private TextView tvTituloDetalle;
    private TextView tvDescripcionDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad8_layout);

        tvTituloDetalle      = findViewById(R.id.tvTituloDetalle);
        tvDescripcionDetalle = findViewById(R.id.tvDescripcionDetalle);
        Spinner spinner      = findViewById(R.id.spinnerVersiones);

        datos = new ArrayList<>();
        datos.add(new Encapsulador(R.drawable.donuts,      "DONUTS",
                "El 15 de septiembre de 2009, fue lanzado el SDK de Android 1.6 Donut, " +
                        "basado en el núcleo Linux 2.6.29.", true));
        datos.add(new Encapsulador(R.drawable.froyo,       "FROYO",
                "El 20 de mayo de 2010, El SDK de Android 2.2 Froyo (Yogur helado) fue " +
                        "lanzado, basado en el núcleo Linux 2.6.32.", false));
        datos.add(new Encapsulador(R.drawable.gingerbread, "GINGERBREAD",
                "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread fue lanzado, " +
                        "basado en el núcleo Linux 2.6.35.", false));
        datos.add(new Encapsulador(R.drawable.honeycomb,   "HONEYCOMB",
                "El 22 de febrero de 2011, sale el SDK de Android 3.0 Honeycomb. Fue la " +
                        "primera actualización exclusiva para TV y tableta.", false));
        datos.add(new Encapsulador(R.drawable.icecream,    "ICE CREAM",
                "El SDK para Android 4.0.0 Ice Cream Sandwich, basado en el núcleo de " +
                        "Linux 3.0.1, fue lanzado el 12 de octubre de 2011.", false));
        datos.add(new Encapsulador(R.drawable.jellybean,   "JELLY BEAN",
                "Google anunció Android 4.1 Jelly Bean en la conferencia del 30 de junio " +
                        "de 2012. Basado en el núcleo de Linux 3.0.31.", false));
        datos.add(new Encapsulador(R.drawable.kitkat,      "KITKAT",
                "Su nombre se debe a la chocolatina KitKat de Nestlé. Posibilidad de " +
                        "impresión mediante WIFI. WebViews basadas en Chromium.", false));
        datos.add(new Encapsulador(R.drawable.lollipop,    "LOLLIPOP",
                "Incluye Material Design, un diseño intrépido, colorido y con interfaz " +
                        "sensible para experiencias coherentes en todos los dispositivos.", false));

        datos.add(new Encapsulador(R.mipmap.ic_launcher,   "DAVID GUELAR",
                "Nombre del alumno que ha desarrollado este código.", false));

        VersionAdapterSpinner adaptador = new VersionAdapterSpinner(this, datos);
        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int posicion, long id) {
        Encapsulador seleccionado = datos.get(posicion);
        tvTituloDetalle.setText(seleccionado.getTitulo());
        tvDescripcionDetalle.setText(seleccionado.getDescripcion());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        tvTituloDetalle.setText("SELECCIONE UNA VERSIÓN");
        tvDescripcionDetalle.setText("Los detalles aparecerán aquí.");
    }
}