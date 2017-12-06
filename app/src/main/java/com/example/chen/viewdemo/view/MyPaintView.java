package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by shuiyanping on 2017/10/30.
 * 用于测试一些画笔
 */

public class MyPaintView extends View {
    private Paint mPaint;

    public MyPaintView(Context context) {
        super(context);
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
    }
}
