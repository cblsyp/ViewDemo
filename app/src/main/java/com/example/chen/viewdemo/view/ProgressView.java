package com.example.chen.viewdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by shuiyanping on 2017/12/23.
 * 通过  这个例子  知道Panit的Cap   的起始是一个额外的半圆   圆的半径是线宽的一半
 */

public class ProgressView extends View {
    private int processWidth=dptpx(30);
    private static final String TAG="chen debug";
    //划进度背景线的笔
    private Paint mPaint;
    //画进度的笔
    private Paint paint;
    int width;
    int height;

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setColor(Color.parseColor("#00dbdc"));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);


        paint=new Paint();
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
        Log.e(TAG, "onSizeChanged:w:"+w+" h:"+h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0,height/2);
        canvas.drawColor(Color.parseColor("#ffffff"));
        drawbackgroundLine(canvas);
        drawProcessLine(canvas);
    }

    public void setProcess(float process){
        if (process>1||process<0){
            throw  new IllegalArgumentException("the process must be ...");
        }
        //对盖帽进行处理
        int processAnimationWidth= (int) (width*process-height/2);
        //因为这里执行的动画是在局域变量中  所以jvm 会自动的帮我们释放内存
        ValueAnimator animator=ValueAnimator.ofInt(0,processAnimationWidth);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                processWidth= (int) animation.getAnimatedValue();
                //当值不断的改变的时候  我们不断的重新啊绘制View
                invalidate();
            }
        });
        animator.start();

    }

    private void drawProcessLine(Canvas canvas) {
        Log.e(TAG, "drawProcessLine: ");
        paint.setStrokeWidth(height-60);
        canvas.drawLine(30,0,processWidth,0,paint);
    }

    private void drawbackgroundLine(Canvas canvas) {
        mPaint.setStrokeWidth(height-60);
        canvas.drawLine(30,0,width-
                (60),0,mPaint);
    }

    private int dptpx(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getContext().getResources().getDisplayMetrics());
    }
}
