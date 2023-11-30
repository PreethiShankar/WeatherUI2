package com.example.weatherui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HalfCircularProgressBar extends View {

    private int progress;
    private int max;
    private int strokeWidth;
    private int startAngle;
    private int sweepAngle;

    private Paint backgroundPaint;
    private Paint progressPaint;
    private Paint textPaint;
    private String temperatureText = "";

    private String locationText = "Tempe";
    private ImageView weatherImageView;

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
        strokeWidth = 20;
        startAngle = 180;
        sweepAngle = 180;

        backgroundPaint = new Paint();
        backgroundPaint.setColor(getResources().getColor(android.R.color.darker_gray));
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);

        progressPaint = new Paint();
        progressPaint.setColor(Color.WHITE);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(strokeWidth);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);


        int minSize = Math.min(width, height);
        setMeasuredDimension(minSize, minSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int diameter = Math.min(width, height) - strokeWidth;
        int centerX = width / 2;
        int centerY = height / 2;
        int increasedRadius = (int) (diameter / 2);

        RectF rectF = new RectF(centerX - increasedRadius, centerY - increasedRadius,
                centerX + increasedRadius, centerY + increasedRadius);


        canvas.drawArc(rectF, startAngle, sweepAngle, false, backgroundPaint);


        float sweep = ((float) progress / max) * sweepAngle;
        canvas.drawArc(rectF, startAngle, sweep, false, progressPaint);


        int locationTextX = getWidth() / 2;
        int locationTextY = getHeight() / 2 - 100;
        textPaint.setTextSize(80);
        canvas.drawText(locationText, locationTextX, locationTextY, textPaint);


        int temperatureTextX = getWidth() / 2;
        int temperatureTextY = getHeight() / 2 + 50;
        textPaint.setTextSize(140);
        canvas.drawText(temperatureText, temperatureTextX, temperatureTextY, textPaint);


        weatherImageView = new ImageView(getContext());
        weatherImageView.setImageResource(R.drawable.sunny); // Assume 'sunny.xml' is a drawable resource
        weatherImageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setTemperatureText(String temperatureText) {
        this.temperatureText = temperatureText;
        invalidate();
    }

    // Method to set temperature from MainActivity
    public void setTemperature(int temperature) {
        this.temperatureText = temperature + "°F";
        invalidate();
    }

}
