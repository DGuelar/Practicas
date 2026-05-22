package com.guelar.preferenciasdialogosnotificaciones;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;
public class MainActividad4 extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad4);

        Button btnFecha = findViewById(R.id.btnSeleccionarFecha);
        Button btnHora  = findViewById(R.id.btnSeleccionarHora);

        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnSeleccionarFecha) {
            mostrarDatePickerDialog();
        } else if (id == R.id.btnSeleccionarHora) {
            mostrarTimePickerDialog();
        }
    }
    private void mostrarDatePickerDialog() {

        final Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes  = calendario.get(Calendar.MONTH);
        int dia  = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                String fechaElegida = String.format(
                        Locale.getDefault(),
                        "Fecha: %02d/%02d/%04d",
                        dayOfMonth,
                        monthOfYear + 1,
                        year
                );

                mostrarToastPersonalizado(fechaElegida);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                listener,
                anio,
                mes,
                dia
        );

        datePickerDialog.show();
    }
    private void mostrarTimePickerDialog() {

        final Calendar calendario = Calendar.getInstance();
        int hora    = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String horaElegida = String.format(
                        Locale.getDefault(),
                        "Hora: %02d:%02d",
                        hourOfDay,
                        minute
                );

                mostrarToastPersonalizado(horaElegida);
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                listener,
                hora,
                minutos,
                true
        );

        timePickerDialog.show();
    }
    private void mostrarToastPersonalizado(String mensaje) {

        LayoutInflater inflater = getLayoutInflater();

        View layoutToast = inflater.inflate(
                R.layout.layout_toast_fecha,
                findViewById(R.id.toast_root)
        );

        TextView tvMensaje = layoutToast.findViewById(R.id.tvToastMensaje);
        tvMensaje.setText(mensaje);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layoutToast);
        toast.show();
    }
}