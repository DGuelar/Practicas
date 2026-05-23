package com.guelar.breakoutgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    private Thread     hiloJuego;
    private SurfaceHolder holder;
    private volatile boolean corriendo = false;
    private volatile boolean enPausa   = true;

    private static final long INTERVALO_FRAME = 1000 / 60;

    private enum Estado { INICIO, JUGANDO, GAME_OVER, VICTORIA }
    private Estado estadoActual = Estado.INICIO;

    private int anchoPantalla, altoPantalla;

    private float bolaX, bolaY;
    private float bolaVelX, bolaVelY;
    private static final float RADIO_BOLA = 22f;
    private int frameActualBola = 0;
    private long ultimoCambioBola = 0;
    private static final int[] COLORES_BOLA = {
            Color.WHITE, Color.YELLOW, Color.CYAN, Color.parseColor("#FF6F00")};

    private float paletaX, paletaY;
    private static final float ANCHO_PALETA = 220f;
    private static final float ALTO_PALETA  = 28f;

    private static final int FILAS_LADRILLOS    = 5;
    private static final int COLUMNAS_LADRILLOS = 7;
    private static final int MARGEN_LADRILLO    = 6;
    private int anchoLadrillo, altoLadrillo;
    private boolean[][] ladrillos;
    private int[][]     puntajesLadrillo;

    private static final int[] COLORES_FILA = {
            Color.parseColor("#EF5350"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FFEE58"),
            Color.parseColor("#66BB6A"),
            Color.parseColor("#42A5F5")
    };
    private static final int[] PUNTOS_FILA = {50, 40, 30, 20, 10};

    private int puntuacion = 0;
    private int vidas      = 3;
    private int maxScore   = 0;
    private static final String PREF_MAX = "max_score";

    private SoundPool soundPool;
    private int sonidoRebote  = 0;
    private int sonidoLadrillo = 0;

    private Paint pincel;
    private SharedPreferences preferencias;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        pincel = new Paint();
        pincel.setAntiAlias(true);
        preferencias = context.getSharedPreferences("breakout_prefs", Context.MODE_PRIVATE);
        maxScore = preferencias.getInt(PREF_MAX, 0);
        iniciarSonidos();
        setFocusable(true);
    }

    private void iniciarSonidos() {
        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(attrs)
                .build();
        try {
            sonidoRebote   = soundPool.load(getContext(), R.raw.sonido_rebote,   1);
            sonidoLadrillo = soundPool.load(getContext(), R.raw.sonido_ladrillo, 1);
        } catch (Exception ignored) {}
    }

    private void reproducir(int sonido) {
        if (soundPool != null && sonido != 0) {
            soundPool.play(sonido, 1f, 1f, 1, 0, 1f);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        anchoPantalla = getWidth();
        altoPantalla  = getHeight();
        calcularDimensionesLadrillos();
        inicializarPartida();
        corriendo = true;
        enPausa   = false;
        hiloJuego = new Thread(this);
        hiloJuego.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla  = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        detenerHilo();
    }

    private void detenerHilo() {
        corriendo = false;
        enPausa   = true;
        if (hiloJuego != null) {
            try {
                hiloJuego.join(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            hiloJuego = null;
        }
    }

    public void pausarJuego() {
        enPausa = true;
    }

    public void reanudarJuego() {
        if (corriendo) enPausa = false;
    }

    private void calcularDimensionesLadrillos() {
        int areaAncho = anchoPantalla - MARGEN_LADRILLO * 2;
        anchoLadrillo = (areaAncho - MARGEN_LADRILLO * (COLUMNAS_LADRILLOS - 1))
                / COLUMNAS_LADRILLOS;
        altoLadrillo  = 48;
    }

    private void inicializarPartida() {
        puntuacion = 0;
        vidas      = 3;
        ladrillos  = new boolean[FILAS_LADRILLOS][COLUMNAS_LADRILLOS];
        puntajesLadrillo = new int[FILAS_LADRILLOS][COLUMNAS_LADRILLOS];

        for (int f = 0; f < FILAS_LADRILLOS; f++) {
            for (int c = 0; c < COLUMNAS_LADRILLOS; c++) {
                ladrillos[f][c]      = true;
                puntajesLadrillo[f][c] = PUNTOS_FILA[f];
            }
        }
        colocarPaleta();
        lanzarBola();
        estadoActual = Estado.JUGANDO;
    }

    private void colocarPaleta() {
        paletaX = anchoPantalla / 2f - ANCHO_PALETA / 2f;
        paletaY = altoPantalla - 180f;
    }

    private void lanzarBola() {
        bolaX    = anchoPantalla / 2f;
        bolaY    = paletaY - RADIO_BOLA - 10f;
        bolaVelX = 12f;
        bolaVelY = -14f;
    }

    @Override
    public void run() {
        while (corriendo) {
            long inicio = System.currentTimeMillis();

            if (!enPausa && estadoActual == Estado.JUGANDO) {
                actualizar();
            }

            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    synchronized (holder) {
                        dibujar(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }

            long transcurrido = System.currentTimeMillis() - inicio;
            long espera = INTERVALO_FRAME - transcurrido;
            if (espera > 0) {
                try { Thread.sleep(espera); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }
    }

    private void actualizar() {
        bolaX += bolaVelX;
        bolaY += bolaVelY;

        if (bolaX - RADIO_BOLA <= 0) {
            bolaX    = RADIO_BOLA;
            bolaVelX = -bolaVelX;
            reproducir(sonidoRebote);
        }
        if (bolaX + RADIO_BOLA >= anchoPantalla) {
            bolaX    = anchoPantalla - RADIO_BOLA;
            bolaVelX = -bolaVelX;
            reproducir(sonidoRebote);
        }
        if (bolaY - RADIO_BOLA <= 100f) {
            bolaY    = 100f + RADIO_BOLA;
            bolaVelY = -bolaVelY;
            reproducir(sonidoRebote);
        }

        if (bolaY + RADIO_BOLA >= altoPantalla) {
            vidas--;
            reproducir(sonidoRebote);
            if (vidas <= 0) {
                guardarRecord();
                estadoActual = Estado.GAME_OVER;
            } else {
                lanzarBola();
            }
            return;
        }

        RectF rectBola = new RectF(
                bolaX - RADIO_BOLA, bolaY - RADIO_BOLA,
                bolaX + RADIO_BOLA, bolaY + RADIO_BOLA);
        RectF rectPaleta = new RectF(paletaX, paletaY,
                paletaX + ANCHO_PALETA, paletaY + ALTO_PALETA);

        if (RectF.intersects(rectBola, rectPaleta)) {
            bolaY    = paletaY - RADIO_BOLA;
            bolaVelY = -Math.abs(bolaVelY);
            float centroRelativo = (bolaX - paletaX) / ANCHO_PALETA;
            bolaVelX = (centroRelativo - 0.5f) * 28f;
            reproducir(sonidoRebote);
        }

        comprobarColisionLadrillos(rectBola);

        long ahora = System.currentTimeMillis();
        if (ahora - ultimoCambioBola > 120) {
            frameActualBola = (frameActualBola + 1) % COLORES_BOLA.length;
            ultimoCambioBola = ahora;
        }
    }

    private void comprobarColisionLadrillos(RectF rectBola) {
        int topOffset = 120 + MARGEN_LADRILLO;

        for (int f = 0; f < FILAS_LADRILLOS; f++) {
            for (int c = 0; c < COLUMNAS_LADRILLOS; c++) {
                if (!ladrillos[f][c]) continue;

                int lx = MARGEN_LADRILLO + c * (anchoLadrillo + MARGEN_LADRILLO);
                int ly = topOffset  + f * (altoLadrillo  + MARGEN_LADRILLO);

                RectF rectLadrillo = new RectF(lx, ly, lx + anchoLadrillo, ly + altoLadrillo);

                if (!RectF.intersects(rectBola, rectLadrillo)) continue;

                ladrillos[f][c] = false;
                puntuacion += puntajesLadrillo[f][c];
                reproducir(sonidoLadrillo);

                float solapX = Math.min(rectBola.right,  rectLadrillo.right)
                        - Math.max(rectBola.left,   rectLadrillo.left);
                float solapY = Math.min(rectBola.bottom, rectLadrillo.bottom)
                        - Math.max(rectBola.top,    rectLadrillo.top);

                if (solapX < solapY) {
                    bolaVelX = -bolaVelX;
                    // Sacamos la bola del ladrillo para evitar doble colisión
                    bolaX += bolaVelX > 0 ? solapX : -solapX;
                } else {
                    bolaVelY = -bolaVelY;
                    bolaY += bolaVelY > 0 ? solapY : -solapY;
                }

                if (quedan0Ladrillos()) {
                    guardarRecord();
                    estadoActual = Estado.VICTORIA;
                }
                return;
            }
        }
    }

    private boolean quedan0Ladrillos() {
        for (boolean[] fila : ladrillos) {
            for (boolean ladrillo : fila) {
                if (ladrillo) return false;
            }
        }
        return true;
    }

    private void guardarRecord() {
        if (puntuacion > maxScore) {
            maxScore = puntuacion;
            preferencias.edit().putInt(PREF_MAX, maxScore).apply();
        }
    }

    private void dibujar(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#0D1B2A"));

        switch (estadoActual) {
            case INICIO:
                dibujarPantallaInicio(canvas);
                break;
            case JUGANDO:
                dibujarJuego(canvas);
                break;
            case GAME_OVER:
                dibujarPantallaFin(canvas, false);
                break;
            case VICTORIA:
                dibujarPantallaFin(canvas, true);
                break;
        }
    }

    private void dibujarJuego(Canvas canvas) {
        dibujarHUD(canvas);
        dibujarLadrillos(canvas);
        dibujarPaleta(canvas);
        dibujarBola(canvas);
    }

    private void dibujarHUD(Canvas canvas) {
        pincel.setColor(Color.WHITE);
        pincel.setTextSize(36f);
        pincel.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("PUNTOS: " + puntuacion, 20f, 70f, pincel);

        pincel.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("RECORD: " + maxScore, anchoPantalla - 20f, 70f, pincel);

        pincel.setTextAlign(Paint.Align.CENTER);
        String corazones = "♥".repeat(vidas) + "♡".repeat(Math.max(0, 3 - vidas));
        pincel.setColor(Color.parseColor("#EF5350"));
        canvas.drawText(corazones, anchoPantalla / 2f, 70f, pincel);

        pincel.setColor(Color.parseColor("#37474F"));
        canvas.drawLine(0f, 100f, anchoPantalla, 100f, pincel);
    }

    private void dibujarLadrillos(Canvas canvas) {
        int topOffset = 120 + MARGEN_LADRILLO;

        for (int f = 0; f < FILAS_LADRILLOS; f++) {
            for (int c = 0; c < COLUMNAS_LADRILLOS; c++) {
                if (!ladrillos[f][c]) continue;

                int lx = MARGEN_LADRILLO + c * (anchoLadrillo + MARGEN_LADRILLO);
                int ly = topOffset  + f * (altoLadrillo  + MARGEN_LADRILLO);

                pincel.setColor(COLORES_FILA[f]);
                canvas.drawRoundRect(lx, ly, lx + anchoLadrillo, ly + altoLadrillo,
                        8f, 8f, pincel);

                pincel.setColor(Color.argb(80, 255, 255, 255));
                pincel.setTextSize(22f);
                pincel.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(String.valueOf(puntajesLadrillo[f][c]),
                        lx + anchoLadrillo / 2f, ly + altoLadrillo / 2f + 8f, pincel);
            }
        }
    }

    private void dibujarPaleta(Canvas canvas) {
        pincel.setColor(Color.parseColor("#90CAF9"));
        canvas.drawRoundRect(paletaX, paletaY,
                paletaX + ANCHO_PALETA, paletaY + ALTO_PALETA,
                14f, 14f, pincel);

        pincel.setColor(Color.argb(100, 255, 255, 255));
        canvas.drawRoundRect(paletaX + 8f, paletaY + 4f,
                paletaX + ANCHO_PALETA - 8f, paletaY + 14f,
                6f, 6f, pincel);
    }

    private void dibujarBola(Canvas canvas) {
        pincel.setColor(COLORES_BOLA[frameActualBola]);
        canvas.drawCircle(bolaX, bolaY, RADIO_BOLA, pincel);

        pincel.setColor(Color.argb(120, 255, 255, 255));
        canvas.drawCircle(bolaX - 6f, bolaY - 6f, RADIO_BOLA / 3f, pincel);
    }

    private void dibujarPantallaInicio(Canvas canvas) {
        pincel.setColor(Color.parseColor("#42A5F5"));
        pincel.setTextSize(100f);
        pincel.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("BREAKOUT", anchoPantalla / 2f, altoPantalla / 2f - 120f, pincel);

        pincel.setColor(Color.WHITE);
        pincel.setTextSize(40f);
        canvas.drawText("David Guelar", anchoPantalla / 2f, altoPantalla / 2f - 40f, pincel);

        pincel.setColor(Color.parseColor("#80CBC4"));
        pincel.setTextSize(48f);
        canvas.drawText("Toca para empezar", anchoPantalla / 2f, altoPantalla / 2f + 80f, pincel);

        pincel.setColor(Color.parseColor("#90CAF9"));
        pincel.setTextSize(34f);
        canvas.drawText("Record: " + maxScore, anchoPantalla / 2f, altoPantalla / 2f + 180f, pincel);
    }

    private void dibujarPantallaFin(Canvas canvas, boolean victoria) {
        pincel.setTextAlign(Paint.Align.CENTER);

        if (victoria) {
            pincel.setColor(Color.parseColor("#FFEE58"));
            pincel.setTextSize(90f);
            canvas.drawText("¡VICTORIA!", anchoPantalla / 2f, altoPantalla / 2f - 140f, pincel);
        } else {
            pincel.setColor(Color.parseColor("#EF5350"));
            pincel.setTextSize(90f);
            canvas.drawText("GAME OVER", anchoPantalla / 2f, altoPantalla / 2f - 140f, pincel);
        }

        pincel.setColor(Color.WHITE);
        pincel.setTextSize(52f);
        canvas.drawText("Puntuación: " + puntuacion, anchoPantalla / 2f,
                altoPantalla / 2f - 30f, pincel);

        if (puntuacion >= maxScore) {
            pincel.setColor(Color.parseColor("#FFEE58"));
            canvas.drawText("¡NUEVO RECORD!", anchoPantalla / 2f,
                    altoPantalla / 2f + 50f, pincel);
        } else {
            pincel.setColor(Color.parseColor("#90CAF9"));
            canvas.drawText("Record: " + maxScore, anchoPantalla / 2f,
                    altoPantalla / 2f + 50f, pincel);
        }

        pincel.setColor(Color.parseColor("#80CBC4"));
        pincel.setTextSize(44f);
        canvas.drawText("Toca para reiniciar", anchoPantalla / 2f,
                altoPantalla / 2f + 160f, pincel);
    }

    @Override
    public boolean onTouchEvent(MotionEvent evento) {
        float x = evento.getX();

        switch (estadoActual) {
            case INICIO:
                if (evento.getAction() == MotionEvent.ACTION_DOWN) {
                    estadoActual = Estado.JUGANDO;
                }
                break;

            case JUGANDO:
                paletaX = x - ANCHO_PALETA / 2f;
                paletaX = Math.max(0f, Math.min(paletaX, anchoPantalla - ANCHO_PALETA));
                break;

            case GAME_OVER:
            case VICTORIA:
                if (evento.getAction() == MotionEvent.ACTION_DOWN) {
                    inicializarPartida();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        detenerHilo();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}