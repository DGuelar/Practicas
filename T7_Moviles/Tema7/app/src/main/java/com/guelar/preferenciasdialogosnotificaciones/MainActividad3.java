package com.guelar.preferenciasdialogosnotificaciones;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class MainActividad3 extends AppCompatActivity
        implements View.OnClickListener {
    private final CharSequence[] diasSemana = {
            "LUNES", "MARTES", "MIÉRCOLES",
            "JUEVES", "VIERNES", "SÁBADO", "DOMINGO"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad3);

        Button btnDialogoBotones = findViewById(R.id.btnDialogoBotones);
        Button btnDialogoListado = findViewById(R.id.btnDialogoListado);

        btnDialogoBotones.setOnClickListener(this);
        btnDialogoListado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnDialogoBotones) {
            mostrarDialogoBotones();
        } else if (id == R.id.btnDialogoListado) {
            mostrarDialogoListado();
        }
    }
    private void mostrarDialogoBotones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("ACTIVIDAD DE DIÁLOGO")
                .setMessage("Toca uno de los Botones")
                .setIcon(R.mipmap.ic_launcher);

        builder.setNegativeButton("NEGATIVO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarToast("TOCADO BOTÓN NEGATIVO");
            }
        });

        builder.setNeutralButton("NEUTRO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarToast("TOCADO BOTÓN NEUTRO");
            }
        });

        builder.setPositiveButton("POSITIVO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarToast("TOCADO BOTÓN POSITIVO");
            }
        });
        builder.create().show();
    }
    private void mostrarDialogoListado() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("ACTIVIDAD DE DIÁLOGO")
                .setIcon(R.mipmap.ic_launcher);

        builder.setItems(diasSemana, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarToast("TOCADO EL " + diasSemana[which]);
            }
        });

        builder.create().show();
    }
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}