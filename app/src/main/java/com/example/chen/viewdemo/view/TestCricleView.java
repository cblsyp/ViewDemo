package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.chen.viewdemo.R;

/**
 * Created by shuiyanping on 2017/10/27.
 */

public class TestCricleView extends View {
    Paint paint;
    public TestCricleView(Context context) {
        super(context);
        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.yuanzhu));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(24);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i=0;i<60;i++) {
            canvas.drawLine(canvas.getWidth() / 2, canvas.getHeight() + 10, canvas.getWidth(), canvas.getHeight() + 20, paint);
            canvas.rotate(6);
        }
    }
}
