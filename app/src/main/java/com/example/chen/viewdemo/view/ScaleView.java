package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by shuiyanping on 2017/10/28.
 */

public class ScaleView extends View {
    private static final String TAG = "chen debug";
    Paint paint;
    int mCricleLineWidth;
    int textHeight;

    public ScaleView(Context context) {
        super(context);

        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeCap(Paint.Cap.ROUND);
//单位转换 测试  将8个dip转化为像素
        mCricleLineWidth= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics());
//说明本手机的dpi是480
        textHeight= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4,getResources().getDisplayMetrics());
        Log.e(TAG,"mCricleLineWidth:"+mCricleLineWidth);
        Log.e(TAG,"textHeight:"+textHeight);
    }

@Override
    protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(Color.BLUE);
    canvas.drawRect(new Rect(0, 0, 400, 400), paint);
    paint.setColor(Color.YELLOW);
    canvas.rotate(45);
    canvas.drawRect(new Rect(0, 0, 400, 400), paint);

}
//    /**
//     * @param canvas
//     *这里是是画布的缩放   一共有两个方法  一个是以默认的基准点  一个是自己设置基准点
//     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawColor(Color.BLUE);
//
//        canvas.drawRect(new Rect(0,0,400,400),paint);
//
//        canvas.scale(0.25f,0.25f);
//        paint.setColor(Color.YELLOW);
//        canvas.drawRect(new Rect(0,0,400,400),paint);
////        画布状态回滚
//        canvas.restore();
////表述以（200，200）为基准点进行缩放  （也就是中心）  可以用（100，200）测试
////        canvas.scale(0.5f,0.5f,100,100);
//
////以下三句和上一句是同一个意思
//        canvas.translate(100,100);
//        canvas.scale(0.5f,0.5f);
//        canvas.translate(-100,-100);
//
//
//
//        paint.setColor(Color.BLACK);
//        canvas.drawRect(new Rect(0,0,400,400),paint);
//
//
//
//    }
}
