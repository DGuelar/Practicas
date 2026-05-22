package com.guelar.preferenciasdialogosnotificaciones;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActividad2 extends AppCompatActivity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private EditText editTextMensaje;
    private Button   btnMostrarToast;
    private Button   btnMostrarToastSeekBar;
    private SeekBar  seekBarPosicion;
    private int yOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad2);

        editTextMensaje      = findViewById(R.id.editTextMensaje);
        btnMostrarToast      = findViewById(R.id.btnMostrarToast);
        btnMostrarToastSeekBar = findViewById(R.id.btnMostrarToastSeekBar);
        seekBarPosicion      = findViewById(R.id.seekBarPosicion);

        btnMostrarToast.setOnClickListener(this);
        btnMostrarToastSeekBar.setOnClickListener(this);
        seekBarPosicion.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnMostrarToast) {
            String mensaje = editTextMensaje.getText().toString();
            if (mensaje.isEmpty()) {
                mensaje = "¡Escribe algo primero!";
            }
            mostrarToastPersonalizado(mensaje, Gravity.BOTTOM, 0, 150);

        } else if (id == R.id.btnMostrarToastSeekBar) {
            mostrarToastPersonalizado("EJEMPLO POSICIÓN", Gravity.CENTER, 0, yOffset);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        yOffset = progress;
    }

    @Override public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override public void onStopTrackingTouch(SeekBar seekBar)  {}

    private void mostrarToastPersonalizado(String mensaje, int gravity,
                                           int xOffset, int yOffset) {

        LayoutInflater inflater = getLayoutInflater();

        View layoutToast = inflater.inflate(
                R.layout.layout_toast_personalizado,
                (ViewGroup) findViewById(R.id.custom_toast_container)
        );

        TextView tvMensaje = layoutToast.findViewById(R.id.textViewToast);
        tvMensaje.setText(mensaje);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(gravity, xOffset, yOffset);

        toast.setView(layoutToast);
        toast.show();
    }
}