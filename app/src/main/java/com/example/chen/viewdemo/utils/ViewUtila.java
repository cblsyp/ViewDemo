package com.example.chen.viewdemo.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by shuiyanping on 2017/12/21.
 */

public class ViewUtila  {
    public static float dptppx(int dp, Context context){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
}
