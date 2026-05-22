package com.guelar.multimedia;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad11_1 extends AppCompatActivity {

    private TextView tvInfo;
    private TextView tvClasificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_1);

        tvInfo          = findViewById(R.id.tvInfoResolucion);
        tvClasificacion = findViewById(R.id.tvClasificacion);

        mostrarInfoPantalla();
    }

    private void mostrarInfoPantalla() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int   anchoPixeles    = metrics.widthPixels;
        int   altoPixeles     = metrics.heightPixels;
        float densidadLogica  = metrics.density;
        int   densidadDpi     = metrics.densityDpi;
        float xdpi            = metrics.xdpi;
        float ydpi            = metrics.ydpi;

        double anchoPulgadas  = anchoPixeles / xdpi;
        double altoPulgadas   = altoPixeles  / ydpi;
        double diagonalPulg   = Math.sqrt(
                Math.pow(anchoPulgadas, 2) + Math.pow(altoPulgadas, 2));

        StringBuilder sb = new StringBuilder();
        sb.append("RESOLUCIÓN DETECTADA:\n");
        sb.append("─────────────────────\n");
        sb.append("Ancho:  ").append(anchoPixeles).append(" px\n");
        sb.append("Alto:   ").append(altoPixeles).append(" px\n\n");
        sb.append("DATOS DE DENSIDAD:\n");
        sb.append("─────────────────────\n");
        sb.append("Densidad:       ").append(densidadDpi).append(" dpi\n");
        sb.append("Factor escala:  ").append(densidadLogica).append("x\n\n");
        sb.append("TAMAÑO FÍSICO (Reto B):\n");
        sb.append("─────────────────────\n");
        sb.append(String.format("Diagonal: %.2f pulgadas", diagonalPulg));

        tvInfo.setText(sb.toString());

        clasificarPantalla(densidadDpi);
    }

    private void clasificarPantalla(int dpi) {
        String categoria;
        int    color;

        if (dpi <= 120) {
            categoria = "ldpi — Baja densidad";
            color     = Color.parseColor("#9E9E9E");
        } else if (dpi <= 160) {
            categoria = "mdpi — Densidad media";
            color     = Color.parseColor("#2196F3");
        } else if (dpi <= 240) {
            categoria = "hdpi — Alta densidad";
            color     = Color.parseColor("#4CAF50");
        } else if (dpi <= 320) {
            categoria = "xhdpi — Muy alta densidad";
            color     = Color.parseColor("#FF9800");
        } else if (dpi <= 480) {
            categoria = "xxhdpi — Extra alta densidad";
            color     = Color.parseColor("#E91E63");
        } else {
            categoria = "xxxhdpi — Ultra alta densidad";
            color     = Color.parseColor("#9C27B0");
        }

        tvClasificacion.setText("Categoría: " + categoria);
        tvClasificacion.setBackgroundColor(color);
        tvClasificacion.setTextColor(Color.WHITE);
    }
}