package com.guelar.androidavanzado;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contenido {

    public static ArrayList<Lista_entrada> ENT_LISTA = new ArrayList<>();
    public static Map<String, Lista_entrada> ENT_MAPA = new HashMap<>();

    static {
        ponerEntrada(new Lista_entrada("0", R.drawable.ima1, "DONUTS",
                "El 15 de septiembre de 2009, fue lanzado el SDK de Android 1.6 Donut, basado en el núcleo Linux 2.6.29. En la actualización se incluyen numerosas características nuevas.",
                Color.parseColor("#FF8F00")));
        ponerEntrada(new Lista_entrada("1", R.drawable.ima2, "FROYO",
                "El 20 de mayo de 2010, El SDK de Android 2.2 Froyo (Yogur helado) fue lanzado, basado en el núcleo Linux 2.6.32.",
                Color.parseColor("#F06292")));
        ponerEntrada(new Lista_entrada("2", R.drawable.ima3, "GINGERBREAD",
                "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread (Pan de Jengibre) fue lanzado, basado en el núcleo Linux 2.6.35.",
                Color.parseColor("#81C784")));
        ponerEntrada(new Lista_entrada("3", R.drawable.ima4, "HONEYCOMB",
                "El 22 de febrero de 2011, sale el SDK de Android 3.0 Honeycomb (Panal de Miel). Fue la primera actualización exclusiva para TV y tableta.",
                Color.parseColor("#FFD54F")));
        ponerEntrada(new Lista_entrada("4", R.drawable.ima5, "ICE CREAM",
                "El SDK para Android 4.0.0 Ice Cream Sandwich (Sándwich de Helado), basado en el núcleo de Linux 3.0.1, fue lanzado públicamente el 12 de octubre de 2011.",
                Color.parseColor("#4FC3F7")));
        ponerEntrada(new Lista_entrada("5", R.drawable.ima6, "JELLY BEAN",
                "Google anunció Android 4.1 Jelly Bean (Gomita Confitada o Gominola) en la conferencia del 30 de junio de 2012. Basado en el núcleo de Linux 3.0.31.",
                Color.parseColor("#CE93D8")));
        ponerEntrada(new Lista_entrada("6", R.drawable.ima7, "KITKAT",
                "Su nombre se debe a la chocolatina KitKat, de la empresa internacional Nestlé. Posibilidad de impresión mediante WIFI. WebViews basadas en el motor de Chromium.",
                Color.parseColor("#EF5350")));
        ponerEntrada(new Lista_entrada("7", R.drawable.ima8, "LOLLIPOP",
                "Incluye Material Design, un diseño intrépido, colorido, y sensible interfaz de usuario para las experiencias coherentes e intuitivos en todos los dispositivos.",
                Color.parseColor("#26C6DA")));
    }

    private static void ponerEntrada(Lista_entrada entrada) {
        ENT_LISTA.add(entrada);
        ENT_MAPA.put(entrada.getId(), entrada);
    }
}