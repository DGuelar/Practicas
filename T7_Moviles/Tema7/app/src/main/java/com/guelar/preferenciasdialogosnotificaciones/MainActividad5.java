package com.guelar.preferenciasdialogosnotificaciones;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class MainActividad5 extends AppCompatActivity {
    private static final String CANAL_ID        = "canal_guelar";
    private static final int    NOTIFICACION_ID = 1;

    private NotificationManager notificador;
    private final ActivityResultLauncher<String> pedirPermiso =
            registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(),
                    concedido -> {
                        if (concedido) {
                            lanzarNotificacion();
                        } else {
                            Toast.makeText(this,
                                    "Permiso de notificación denegado",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad5);
        notificador = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        crearCanalNotificacion();
        Button btnNotificacion = findViewById(R.id.btnMostrarNotificacion);
        btnNotificacion.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                boolean permisoConcedido = ContextCompat.checkSelfPermission(
                        this, Manifest.permission.POST_NOTIFICATIONS)
                        == PackageManager.PERMISSION_GRANTED;

                if (permisoConcedido) {
                    lanzarNotificacion();
                } else {
                    pedirPermiso.launch(Manifest.permission.POST_NOTIFICATIONS);
                }

            } else {
                lanzarNotificacion();
            }
        });
    }
    private void crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel canal = new NotificationChannel(
                    CANAL_ID,
                    "Canal David Guelar",
                    NotificationManager.IMPORTANCE_HIGH
            );
            canal.setDescription("Canal de notificaciones de la práctica 7.5");
            notificador.createNotificationChannel(canal);
        }
    }
    private void lanzarNotificacion() {
        Intent intentGoogle = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com"));

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intentGoogle,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CANAL_ID)
                .setSmallIcon(R.drawable.ic_notificacion)
                .setLargeIcon(android.graphics.BitmapFactory.decodeResource(
                        getResources(), R.mipmap.ic_launcher))
                .setContentTitle("AVISO DE NOTIFICACIÓN")
                .setContentText("Toque para ir a Google")
                .setTicker("Aviso: toque para ir a Google")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        notificador.notify(NOTIFICACION_ID, builder.build());
    }
}