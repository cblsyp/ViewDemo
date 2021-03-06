package com.example.chen.viewdemo.bean;

/**
 * Created by shuiyanping on 2017/12/22.
 */

public class CPoint {
    private float x;
    private float y;

    public CPoint(float x, float y){
        this.x=x;

        this.y=y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "CPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
