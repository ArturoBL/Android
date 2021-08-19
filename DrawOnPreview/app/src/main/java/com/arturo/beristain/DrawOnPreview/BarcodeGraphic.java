package com.arturo.beristain.drawonpreview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class BarcodeGraphic extends GraphicOverlay.Graphic{
    private static final float BOX_STROKE_WIDTH = 5.0f;
    private Paint mBoxPaint;
    public Rect rect;

    public BarcodeGraphic(GraphicOverlay overlay) {
        super(overlay);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(Color.RED);
        mBoxPaint.setStyle(Paint.Style.STROKE);
        mBoxPaint.setStrokeWidth(BOX_STROKE_WIDTH);
        rect = new Rect(0,0,100,100);
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, mBoxPaint);
    }
}
