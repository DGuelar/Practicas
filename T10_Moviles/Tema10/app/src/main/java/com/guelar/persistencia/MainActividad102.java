package com.guelar.persistencia;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActividad102 extends AppCompatActivity {

    private static final String NOMBRE_FICHERO     = "notas_guelar.txt";
    private static final String NOMBRE_BITACORA    = "bitacora_guelar.txt";

    private EditText etContenido;
    private TextView tvContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad102);

        etContenido = findViewById(R.id.etContenido);
        tvContenido = findViewById(R.id.tvContenido);
        Button btnGuardar   = findViewById(R.id.btnGuardar);
        Button btnRecuperar = findViewById(R.id.btnRecuperar);
        Button btnBitacora  = findViewById(R.id.btnBitacora);

        btnGuardar.setOnClickListener(v -> guardarFichero());
        btnRecuperar.setOnClickListener(v -> recuperarFichero());
        btnBitacora.setOnClickListener(v -> guardarEnBitacora());
    }

    private void guardarFichero() {
        String texto = etContenido.getText().toString();
        if (texto.isEmpty()) {
            Toast.makeText(this, "Escribe algo primero", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            OutputStreamWriter fichero = new OutputStreamWriter(
                    openFileOutput(NOMBRE_FICHERO, Context.MODE_PRIVATE));
            fichero.write(texto);
            fichero.close();
            etContenido.setText("");
            Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        }
    }

    private void recuperarFichero() {
        try {
            InputStreamReader fichero = new InputStreamReader(
                    openFileInput(NOMBRE_FICHERO));
            BufferedReader br = new BufferedReader(fichero);
            StringBuilder todo = new StringBuilder();
            String linea = br.readLine();
            while (linea != null) {
                todo.append(linea).append("\n");
                linea = br.readLine();
            }
            br.close();
            fichero.close();
            tvContenido.setText(todo.toString());
        } catch (IOException e) {
            Toast.makeText(this, "No se encontró el fichero", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarEnBitacora() {
        String texto = etContenido.getText().toString();
        if (texto.isEmpty()) {
            Toast.makeText(this, "Escribe algo primero", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            String fechaHora = new SimpleDateFormat(
                    "dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
            String entrada = "[" + fechaHora + "] " + texto + "\n";

            OutputStreamWriter fichero = new OutputStreamWriter(
                    openFileOutput(NOMBRE_BITACORA, Context.MODE_APPEND));
            fichero.write(entrada);
            fichero.close();

            etContenido.setText("");
            recuperarBitacora();
            Toast.makeText(this, "Entrada añadida a la bitácora", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error al escribir en bitácora", Toast.LENGTH_SHORT).show();
        }
    }

    private void recuperarBitacora() {
        try {
            InputStreamReader fichero = new InputStreamReader(
                    openFileInput(NOMBRE_BITACORA));
            BufferedReader br = new BufferedReader(fichero);
            StringBuilder todo = new StringBuilder();
            String linea = br.readLine();
            while (linea != null) {
                todo.append(linea).append("\n");
                linea = br.readLine();
            }
            br.close();
            fichero.close();
            tvContenido.setText(todo.toString());
        } catch (IOException e) {
            tvContenido.setText("Bitácora vacía");
        }
    }
}