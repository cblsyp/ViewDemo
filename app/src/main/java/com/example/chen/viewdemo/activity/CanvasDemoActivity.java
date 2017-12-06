package com.example.chen.viewdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.chen.viewdemo.R;
import com.example.chen.viewdemo.view.TestCricleView;
import com.facebook.imagepipeline.core.PriorityThreadFactory;

/**
 * Created by shuiyanping on 2017/10/27.
 */

public class CanvasDemoActivity extends Activity {
    private static final String TAG = "chen debug";
private int Width;
    private int Height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Width=displayMetrics.widthPixels;
        Height=displayMetrics.heightPixels;
        Log.e(TAG,"Width:"+Width+"\n"+"Height:"+Height);
        setContentView(new Custome1(this));
    }


    /**
     *
     */
    class Custome1 extends View {
        Paint mPaint;
        Paint paint;
        /**
         * @param context
         */
        public Custome1(Context context) {
            super(context);
            mPaint=new Paint();
//           设置笔刷大小为3的黄色的画笔
            mPaint.setColor(Color.RED);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(3);



            paint=new Paint();
            paint.setColor(getResources().getColor(R.color.yuanzhu));
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.translate(canvas.getWidth()/2,200);

            canvas.drawCircle(0,0,100,mPaint);

            canvas.save();
//            使用Path绘制文件路径
            canvas.translate(-75, -75);
            Path path = new Path();
            path.addArc(new RectF(0,0,150,150), -180, 180);
            Paint citePaint = new Paint(mPaint);
            citePaint.setTextSize(14);
            citePaint.setStrokeWidth(1);
            canvas.drawTextOnPath("http://www.android777.com", path, 28, 0, citePaint);
            canvas.restore();

//这里的画线的逻辑是我们每次都在坐标为（0，100）向上画一定长度的线   当为5 的整数倍的时候  那么我们画一个长一点的线  否则画一个稍短的刻度
//            Paint tmpPaint=new Paint(mPaint);
//            tmpPaint.setStrokeWidth(1);
            float y=120;
            int count=60;


//            for(int i=0 ; i <count ; i++){
//                if(i%5 == 0){
//                    canvas.drawLine(0f, y, 0, y+12f, mPaint);
////                    canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, );
//
//                }else{
//                    canvas.drawLine(0f, y, 0f, y +5f, mPaint);
//                }
//                canvas.rotate(6,0f,0f); //旋转画纸
//            }


            for (int i=0;i<60;i++){
                canvas.drawLine(0f,y,0,y+60f,paint);
                canvas.rotate(6,0f,0f);
            }

//            绘制指针

//            tmpPaint.setColor(Color.GRAY);
//            tmpPaint.setStrokeWidth(4);
//            canvas.drawCircle(0, 0, 7, tmpPaint);
//            tmpPaint.setStyle(Paint.Style.FILL);
//            tmpPaint.setColor(Color.YELLOW);
//            canvas.drawCircle(0, 0, 5, tmpPaint);
//            canvas.drawLine(0, 10, 0, -65, mPaint);



        }

        //        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
////            Path path = new Path(); //定义一条路径
////            path.moveTo(10, 10); //移动到 坐标10,10
////            path.lineTo(200, 200);
////            path.lineTo(400,800);
////            path.lineTo(10, 10);
////
//////            canvas.drawPath(path, mPaint);
////
//////            canvas.drawPath(path,mPaint);
////            String s="nothing can change my mind.";
//////            canvas.drawTextOnPath(path,mPaint);
////            canvas.drawTextOnPath(s,path,10,10,mPaint);
//
////            Path path = new Path(); //定义一条路径
////            path.moveTo(10, 10); //移动到 坐标10,10
////            path.lineTo(50, 60);
////            path.lineTo(200,80);
////            path.lineTo(10, 10);
////
//////          canvas.drawPath(path, paint);
////            canvas.drawTextOnPath("Android777开发者博客", path, 10, 10, mPaint);
//
//
//            Path path=new Path();
//            path.moveTo(100,100);
//            path.lineTo(400,400);
//            path.lineTo(500,400);
//            path.lineTo(100,100);
//
//            canvas.drawTextOnPath("i love you",path,10,10,mPaint);
//        }


        //        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//            float l=Width/2-200;
//            float t=Height/2-200;
//            float r=Width/2+200;
//            float b=Height/2+200;
//            RectF  rectF=new RectF(l,t,r,b);
//            canvas.drawRoundRect(rectF,
//                    60, //x轴的半径
//                    30, //y轴的半径
//                    mPaint);
//        }


        /**
         * @param canvas
         */
//        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
////            canvas.drawColor(Color.BLUE);
//
////            canvas.drawLine(100,100,200,200,mPaint);
////在矩形内内切圆
////            RectF rect=new RectF(200,200,600,800);
////            canvas.drawOval(rect,mPaint);
////            按照既定位置画文本
////                       canvas.drawPosText("AmyILoveYou",new float[]{
////                    10,10,
////                    20,20,
////                    30,30,
////                    40,40,
////                    50,50,
////                    60,60,
////                    70,70,
////                    80,80,
////                    90,90,
////                    100,100,
////                    110,110,
////
////
////            },mPaint);
////按照既定点 绘制文本内容
//            canvas.drawPosText("Android777", new float[]{
//                    10,10, //第一个字母在坐标10,10
//                    20,20, //第二个字母在坐标20,20
//                    30,30, //....
//                    40,40,
//                    50,50,
//                    60,60,
//                    70,70,
//                    80,80,
//                    90,90,
//                    100,100
//            }, mPaint);
//        }


        /**
         *  在中心画弧    主要是参数的应用
         * @param canvas
         */
//        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//            float l=Width/2-200;
//            float t=Height/2-200;
//            float r=Width/2+200;
//            float b=Height/2+200;
//
//            RectF rectF=new RectF(l,t,r,b);
////            第三个参数表示转动的角度 而不是终点的角度值  90表示要转动90度 而不是到达90度的地方
////            canvas.drawArc(rectF,30,90,true,mPaint);
//            canvas.drawArc(rectF,30,90,false,mPaint);
//        }


        //        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//            RectF rectF=new RectF(Width/2,Height,Width/2+100,Height/2+100);
////            flase 表示是否使用中心   从30 这个数字就可以看出  是以父容器的X舟正方向为起点   顺时针方向为正方向  用-90测试
//            canvas.drawArc(rectF,0,90,true,mPaint);
//
//        }


        /**
         * @param canvas
         * 屏幕正中心画一个正方形
         */
//        @Override
//        protected void onDraw(Canvas canvas){
//            super.onDraw(canvas);
//            float l=Width/2-200;
//            float t=Height/2-200;
//            float r=Width/2+200;
//            float b=Height/2+200;
//            RectF  rectF=new RectF(l,t,r,b);
//            canvas.drawRect(rectF,mPaint);
//        }
/**
         * @param canvas
         */
//        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//            Log.e(TAG,"getWidth:"+getWidth()+"\n"+"getHeight:"+getHeight());
//
//            canvas.drawCircle(Width/2,Height/2,90,mPaint);
//        }
    }
}
