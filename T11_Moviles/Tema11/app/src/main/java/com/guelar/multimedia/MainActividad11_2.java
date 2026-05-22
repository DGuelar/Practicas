package com.guelar.multimedia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActividad11_2 extends AppCompatActivity {

    private static final String URL_IMAGEN =
            "https://upload.wikimedia.org/wikipedia/commons/e/ee/Yamanaka-lake-panoramic.png";

    private ImageView ivPanoramica;
    private boolean usandoFitCenter = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_2);

        ivPanoramica = findViewById(R.id.ivPanoramica);
        Button btnCambiarEscala = findViewById(R.id.btnCambiarEscala);

        cargarImagen();

        btnCambiarEscala.setOnClickListener(v -> {
            usandoFitCenter = !usandoFitCenter;

            if (usandoFitCenter) {
                ivPanoramica.setScaleType(ImageView.ScaleType.FIT_CENTER);
                btnCambiarEscala.setText("fitCenter");
                Toast.makeText(this,
                        "fitCenter: imagen completa con franjas negras",
                        Toast.LENGTH_SHORT).show();
            } else {
                ivPanoramica.setScaleType(ImageView.ScaleType.CENTER_CROP);
                btnCambiarEscala.setText("centerCrop");
                Toast.makeText(this,
                        "centerCrop: rellena la pantalla recortando bordes",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarImagen() {
        Glide.with(this)
                .load(URL_IMAGEN)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .fitCenter()
                .into(ivPanoramica);

        Toast.makeText(this, "Cargando panorámica...", Toast.LENGTH_SHORT).show();
    }
}