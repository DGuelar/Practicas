package com.guelar.multimedia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class VistaTactil extends View {

    private final Paint pincelTexto   = new Paint();
    private final Paint pincelCirculo = new Paint();
    private final Paint pincelDistancia = new Paint();

    private MotionEvent eventoActual;
    private String infoEvento = "Toca la pantalla con varios dedos";

    private final int[] coloresDedos = {
            Color.CYAN,
            Color.parseColor("#FF6F00"),
            Color.parseColor("#76FF03"),
            Color.parseColor("#FF1744"),
            Color.parseColor("#D500F9")
    };

    public VistaTactil(Context context) {
        super(context);
        configurarPinceles();
    }

    private void configurarPinceles() {
        pincelTexto.setColor(Color.WHITE);
        pincelTexto.setTextSize(38f);
        pincelTexto.setAntiAlias(true);

        pincelCirculo.setStyle(Paint.Style.STROKE);
        pincelCirculo.setStrokeWidth(6f);
        pincelCirculo.setAntiAlias(true);

        pincelDistancia.setColor(Color.YELLOW);
        pincelDistancia.setTextSize(50f);
        pincelDistancia.setTextAlign(Paint.Align.CENTER);
        pincelDistancia.setAntiAlias(true);
        pincelDistancia.setFakeBoldText(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        eventoActual = event;
        int accion = event.getActionMasked();

        switch (accion) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                infoEvento = "Dedo detectado";
                break;
            case MotionEvent.ACTION_MOVE:
                infoEvento = "Desplazando...";
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                infoEvento = "Dedo levantado";
                break;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (eventoActual == null) {
            canvas.drawText(infoEvento, 60f, 100f, pincelTexto);
            return;
        }

        int numDedos = eventoActual.getPointerCount();

        canvas.drawText("Dedos: " + numDedos + " — " + infoEvento, 40f, 80f, pincelTexto);

        for (int i = 0; i < numDedos; i++) {
            int   id = eventoActual.getPointerId(i);
            float x  = eventoActual.getX(i);
            float y  = eventoActual.getY(i);

            int colorDedo = coloresDedos[id % coloresDedos.length];
            pincelCirculo.setColor(colorDedo);

            canvas.drawCircle(x, y, 80f, pincelCirculo);
            canvas.drawCircle(x, y, 12f,
                    new Paint() {{ setColor(colorDedo); setAntiAlias(true); }});

            pincelTexto.setColor(colorDedo);
            canvas.drawText("ID:" + id, x - 30f, y - 100f, pincelTexto);
            canvas.drawText("X:" + (int) x, x - 30f, y - 60f, pincelTexto);
            canvas.drawText("Y:" + (int) y, x - 30f, y - 20f, pincelTexto);
        }

        pincelTexto.setColor(Color.WHITE);

        if (numDedos == 2) {
            float x0 = eventoActual.getX(0);
            float y0 = eventoActual.getY(0);
            float x1 = eventoActual.getX(1);
            float y1 = eventoActual.getY(1);

            double distancia = Math.sqrt(
                    Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));

            float centroX = (x0 + x1) / 2f;
            float centroY = (y0 + y1) / 2f;

            canvas.drawText(String.format("%.0f px", distancia),
                    centroX, centroY, pincelDistancia);
        }
    }
}