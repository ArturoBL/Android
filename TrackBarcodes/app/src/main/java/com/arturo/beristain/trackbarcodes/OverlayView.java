package com.arturo.beristain.trackbarcodes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


public class OverlayView extends View {

    private Paint BoxPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BoxPaint.setColor(Color.RED);
        BoxPaint.setStyle(Paint.Style.STROKE);
        BoxPaint.setStrokeWidth(5.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        canvas.drawRect(new Rect(0,0,400,100),BoxPaint);
    }

}
