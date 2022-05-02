package com.nam.bai4tuan5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class GraphicsView extends View {
    Bitmap [] frames = new Bitmap[8];
    int i=0;
    long last_tick=0;
    long period=200;
    Context ctext;
    MediaPlayer mPlayer;
    public GraphicsView(Context context) {
        super(context);
        ctext=context;
        mPlayer = MediaPlayer.create(ctext, R.raw.naruto);
        mPlayer.start();
        mPlayer.setLooping(true);
        frames[0] = BitmapFactory.decodeResource(getResources(),R.drawable.a1);
        frames[1] = BitmapFactory.decodeResource(getResources(),R.drawable.b1);
        frames[2] = BitmapFactory.decodeResource(getResources(),R.drawable.c1);
        frames[3] = BitmapFactory.decodeResource(getResources(),R.drawable.d1);
        frames[4] = BitmapFactory.decodeResource(getResources(),R.drawable.e1);
        frames[5] = BitmapFactory.decodeResource(getResources(),R.drawable.f1);
        frames[6] = BitmapFactory.decodeResource(getResources(),R.drawable.g1);
        frames[7] = BitmapFactory.decodeResource(getResources(),R.drawable.h1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(i<8){
            long time = (System.currentTimeMillis() - last_tick);
            if (time >= period){
                last_tick = System.currentTimeMillis();

                i++;
                postInvalidate();
            }
            else{
                canvas.drawBitmap(frames[i],40,40,new Paint());
                postInvalidate();
            }
        }
        else {
            i = 0;

            postInvalidate();

        }
    }


}
