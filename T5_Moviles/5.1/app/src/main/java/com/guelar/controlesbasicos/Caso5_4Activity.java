package com.guelar.controlesbasicos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Caso5_4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso5_4);

        // Recogemos cada EditText por su ID
        EditText etTelefono = findViewById(R.id.etTelefono);
        EditText etNumero   = findViewById(R.id.etNumero);
        EditText etEmail    = findViewById(R.id.etEmail);
        EditText etPin      = findViewById(R.id.etPin);

        Button   btnMostrar  = findViewById(R.id.btnMostrar);
        TextView tvResultado = findViewById(R.id.tvResultado);

        btnMostrar.setOnClickListener(v -> {

            // getText().toString() convierte el contenido del EditText en un String normal
            String telefono = etTelefono.getText().toString();
            String numero   = etNumero.getText().toString();
            String email    = etEmail.getText().toString();
            String pin      = etPin.getText().toString();

            // Comprobamos que al menos un campo tiene algo escrito
            if (telefono.isEmpty() && numero.isEmpty() && email.isEmpty() && pin.isEmpty()) {
                tvResultado.setText(getString(R.string.resultado_vacio));
                return;
            }

            // Construimos el texto de resultado con los datos introducidos
            String resultado =
                    "📞 Teléfono: " + (telefono.isEmpty() ? "—" : telefono) + "\n" +
                            "🔢 Número:   " + (numero.isEmpty()   ? "—" : numero)   + "\n" +
                            "📧 Email:    " + (email.isEmpty()    ? "—" : email)    + "\n" +
                            "🔐 PIN:      " + (pin.isEmpty()      ? "—" : pin);

            tvResultado.setText(resultado);
        });
    }
}