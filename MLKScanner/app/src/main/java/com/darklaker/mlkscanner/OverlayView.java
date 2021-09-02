package com.darklaker.mlkscanner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashSet;
import java.util.Set;


public class OverlayView extends View {

    private Paint BoxPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Set<Point[]> points = new HashSet<>();

    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BoxPaint.setColor(Color.RED);
        BoxPaint.setStyle(Paint.Style.STROKE);
        BoxPaint.setStrokeWidth(5.0f);
    }

    public void add(Point[] pts) {
        points.add(pts);
        postInvalidate();
    }

    public void clear() {
        points.clear();
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        for (Point[] pts : points) {

            canvas.drawLine((float)pts[0].x, (float)pts[0].y, (float)pts[1].x, (float)pts[1].y,BoxPaint);
            canvas.drawLine((float)pts[1].x, (float)pts[1].y, (float)pts[2].x, (float)pts[2].y,BoxPaint);
            canvas.drawLine((float)pts[2].x, (float)pts[2].y, (float)pts[3].x, (float)pts[3].y, BoxPaint);
            canvas.drawLine((float)pts[3].x,(float) pts[3].y, (float)pts[0].x, (float)pts[0].y, BoxPaint);
            //canvas.drawRect(rect,BoxPaint);

        }

    }

}