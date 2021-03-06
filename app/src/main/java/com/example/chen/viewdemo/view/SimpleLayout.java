package com.example.chen.viewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shuiyanping on 2017/10/26.
 */

public class SimpleLayout extends ViewGroup {

    public SimpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount()>0){
            View childView=getChildAt(0);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount()>0){
            View childView=getChildAt(0);
            childView.layout(0,0,getMeasuredWidth(),getMeasuredHeight());
        }
    }
}
