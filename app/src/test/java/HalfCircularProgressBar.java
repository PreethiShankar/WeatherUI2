
package com.example.weatherui.test;
import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class HalfCircularProgressBar extends View {

    private int progress;
    private int max;
    private int strokeWidth;
    private int startAngle;
    private int sweepAngle;

    private Paint backgroundPaint;
    private Paint progressPaint;

    public HalfCircularProgressBar(Context context) {
        super(context);
        init();
    }

    public HalfCircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HalfCircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        strokeWidth = 20; // Adjust this value to change the thickness of the progress bar
        startAngle = 180; // Start angle for the half-circle
        sweepAngle = 180; // Sweep angle for the half-circle

        backgroundPaint = new Paint();
        backgroundPaint.setColor(getResources().getColor(android.R.color.white));
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);

        progressPaint = new Paint();
        progressPaint.setColor(getResources().getColor(android.R.color.white));
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int diameter = Math.min(width, height) - strokeWidth;
        int centerX = width / 2;
        int centerY = height / 2;

        RectF rectF = new RectF(centerX - diameter / 2, centerY - diameter / 2,
                centerX + diameter / 2, centerY + diameter / 2);

        // Draw the background circle
        canvas.drawArc(rectF, startAngle, sweepAngle, false, backgroundPaint);

        // Draw the progress arc
        float sweep = ((float) progress / max) * sweepAngle;
        canvas.drawArc(rectF, startAngle, sweep, false, progressPaint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setMax(int max) {
        this.max = max;
    }
}
