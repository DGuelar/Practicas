package com.guelar.androidavanzado;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;

public class CronometroService extends Service {

    private final IBinder binder = new LocalBinder();

    private long startTime = 0L;
    private long timeBuff  = 0L;
    private long updateTime = 0L;
    private boolean isRunning = false;

    public class LocalBinder extends Binder {
        public CronometroService getService() {
            return CronometroService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void iniciarCronometro() {
        if (!isRunning) {
            startTime = SystemClock.uptimeMillis();
            isRunning = true;
        }
    }

    public void pausarCronometro() {
        if (isRunning) {
            timeBuff += SystemClock.uptimeMillis() - startTime;
            isRunning = false;
        }
    }

    public void reiniciarCronometro() {
        startTime  = 0L;
        timeBuff   = 0L;
        updateTime = 0L;
        isRunning  = false;
    }

    public boolean estaCorreindo() {
        return isRunning;
    }

    public double obtenerTiempoTranscurrido() {
        if (isRunning) {
            updateTime = timeBuff + (SystemClock.uptimeMillis() - startTime);
        } else {
            updateTime = timeBuff;
        }
        return updateTime;
    }
}