package com.guelar.multimedia;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActividad11_7 extends AppCompatActivity
        implements SensorEventListener {

    private TextView tvValores;
    private Button   btnAccel, btnGyro, btnLuz, btnStop;
    private SensorManager sensorManager;
    private Sensor acelerometro, giroscopio, luz;
    private int sensorActivo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_7);

        tvValores = findViewById(R.id.txtValores);
        btnAccel  = findViewById(R.id.btnAcelerometro);
        btnGyro   = findViewById(R.id.btnGiroscopio);
        btnLuz    = findViewById(R.id.btnLuz);
        btnStop   = findViewById(R.id.btnDetener);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        giroscopio    = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        luz           = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        btnAccel.setOnClickListener(v -> activarSensor(Sensor.TYPE_ACCELEROMETER));
        btnGyro.setOnClickListener(v  -> activarSensor(Sensor.TYPE_GYROSCOPE));
        btnLuz.setOnClickListener(v   -> activarSensor(Sensor.TYPE_LIGHT));
        btnStop.setOnClickListener(v  -> detenerMedicion());
    }

    private void activarSensor(int tipo) {
        detenerMedicion();

        Sensor seleccionado = null;
        String nombre       = "";

        switch (tipo) {
            case Sensor.TYPE_ACCELEROMETER:
                seleccionado = acelerometro;
                nombre       = "Acelerómetro";
                break;
            case Sensor.TYPE_GYROSCOPE:
                seleccionado = giroscopio;
                nombre       = "Giroscopio";
                break;
            case Sensor.TYPE_LIGHT:
                seleccionado = luz;
                nombre       = "Luz Ambiental";
                break;
        }

        if (seleccionado == null) {
            Toast.makeText(this, "Sensor no disponible en este dispositivo",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        sensorManager.registerListener(this, seleccionado,
                SensorManager.SENSOR_DELAY_UI);
        sensorActivo = tipo;

        actualizarBotones(false);
        tvValores.setBackgroundColor(Color.parseColor("#E0E0E0"));
        tvValores.setText("Leyendo " + nombre + "...");
    }

    private void detenerMedicion() {
        if (sensorActivo != 0) {
            sensorManager.unregisterListener(this);
            sensorActivo = 0;
            tvValores.setText("Medición detenida.");
            tvValores.setBackgroundColor(Color.parseColor("#E0E0E0"));
            actualizarBotones(true);
        }
    }

    private void actualizarBotones(boolean habilitarInicio) {
        btnAccel.setEnabled(habilitarInicio);
        btnGyro.setEnabled(habilitarInicio);
        btnLuz.setEnabled(habilitarInicio);
        btnStop.setEnabled(!habilitarInicio);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {

            case Sensor.TYPE_ACCELEROMETER:
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                double modulo = Math.sqrt(x * x + y * y + z * z);

                tvValores.setText(String.format(Locale.getDefault(),
                        "ACELERÓMETRO\n\n" +
                                "X: %.2f m/s²\n" +
                                "Y: %.2f m/s²\n" +
                                "Z: %.2f m/s²\n\n" +
                                "Módulo: %.2f m/s²\n" +
                                "(Incluye gravedad ~9.8)",
                        x, y, z, modulo));
                break;

            case Sensor.TYPE_GYROSCOPE:
                float rx = event.values[0];
                float ry = event.values[1];
                float rz = event.values[2];

                tvValores.setText(String.format(Locale.getDefault(),
                        "GIROSCOPIO\n\n" +
                                "Rot. X: %.4f rad/s\n" +
                                "Rot. Y: %.4f rad/s\n" +
                                "Rot. Z: %.4f rad/s",
                        rx, ry, rz));
                break;

            case Sensor.TYPE_LIGHT:
                float lux = event.values[0];

                String descripcion;
                int    colorFondo;

                if (lux < 10) {
                    descripcion = "🌑 Muy oscuro";
                    colorFondo  = Color.parseColor("#B71C1C");
                } else if (lux < 100) {
                    descripcion = "🌒 Oscuro";
                    colorFondo  = Color.parseColor("#E65100");
                } else if (lux < 1000) {
                    descripcion = "🌤 Luz moderada";
                    colorFondo  = Color.parseColor("#F9A825");
                } else {
                    descripcion = "☀ Mucha luz";
                    colorFondo  = Color.parseColor("#2E7D32");
                }

                tvValores.setBackgroundColor(colorFondo);
                tvValores.setText(String.format(Locale.getDefault(),
                        "SENSOR DE LUZ\n\nNivel: %.0f lx\n\n%s",
                        lux, descripcion));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorActivo != 0) {
            activarSensor(sensorActivo);
        }
    }
}