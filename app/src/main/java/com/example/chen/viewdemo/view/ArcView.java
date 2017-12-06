package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by shuiyanping on 2017/11/2.
 * 画一个圆弧的测试   主要测试drawArc（）  方法
 */

public class ArcView extends View {

    private Paint mPaint;

    public ArcView(Context context) {
        super(context);
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);

    }


    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

        RectF rectF=new RectF(0,0,getWidth()/2,getHeight()/2);
        canvas.drawArc(rectF,90,180,false,mPaint);
    }
}
