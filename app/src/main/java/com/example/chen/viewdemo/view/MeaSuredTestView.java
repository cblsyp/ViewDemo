package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.chen.viewdemo.R;

/**
 * Created by shuiyanping on 2017/10/26.
 */

public class MeaSuredTestView extends View {
    private static final String TAG = "chen debug";
    private Paint paint;
    public MeaSuredTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint=new Paint();
        paint.setAlpha(5);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(200,200);
    }

    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"getWidth:"+getWidth()+"\n"+"getHeight:"+getHeight());
        Rect rect=new Rect(getWidth()/2,getHeight()/2,200,150);
        canvas.drawRect(rect,paint);
    }
}
