package com.guelar.persistencia;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActividad109 extends AppCompatActivity {

    private EditText etNombre, etAnio;
    private GridView gridView;
    private SimpleCursorAdapter adaptador;
    private long idSeleccionado = -1;
    private View celdaSeleccionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad109);

        etNombre = findViewById(R.id.etNombre);
        etAnio   = findViewById(R.id.etAnio);
        gridView = findViewById(R.id.gridViewVersiones);
        Button btnAniadir   = findViewById(R.id.btnAniadir);
        Button btnRefrescar = findViewById(R.id.btnRefrescar);
        Button btnEliminar  = findViewById(R.id.btnEliminar);

        String[] from = {AndroidVersionsDbHelper.COL_NOMBRE,
                AndroidVersionsDbHelper.COL_ANIO};
        int[]    to   = {R.id.tvNombreItem, R.id.tvAnioItem};

        adaptador = new SimpleCursorAdapter(this,
                R.layout.item_grid_version, null, from, to, 0);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            idSeleccionado = id;

            if (celdaSeleccionada != null) {
                celdaSeleccionada.setBackgroundColor(0xFFE3F2FD);
            }
            view.setBackgroundColor(0xFFFF6F00);
            celdaSeleccionada = view;

            Cursor cursor = (Cursor) adaptador.getItem(position);
            String nombre = cursor.getString(
                    cursor.getColumnIndexOrThrow(AndroidVersionsDbHelper.COL_NOMBRE));
            String anio = String.valueOf(cursor.getInt(
                    cursor.getColumnIndexOrThrow(AndroidVersionsDbHelper.COL_ANIO)));

            TextView tvSeleccion = findViewById(R.id.tvSeleccion);
            tvSeleccion.setText("Seleccionado: " + nombre + " (" + anio + ")");
        });

        btnAniadir.setOnClickListener(v   -> insertarRegistro());
        btnRefrescar.setOnClickListener(v -> cargarDatos());
        btnEliminar.setOnClickListener(v  -> eliminarSeleccionado());

        cargarDatos();
    }

    private void cargarDatos() {
        Cursor cursor = getContentResolver().query(
                VersionesProvider.CONTENT_URI, null, null, null, null);
        adaptador.changeCursor(cursor);
        celdaSeleccionada = null;
        idSeleccionado    = -1;
        TextView tvSeleccion = findViewById(R.id.tvSeleccion);
        tvSeleccion.setText("Selecciona un elemento para borrarlo:");
    }

    private void insertarRegistro() {
        String nombre = etNombre.getText().toString().trim();
        String anioTxt = etAnio.getText().toString().trim();

        if (nombre.isEmpty() || anioTxt.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int anio = Integer.parseInt(anioTxt);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);

        if (anio < 2008 || anio > anioActual) {
            Toast.makeText(this,
                    "El año debe estar entre 2008 y " + anioActual,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues valores = new ContentValues();
        valores.put(AndroidVersionsDbHelper.COL_NOMBRE, nombre);
        valores.put(AndroidVersionsDbHelper.COL_ANIO,   anio);

        Uri resultado = getContentResolver().insert(VersionesProvider.CONTENT_URI, valores);

        if (resultado != null) {
            Toast.makeText(this, "Versión añadida", Toast.LENGTH_SHORT).show();
            etNombre.setText("");
            etAnio.setText("");
            cargarDatos();
        } else {
            Toast.makeText(this, "Error al añadir", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarSeleccionado() {
        if (idSeleccionado == -1) {
            Toast.makeText(this, "Toca primero una celda del grid",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Uri uriBorrado = ContentUris.withAppendedId(
                VersionesProvider.CONTENT_URI, idSeleccionado);
        int filas = getContentResolver().delete(uriBorrado, null, null);

        if (filas > 0) {
            Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
            cargarDatos();
        } else {
            Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
        }
    }
}