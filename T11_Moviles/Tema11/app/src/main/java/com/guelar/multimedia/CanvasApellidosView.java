package com.guelar.multimedia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class CanvasApellidosView extends View {

    private final Paint paint = new Paint();
    private float anguloRotacion = 0f;

    public CanvasApellidosView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAnguloRotacion(float angulo) {
        this.anguloRotacion = angulo;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cx = getWidth()  / 2;
        int cy = getHeight() / 2;

        canvas.drawColor(Color.parseColor("#1A1A2E"));

        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#4FC3F7"));
        paint.setTextSize(80f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("GUELAR", cx, cy - 280, paint);

        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#FF9800"));
        paint.setTextSize(40f);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        canvas.drawText("GUELAR", 40f, cy - 180, paint);

        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#A5D6A7"));
        paint.setTextSize(50f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

        canvas.save();
        Matrix matrizEscala = new Matrix();
        matrizEscala.setScale(2.0f, 0.5f, cx, cy - 80);
        canvas.concat(matrizEscala);
        canvas.drawText("GUELAR", cx, cy - 80, paint);
        canvas.restore();

        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#EF5350"));
        paint.setTextSize(55f);
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setStrikeThruText(true);
        canvas.drawText("GUELAR", getWidth() - 40f, cy + 20, paint);

        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#CE93D8"));
        paint.setTextSize(65f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.MONOSPACE);

        canvas.save();
        canvas.rotate(anguloRotacion, cx, cy + 160);
        canvas.drawText("GUELAR", cx, cy + 160, paint);
        canvas.restore();

        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setTextSize(45f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setShadowLayer(8f, 4f, 4f, Color.parseColor("#9C27B0"));
        canvas.drawText("GUELAR", cx, cy + 280, paint);
    }
}