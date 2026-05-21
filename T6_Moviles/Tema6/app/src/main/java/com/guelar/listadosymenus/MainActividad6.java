package com.guelar.listadosymenus;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.guelar.listadosymenus.adapters.VersionAdapter;
import com.guelar.listadosymenus.pojos.Encapsulador;

import java.util.ArrayList;

public class MainActividad6 extends AppCompatActivity {

    private ArrayList<Encapsulador> datos;
    private VersionAdapter adaptador;
    private TextView tvTituloDetalle;
    private TextView tvDescripcionDetalle;
    private TextView tvInfoInferior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad6_layout);

        tvTituloDetalle       = findViewById(R.id.tvTituloDetalle);
        tvDescripcionDetalle  = findViewById(R.id.tvDescripcionDetalle);
        tvInfoInferior        = findViewById(R.id.tvInfoInferior);
        ListView listView     = findViewById(R.id.listViewVersiones);

        datos = new ArrayList<>();
        datos.add(new Encapsulador(R.drawable.donuts,      "DONUTS",
                "El 15 de septiembre de 2009, fue lanzado el SDK de Android 1.6 Donut, " +
                        "basado en el núcleo Linux 2.6.29. Hecho por David Guelar", true));
        datos.add(new Encapsulador(R.drawable.froyo,       "FROYO",
                "El 20 de mayo de 2010, El SDK de Android 2.2 Froyo (Yogur helado) fue " +
                        "lanzado, basado en el núcleo Linux 2.6.32. Hecho por David Guelar", false));
        datos.add(new Encapsulador(R.drawable.gingerbread, "GINGERBREAD",
                "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread fue lanzado, " +
                        "basado en el núcleo Linux 2.6.35. Hecho por David Guelar", false));
        datos.add(new Encapsulador(R.drawable.honeycomb,   "HONEYCOMB",
                "El 22 de febrero de 2011, sale el SDK de Android 3.0 Honeycomb. Fue la " +
                        "primera actualización exclusiva para TV y tableta. Hecho por David Guelar", false));
        datos.add(new Encapsulador(R.drawable.icecream,    "ICE CREAM",
                "El SDK para Android 4.0.0 Ice Cream Sandwich, basado en el núcleo de " +
                        "Linux 3.0.1, fue lanzado el 12 de octubre de 2011. Hecho por David Guelar", false));
        datos.add(new Encapsulador(R.drawable.jellybean,   "JELLY BEAN",
                "Google anunció Android 4.1 Jelly Bean en la conferencia del 30 de junio " +
                        "de 2012. Basado en el núcleo de Linux 3.0.31. Hecho por David Guelar", false));
        datos.add(new Encapsulador(R.drawable.kitkat,      "KITKAT",
                "Su nombre se debe a la chocolatina KitKat de Nestlé. Posibilidad de " +
                        "impresión mediante WIFI. WebViews basadas en Chromium. Hecho por David Guelar", false));
        datos.add(new Encapsulador(R.drawable.lollipop,    "LOLLIPOP",
                "Incluye Material Design, un diseño intrépido, colorido y con interfaz " +
                        "sensible para experiencias coherentes en todos los dispositivos. Hecho por David Guelar", false));

        adaptador = new VersionAdapter(this, datos);
        listView.setAdapter(adaptador);

        actualizarPaneles(0);

        listView.setOnItemClickListener((parent, view, posicion, id) -> {

            for (int i = 0; i < datos.size(); i++) {
                datos.get(i).setSeleccionado(i == posicion);
            }

            adaptador.notifyDataSetChanged();

            actualizarPaneles(posicion);
        });
    }
    private void actualizarPaneles(int posicion) {
        Encapsulador version = datos.get(posicion);
        tvTituloDetalle.setText(version.getTitulo());
        tvDescripcionDetalle.setText(version.getDescripcion());
        tvInfoInferior.setText(version.getDescripcion());
    }
}