package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by shuiyanping on 2017/12/23.
 */

public class MyChenView extends View {
    //这个是用来划线的笔  绘制粗线的笔
    int width;
    int height;

    private Paint mPanit;
    public MyChenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPanit=new Paint();
        mPanit.setStrokeWidth(dptpx(10));
        mPanit.setStrokeCap(Paint.Cap.ROUND);
        mPanit.setAntiAlias(true);
        mPanit.setColor(Color.parseColor("#000000"));



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#00dbdc"));

        canvas.translate(width/2,height/2);
        canvas.rotate(30);
        drawOLine(canvas);
       // drawVercailLine(canvas);
    }

    private void drawOLine(Canvas canvas) {
        canvas.drawLine(0,width/2-dptpx(5),0,dptpx(5),mPanit);

    }

    private void drawVercailLine(Canvas canvas) {
        canvas.drawLine(0,-width/2+dptpx(5),0,-dptpx(5),mPanit);
    }

    private int dptpx(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getContext().getResources().getDisplayMetrics());
    }
}
