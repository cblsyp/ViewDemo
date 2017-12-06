package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shuiyanping on 2017/10/30.
 */

public class MyCricleView extends View {
    private int mColor= Color.RED;
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    public MyCricleView(Context context) {
        super(context);
        init();
    }

    public MyCricleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCricleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
      mPaint.setColor(mColor);
    }


    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth();
        int height=getHeight();


        int radius=Math.min(width,height)/2;
        canvas.drawCircle(width/2,height/2,radius,mPaint);
    }
}
