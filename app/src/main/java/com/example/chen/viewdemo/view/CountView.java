package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by shuiyanping on 2017/10/26.
 */

public class CountView extends View implements View.OnClickListener {
    private static final String TAG = "chen debug";
    private Paint mPaint;
    private Rect mBounds;
    private int mCount;

    public CountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds=new Rect();
        setOnClickListener(this);
    }

    /**
     2* @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        /**
         下面的参数需要解释
         一般来说 我们的实际的宽高就是我们测量的宽高
         1。如果我们在XML代码中设置宽高是200dp   那么我们这里测量就是得到的结果结果就是600px
         1。如果我们设置的是200px  那么我们这里的结果是200px
         3。如果我们设置的是macth_parent  和wrap_content  那么我们这里得到的结果是  1080px 1701px
         */

        Log.e(TAG,"getwidth:"+getWidth()+"\n"+"getheight:"+getHeight());
        Log.e(TAG,"getMeasuredWidth:"+getMeasuredWidth()+"   getMeasuredHeight:"+getMeasuredHeight());
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(30);

        String text=String.valueOf(mCount);
        mPaint.getTextBounds(text,0,text.length(),mBounds);

        float textWidth=mBounds.width();
        float textHeight=mBounds.height();

        canvas.drawText(text,getWidth()/2-textWidth/2,getHeight()/2+textHeight/2,mPaint);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        mCount++;
//         表示重新绘制视图
        invalidate();
    }
}
