package com.guelar.persistencia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActividad105 extends AppCompatActivity {

    private EditText etUrl;
    private TextView tvResultado;
    private ProgressBar progressBar;

    private static final String URL_DEFECTO = "https://www.google.com/humans.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad105);

        etUrl       = findViewById(R.id.etUrl);
        tvResultado = findViewById(R.id.tvResultado);
        progressBar = findViewById(R.id.progressBar);
        Button btnConectar = findViewById(R.id.btnConectar);

        btnConectar.setOnClickListener(v -> {
            String urlTexto = etUrl.getText().toString().trim();
            if (urlTexto.isEmpty()) {
                urlTexto = URL_DEFECTO;
                etUrl.setText(urlTexto);
            }
            if (!urlTexto.startsWith("http://") && !urlTexto.startsWith("https://")) {
                urlTexto = "https://" + urlTexto;
                etUrl.setText(urlTexto);
            }
            tvResultado.setText("Conectando...");
            descargarContenido(urlTexto);
        });
    }

    private void descargarContenido(String direccion) {
        progressBar.setVisibility(View.VISIBLE);

        final String urlFinal = direccion;

        Thread hilo = new Thread(() -> {
            final String resultado = realizarPeticionHTTP(urlFinal);

            runOnUiThread(() -> {
                tvResultado.setText(resultado);
                progressBar.setVisibility(View.GONE);
            });
        });

        hilo.start();
    }

    private String realizarPeticionHTTP(String direccionUrl) {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection conexion = null;

        try {
            URL url = new URL(direccionUrl);
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(5000);
            conexion.setReadTimeout(5000);
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("User-Agent", "AndroidApp-Guelar/1.0");

            int codigoRespuesta = conexion.getResponseCode();

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                InputStream entrada = conexion.getInputStream();
                BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
                String linea;
                while ((linea = lector.readLine()) != null) {
                    sb.append(linea).append("\n");
                }
                lector.close();
                entrada.close();
            } else {
                return "Error del servidor. Código HTTP: " + codigoRespuesta;
            }

        } catch (MalformedURLException e) {
            return "Error: URL no válida";
        } catch (IOException e) {
            return "Error: No se pudo conectar.\nComprueba la URL y la conexión a Internet.";
        } catch (Exception e) {
            return "Error desconocido: " + e.getMessage();
        } finally {
            if (conexion != null) {
                conexion.disconnect();
            }
        }

        return sb.toString();
    }
}