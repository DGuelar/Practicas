package com.guelar.persistencia;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActividad108 extends AppCompatActivity {

    private static final int CODIGO_PERMISO = 100;
    private TextView tvListaContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad108);

        tvListaContactos = findViewById(R.id.tvListaContactos);
        Button btnCargar = findViewById(R.id.btnCargarContactos);

        btnCargar.setOnClickListener(v -> solicitarPermisoYCargar());
    }

    private void solicitarPermisoYCargar() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            leerContactos();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    CODIGO_PERMISO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PERMISO) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                leerContactos();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void leerContactos() {
        StringBuilder builder = new StringBuilder();
        ContentResolver resolver = getContentResolver();

        Uri uriContactos = ContactsContract.Contacts.CONTENT_URI;
        String[] proyeccion = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER
        };
        String orden = ContactsContract.Contacts.DISPLAY_NAME + " ASC";

        try (Cursor cursor = resolver.query(uriContactos, proyeccion, null, null, orden)) {

            if (cursor != null && cursor.getCount() > 0) {

                int indiceId     = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                int indiceNombre = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                int indiceTlf    = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);

                while (cursor.moveToNext()) {
                    String id     = cursor.getString(indiceId);
                    String nombre = cursor.getString(indiceNombre);
                    int tieneTlf  = cursor.getInt(indiceTlf);

                    builder.append("ID: ").append(id).append("\n");
                    builder.append("Nombre: ").append(nombre).append("\n");

                    if (tieneTlf > 0) {
                        String numero = obtenerNumeroTelefono(resolver, id);
                        builder.append("📞 ").append(numero).append("\n");
                    } else {
                        builder.append("Sin teléfono\n");
                    }

                    builder.append("-------------------\n");
                }

                tvListaContactos.setText(builder.toString());

            } else {
                tvListaContactos.setText("No hay contactos en la agenda.");
            }

        } catch (Exception e) {
            tvListaContactos.setText("Error al leer: " + e.getMessage());
        }
    }

    private String obtenerNumeroTelefono(ContentResolver resolver, String contactId) {
        String numero = "[Sin número]";

        String[] proyeccionTlf = {
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        String seleccion = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";

        try (Cursor cursorTlf = resolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                proyeccionTlf,
                seleccion,
                new String[]{contactId},
                null)) {

            if (cursorTlf != null && cursorTlf.moveToFirst()) {
                int indiceNumero = cursorTlf.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER);
                numero = cursorTlf.getString(indiceNumero);
            }
        } catch (Exception e) {
            numero = "[Error al leer número]";
        }

        return numero;
    }
}