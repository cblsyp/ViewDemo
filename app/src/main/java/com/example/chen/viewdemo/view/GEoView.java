package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by shuiyanping on 2017/10/28.
 */

public class GEoView extends View {
    Paint paint;
    public GEoView(Context context) {
        super(context);
        init();
    }

    private void init() {
       paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeCap(Paint.Cap.BUTT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        画布的左上角 为（0，0）  这个在画布的变幻中是很重要的
        canvas.drawColor(Color.BLUE);
//        canvas.translate(100,100); 这行代码如果是负100的参数 那么显示的红色的区域会变小  那么说明移动的是canvas
        canvas.drawRect(new Rect(0,0,400,400),paint);
//        canvas.translate(100,100);
////        新的将会吧原来的覆盖 而不是颜色的融合
//        paint.setColor(Color.YELLOW);
//        canvas.drawRect(new Rect(0,0,400,400),paint);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
////        画布的左上角 为（0，0）  这个在画布的变幻中是很重要的
//        canvas.drawColor(Color.BLUE);
//        canvas.translate(100,100);
//        canvas.drawRect(new Rect(0,0,400,400),paint);
//        canvas.translate(100,100);
////        新的将会吧原来的覆盖 而不是颜色的融合
//        paint.setColor(Color.YELLOW);
//        canvas.drawRect(new Rect(0,0,400,400),paint);
//    }
}
