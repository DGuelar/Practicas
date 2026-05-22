package com.guelar.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActividad104 extends AppCompatActivity {

    private EditText etCodigo, etNombre, etApellidos, etBuscar;
    private ListView lvLista;
    private ArrayList<String> listaInformacion;
    private ArrayAdapter<String> adaptador;
    private String codigoSeleccionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad104);

        etCodigo    = findViewById(R.id.etCodigo);
        etNombre    = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etBuscar    = findViewById(R.id.etBuscar);
        lvLista     = findViewById(R.id.lvLista);
        Button btnInsertar  = findViewById(R.id.btnInsertar);
        Button btnListar    = findViewById(R.id.btnListar);
        Button btnModificar = findViewById(R.id.btnModificar);
        Button btnBorrar    = findViewById(R.id.btnBorrar);

        btnInsertar.setOnClickListener(v  -> insertarRegistro());
        btnListar.setOnClickListener(v    -> listarRegistros(""));
        btnModificar.setOnClickListener(v -> confirmarAccion("MODIFICAR"));
        btnBorrar.setOnClickListener(v    -> confirmarAccion("BORRAR"));

        lvLista.setOnItemClickListener((parent, view, position, id) -> {
            String elemento = listaInformacion.get(position);
            codigoSeleccionado = elemento.split(" - ")[0].trim();
            buscarPorCodigo(codigoSeleccionado);
        });

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            @Override public void onTextChanged(CharSequence s, int st, int b, int c) {
                listarRegistros(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void insertarRegistro() {
        String cod  = etCodigo.getText().toString().trim();
        String nom  = etNombre.getText().toString().trim();
        String ape  = etApellidos.getText().toString().trim();

        if (cod.isEmpty() || nom.isEmpty() || ape.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion_db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("codigo",    Integer.parseInt(cod));
        registro.put("nombre",    nom);
        registro.put("apellidos", ape);

        long resultado = db.insert("usuarios", null, registro);
        db.close();

        if (resultado == -1) {
            Toast.makeText(this, "Error: código ya existente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
            listarRegistros("");
        }
    }

    private void listarRegistros(String filtro) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion_db", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor;
        if (filtro.isEmpty()) {
            cursor = db.rawQuery("SELECT * FROM usuarios ORDER BY codigo", null);
        } else {
            cursor = db.rawQuery(
                    "SELECT * FROM usuarios WHERE nombre LIKE ? ORDER BY codigo",
                    new String[]{"%" + filtro + "%"});
        }

        listaInformacion = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                listaInformacion.add(
                        cursor.getInt(0) + " - " +
                                cursor.getString(1) + " " +
                                cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listaInformacion);
        lvLista.setAdapter(adaptador);
    }

    private void buscarPorCodigo(String codigo) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion_db", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nombre, apellidos FROM usuarios WHERE codigo=?",
                new String[]{codigo});

        if (cursor.moveToFirst()) {
            etCodigo.setText(codigo);
            etNombre.setText(cursor.getString(0));
            etApellidos.setText(cursor.getString(1));
            etCodigo.setEnabled(false);
        }
        cursor.close();
        db.close();
    }

    private void confirmarAccion(String accion) {
        if (codigoSeleccionado.isEmpty()) {
            Toast.makeText(this, "Selecciona un registro de la lista", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Confirmar " + accion)
                .setMessage("¿Seguro que quieres " + accion.toLowerCase() +
                        " el registro " + codigoSeleccionado + "?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("SÍ", (dialog, which) -> {
                    if (accion.equals("BORRAR")) {
                        borrarRegistro();
                    } else {
                        modificarRegistro();
                    }
                })
                .setNegativeButton("CANCELAR", null)
                .show();
    }

    private void borrarRegistro() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion_db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        int filas = db.delete("usuarios", "codigo=?",
                new String[]{codigoSeleccionado});
        db.close();

        if (filas > 0) {
            Toast.makeText(this, "Registro borrado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se encontró el registro", Toast.LENGTH_SHORT).show();
        }
        codigoSeleccionado = "";
        limpiarCampos();
        listarRegistros("");
    }

    private void modificarRegistro() {
        String nom = etNombre.getText().toString().trim();
        String ape = etApellidos.getText().toString().trim();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion_db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre",    nom);
        valores.put("apellidos", ape);

        int filas = db.update("usuarios", valores, "codigo=?",
                new String[]{codigoSeleccionado});
        db.close();

        if (filas > 0) {
            Toast.makeText(this, "Registro modificado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se pudo modificar", Toast.LENGTH_SHORT).show();
        }
        codigoSeleccionado = "";
        limpiarCampos();
        listarRegistros("");
    }

    private void limpiarCampos() {
        etCodigo.setText("");
        etNombre.setText("");
        etApellidos.setText("");
        etCodigo.setEnabled(true);
        codigoSeleccionado = "";
    }
}