package com.example.chen.viewdemo.evaluator;

import android.animation.TypeEvaluator;
import android.util.Log;

import com.example.chen.viewdemo.bean.CPoint;

/**
 * Created by shuiyanping on 2017/12/22.
 */

public class PointEvaluator implements TypeEvaluator {
    private static final String TAG="chen debug";
    /**
     * 这个才是重点
     *
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Log.e(TAG, "evaluate: " );
        CPoint startCpoint = (CPoint) startValue;
        CPoint endCpoint = (CPoint) endValue;

        float radius = 250f;
        float x = 0;
        float y = 0;
        if (fraction <= 0.5f) {
            x = (float) Math.sin(fraction * Math.PI)*radius;
            y = -(float) Math.cos(fraction * Math.PI)*radius;
        } else {
            x = (float) Math.cos(fraction * Math.PI - Math.PI / 2)*radius;
            y = (float) Math.sin(fraction * Math.PI - Math.PI / 2)*radius;
        }
        CPoint cPoint = new CPoint(x, y);

        return cPoint;
    }
}
