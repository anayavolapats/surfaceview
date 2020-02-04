package com.example.student1.threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    private Paint backgroundPaint;
    private Bitmap bitmap;
    private int towardPointX;
    private int towardPointY;

    public DrawThread(Context context, SurfaceHolder surfaceHolder){
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.space);
        this.surfaceHolder = surfaceHolder;
        backgroundPaint.setColor(Color.GREEN);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void run() {
        int smileX = 0;
        int smileY = 0;
        while(running){
            Canvas canvas = surfaceHolder.lockCanvas();
            if(canvas != null){
                try{
                    canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
                    if(smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 10;
                    if(smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 10;
                    if(smileY + bitmap.getHeight() / 2 < towardPointY) smileX += 10;
                    if(smileY + bitmap.getHeight() / 2 > towardPointY) smileX -= 10;
                }finally{
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
        super.run();
    }
    public void requestStop(){
        running = false;
    }

    public void setTowardPoint(int x, int y){
        towardPointX = x;
        towardPointY = y;
    }
}
