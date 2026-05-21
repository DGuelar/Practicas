package com.guelar.controlesbasicos;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Caso5_5Activity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener {
    private TextView tvDiaSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso5_5);

        tvDiaSeleccionado = findViewById(R.id.tvDiaSeleccionado);

        RadioGroup rgDias = findViewById(R.id.rgDias);

        rgDias.setOnCheckedChangeListener(this);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        RadioButton rbSeleccionado = findViewById(checkedId);

        String mensaje = "PULSADO " + rbSeleccionado.getText().toString();

        tvDiaSeleccionado.setText(mensaje);
    }
}