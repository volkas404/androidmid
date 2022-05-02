package com.nam.bai2tuan5;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Canvas;
import android.view.View;
import java.lang.*;
public class GraphicsView extends View {
    int x = 0, y = 0, d = 100, r = 50;

    public GraphicsView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (x != 0 && y != 0) {
            int right = x + d;
            int bottom = y + r;
            Rect r = new Rect(x, y, right, bottom);
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.RED);
            canvas.drawRect(r, p);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=(int)event.getX();
        y=(int)event.getY();
        return super.onTouchEvent(event);
    }
}
