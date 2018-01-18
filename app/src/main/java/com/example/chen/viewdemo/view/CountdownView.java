package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.chen.viewdemo.utils.ViewUtila;

import java.util.Locale;

/**
 * Created by shuiyanping on 2017/12/21.
 * <p>
 * 12  21 号  还没有完全理解
 */

public class CountdownView extends View {
    private static final String TAG = "chen debug";
    //控件宽
    private int width;
    //控件高
    private int height;

    //刻度盘半径
    private int dialRadius;

    //小时刻度高
    private float hourScaleHeight = ViewUtila.dptppx(6, getContext());

    //分钟刻度高
    private float minuteScaleHeight = ViewUtila.dptppx(4, getContext());

    //定时进度条宽
    private float arcWidth = ViewUtila.dptppx(6, getContext());
    //时间分
    private int time = 0;

    //刻度盘画笔
    private Paint dialPaint;

    //时间画笔
    private Paint timePaint;

    //是否移动
    private boolean isMove;

    //当前旋转的角度
    private float rotateAngle;

    //当前的角度
    private float currentAngle;

    //时间改变监听
    private OnCountdownListener onCountdownListener;


    public CountdownView(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public CountdownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "CountdownView: ");
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CountdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, "CountdownView: ");
        init();
    }

    private void init() {
        //刻度盘画笔
        dialPaint = new Paint();
        dialPaint.setAntiAlias(true);
        dialPaint.setColor(Color.parseColor("#94c5ff"));
        dialPaint.setStyle(Paint.Style.STROKE);
        dialPaint.setStrokeCap(Paint.Cap.ROUND);


        //时间画笔
        timePaint = new Paint();
        timePaint.setAntiAlias(true);
        timePaint.setColor(Color.parseColor("#94c5ff"));
        timePaint.setTextSize(sptpx(33));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDial(canvas);
        drawArc(canvas);
        drawTime(canvas);
    }

    private void drawTime(Canvas canvas) {
        canvas.restore();
        String timeText = String.format(Locale.CHINA, "%02d", time) + "  :00";
        //获取时间的宽高
        float timeWidth = timePaint.measureText(timeText);
        float timeHeight = Math.abs(timePaint.ascent() + timePaint.descent());
        //剧中显示
        canvas.drawText(timeText, -timeWidth / 2, timeHeight / 2, timePaint);
    }

    private void drawArc(Canvas canvas) {
        if (time > 0) {
            //绘制起始标志
            dialPaint.setStrokeWidth(dptppx(3));
            canvas.drawLine(0, -dialRadius - hourScaleHeight, 0, -dialRadius + hourScaleHeight, dialPaint);

            //取消直线圆角设置
            dialPaint.setStrokeCap(Paint.Cap.BUTT);

            //绘制圆弧
            float arcWidth = dptppx(6);
            for (int i = 0; i <= time * 6; i++) {
                canvas.drawLine(0, -dialRadius - arcWidth / 2, 0, -dialRadius + arcWidth / 2, dialPaint);
                //最后一次不旋转画布
                if (i != time * 6) {
                    canvas.rotate(1);
                }
            }

            //绘制结束标志
            dialPaint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawLine(0, -dialRadius - hourScaleHeight, 0, -dialRadius + hourScaleHeight, dialPaint);
        }


    }

    private void drawDial(Canvas canvas) {
        dialPaint.setStrokeWidth(dptppx(2));
        canvas.drawCircle(width / 2, height / 2, dialRadius, dialPaint);

        //将坐标原点移动到控件中心
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.save();

        //绘制小时刻度
        for (int i = 0; i < 12; i++) {
            //定时间为0时正常绘制小时刻度
            //小时刻度没有被定时进度条覆盖时正常绘制小时刻度

            if (time == 0 || i > time / 5) {
                canvas.drawLine(0, -dialRadius, 0, -dialRadius + hourScaleHeight, dialPaint);
            }

            canvas.rotate(30);
        }


        //绘制分钟刻度
        dialPaint.setStrokeWidth(dptppx(1));
        for (int i = 0; i < 60; i++) {
            //小时刻度不会绘制分钟刻度
            //分钟刻度没有被定时进度条覆盖时正常绘制分钟刻度
            if (i % 5 != 0 && i > time) {
                canvas.drawLine(0, -dialRadius, 0, -dialRadius + minuteScaleHeight, dialPaint);

            }

            canvas.rotate(6);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: ");
        //控件宽 高
        width = height = Math.min(h, w);

        //刻度盘半径
        dialRadius = (int) (width / 2 - ViewUtila.dptppx(10, getContext()));
    }

    private float sptpx(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public float dptppx(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure: ");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout: ");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentAngle = calcAngle(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                //标记正在移动
                isMove = true;
                //移动的角度
                float moveAngle = calcAngle(event.getX(), event.getY());
                //滑过的角度偏移量
                float angleoffset = moveAngle - currentAngle;

                //防止越界
                if (angleoffset < -270) {
                    angleoffset = angleoffset + 360;
                } else if (angleoffset > 270) {
                    angleoffset = angleoffset - 360;
                }

//经典  防止 重复加上一段角度   把当前移动点的角度复制给当前的角度
                currentAngle = moveAngle;

                //计算时间
                calcTime(angleoffset);
                break;


            case MotionEvent.ACTION_POINTER_UP:
                if (isMove && onCountdownListener != null) {
                    onCountdownListener.countdown(time);
                    isMove = false;
                }

                break;
        }

        return true;
    }

    /**
     * @param targetX x坐标
     * @param targetY y坐标
     * @return （targetX,tagetY）坐标与X轴的夹角
     */
    private float calcAngle(float targetX, float targetY) {
        //已刻度盘圆心为坐标原点
        float x = targetX - width / 2;
        float y = targetY - height / 2;

        //滑动的弧度
        double radian;

        if (x != 0) {
            float tan = Math.abs(y / x);
            if (x > 0) {
                //第四象限
                if (y >= 0) {
                    radian = Math.atan(tan);
                } else {
                    //第一象限
                    radian = Math.PI * 2 - Math.atan(tan);
                }
            } else {
                if (y >= 0) {
                    //第三象限
                    radian = Math.PI - Math.atan(tan);
                } else {
                    //第二象限
                    radian = Math.PI + Math.atan(tan);
                }
            }
        } else {
            if (y > 0) {
                //卡文迪许坐标系 下方向
                radian = Math.PI / 2;
            } else {
                radian = Math.PI + Math.PI / 2;
            }
        }

        return (float) (radian / Math.PI * 180);
    }


    /**
     * 计算增加的角度
     * 经过滑动的测试 很准确  rotateAngle  很准确
     *
     * @param angle
     */
    private void calcTime(float angle) {
        //传过来的是移动角度  我们需要一个全局变量将所有的角度加起来

        //即使第一次 滑动结束了  第二次也可以接着加上  这样就能保证时间的连贯性
        rotateAngle += angle;
        if (rotateAngle < 0) {
            rotateAngle = 0;
        } else if (rotateAngle > 360) {
            rotateAngle = 360;
        }

        time = (int) (rotateAngle / 6);
        invalidate();

        Log.e(TAG, "calcTime: rotateAngle:" + rotateAngle);
    }


    public void setCountdown(int mimute) {
        if (mimute < 0 || mimute > 60) {
            return;
        }
        time = mimute;
        rotateAngle = mimute * 6;
        invalidate();
    }

    /**
     * 设置倒计时监听
     *
     * @param onCountdownListener
     */
    public void setOnCountdownListener(OnCountdownListener onCountdownListener) {
        this.onCountdownListener = onCountdownListener;
    }

    /**
     * 到计时监听接口
     */
    public interface OnCountdownListener {
        void countdown(int time);
    }


}
