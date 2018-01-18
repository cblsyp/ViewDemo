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

import com.example.chen.viewdemo.bean.CPoint;
import com.example.chen.viewdemo.evaluator.PointEvaluator;

/**
 * Created by shuiyanping on 2017/12/22.
 */

public class RotryCricleView extends View {
    private static final String TAG = "chen debug";
    int v_Width,v_Height;
    private CPoint cPoint;
    Paint mPint;
    int width;
    int height;

    public RotryCricleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 画圆的画笔
        mPint = new Paint();
        mPint.setStrokeWidth(1);
        mPint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPint.setColor(Color.parseColor("#ff0000"));


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        v_Height=h;
        v_Width=w;
        width = (int) (w - dptpx(20));
        height = (int) (h - dptpx(20));
        Log.e(TAG, "width:" + w + "height:" + height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#00ffff"));
        canvas.translate(v_Width/2, v_Height / 2);

        if (cPoint == null) {
            Log.e(TAG,"cPoint 为空");
            cPoint = new CPoint(0, -width/2);
            drawCircle(canvas);
            startAnimation();
        }else {
            Log.e(TAG,"cPoint 不为空");
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {

        canvas.drawCircle(cPoint.getX(), cPoint.getY(), dptpx(5), mPint);

    }


    private void startAnimation() {
        Log.e(TAG, "startAnimation: ");
//        Point startPoint = new Point(RADIUS, RADIUS);
        //因为是一个圆 所以起始点的坐标和结束的动画一样
        CPoint startPoint = new CPoint(0, -width / 2);
        CPoint endPoint = new CPoint(0, width / 2);


        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                cPoint = (CPoint) valueAnimator.getAnimatedValue();
                Log.e(TAG, "onAnimationUpdate: CPoint:"+cPoint.toString() );
                invalidate();
            }
        });
        anim.setDuration(3000);
        anim.start();

    }

    public float dptpx(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }
}
