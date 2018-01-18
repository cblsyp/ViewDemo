package com.example.chen.viewdemo.view;

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
 * Created by shuiyanping on 2017/12/27.
 */

public class SpeedCricleView extends View {
    private static final String TAG = "chendd debug";
    private int width;
    private int height;
    private int wai_radius;

    private Paint wai_paint;
    private Paint nei_paint;
    private Paint xiao_bpaint;
    private Paint xiao_paint;
    private float pathway_radius;


    public SpeedCricleView(Context context) {
        super(context);
        init();
    }


    public SpeedCricleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        wai_paint = new Paint();
        wai_paint.setAntiAlias(false);
        wai_paint.setStyle(Paint.Style.STROKE);//描边
        wai_paint.setStrokeCap(Paint.Cap.ROUND);
        wai_paint.setStrokeWidth(dptopx(14));//画笔的宽度是14 个dp
        // wai_paint.setStrokeWidth(1f);
        wai_paint.setColor(Color.parseColor("#92e9e9"));

        nei_paint = new Paint();
        nei_paint.setAntiAlias(false);
        nei_paint.setStyle(Paint.Style.STROKE);//描边
        nei_paint.setStrokeCap(Paint.Cap.ROUND);
        nei_paint.setStrokeWidth(dptopx(14));//画笔的宽度是14 个dp
        // wai_paint.setStrokeWidth(1f);
        nei_paint.setColor(Color.parseColor("#00dbdc"));

        xiao_bpaint = new Paint();
        xiao_bpaint.setAntiAlias(false);
        xiao_bpaint.setStyle(Paint.Style.FILL_AND_STROKE);//描边
        xiao_bpaint.setStrokeCap(Paint.Cap.ROUND);
        xiao_bpaint.setStrokeWidth(1);//画笔的宽度是14 个dp
        // wai_paint.setStrokeWidth(1f);
        xiao_bpaint.setColor(Color.parseColor("#ffffff"));

        xiao_paint = new Paint();
        xiao_paint.setAntiAlias(false);
        xiao_paint.setStyle(Paint.Style.STROKE);//描边
        xiao_paint.setStrokeCap(Paint.Cap.ROUND);
        xiao_paint.setStrokeWidth(dptopx(1));//画笔的宽度是14 个dp
        // wai_paint.setStrokeWidth(1f);
        xiao_paint.setColor(Color.parseColor("#00dbdc"));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        wai_radius = Math.min(width, height) / 2;
        pathway_radius = wai_radius - dptopx(15);

        Log.e(TAG, "width:" + width + " height:" + height);
        Log.e(TAG, "wai_width:" + wai_radius);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      //  canvas.drawColor(Color.parseColor("#ffff00"));

        drawWaiCricle(canvas);

        drawpathwayCricle(canvas);

    }

    private void drawpathwayCricle(Canvas canvas) {
        //canvas.translate(width / 2,dptopx(15));
       // canvas.rotate(225);
        canvas.drawCircle(0,-wai_radius+dptopx(15),dptopx(11),xiao_bpaint);
        canvas.drawCircle(0,-wai_radius+dptopx(15),dptopx(9),xiao_paint);
    }

    private void drawWaiCricle(Canvas canvas) {
        canvas.save();
        canvas.translate(width / 2, height / 2);
        //画笔的宽度从中间落下
        canvas.drawCircle(0, 0, wai_radius - dptopx(7), wai_paint);
        Log.e(TAG, "wai_radius-dptopx(7):" + (wai_radius - dptopx(7)));
        canvas.drawCircle(0, 0, wai_radius - dptopx(23), nei_paint);
    //    canvas.restore();

    }

    private float dptopx(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }
}
