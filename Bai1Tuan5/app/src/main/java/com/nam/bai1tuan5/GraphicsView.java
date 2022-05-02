package com.nam.bai1tuan5;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.graphics.Canvas;
import android.view.View;
import java.lang.*;
public class GraphicsView extends View {

    public GraphicsView(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Rect r = new Rect(100, 50, 1000, 200);
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        canvas.drawRect(r, p);
        invalidate();
    }
}
