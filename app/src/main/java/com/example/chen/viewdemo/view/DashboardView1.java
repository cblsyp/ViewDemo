package com.example.chen.viewdemo.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.RemoteException;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.chen.viewdemo.R;

/**
 * Created by shuiyanping on 2017/10/24.
 */

public class DashboardView1 extends View {
    //
    private int mRadius;//  扇形半径
    private int mStartAngle = 180;//  起始角度
    private int mSweetAngle = 180;//  绘制角度

    private int mMin = 0; //最小值
    private int mMax = 100;//最大值

    private int mSection = 10; //值域（mMAx-mMin）等份份数
    private int mPortion = 10; //一个mSection等分份数


    private String mHeaderText = "℃"; //表头

    private int mRealTimeValue = mMin; //实时读数

    private boolean isShowValue = true; //是否显示实时读数

    private int mStrokeWidth; //画笔宽度
    private int mLength1;    //长刻度的相对圆弧的长度
    private int mLength2; //刻度读数顶部的相对圆弧的长度
    private int mPLRadius; //指针长半径
    private int mPSRadius; //指针短半径


    private int mPadding;
    private float mCenterX, mCenterY;   // 圆心坐标
    private Paint mPaint;
    private RectF mRectFArc;
    private Path mPath;
    private RectF mRectFInnerArc;
    private Rect mRecttext;
    private String[] mTexts;

    public DashboardView1(Context context) {
        super(context, null);
    }


    public DashboardView1(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public DashboardView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     *
     */
    private void init() {
        mStrokeWidth = dp2px(1);

        mLength1 = dp2px(8) + mStrokeWidth;
        mLength2 = mLength1 + dp2px(2);

        mPSRadius = dp2px(10);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


        mRectFArc = new RectF();
        mPath = new Path();
        mRectFInnerArc = new RectF();


        mRecttext = new Rect();

        mTexts = new String[mSection + 1];
        for (int i = 0; i < mTexts.length; i++) {
            int n = (mMax - mMin) / mSection;
            mTexts[i] = String.valueOf(mMin + i * n);
        }
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        以后这种方向的东西需要遵守的方向是  左上右下
        mPadding = Math.max(Math.max(getPaddingLeft(), getPaddingTop()), Math.max(getPaddingRight(), getPaddingBottom()));
        setPadding(mPadding, mPadding, mPadding, mPadding);

        int width = resolveSize(dp2px(200), widthMeasureSpec);
//        这里计算的是半径  总的宽度-两个Padding-圆环的宽度*2=内环的直径
        mRadius = (width - mPadding * 2 + mStrokeWidth * 2) / 2;

        mPaint.setTextSize(sp2px(16));

        if (isShowValue) {//显示实时数据，view高度增加字体高度的三倍
            mPaint.getTextBounds("0", 0, "0".length(), mRecttext);
        } else {
            mPaint.getTextBounds("0", 0, 0, mRecttext);
        }
//         由半径+指针短半径+实时都市文字高度确定的高度
        int height1 = mRadius + mStrokeWidth * 2 + mPSRadius + mRecttext.height() * 3;
//        由其实角度决定的高度
        float[] point1 = getCoordinatePonit(mRadius, mStartAngle);
//            由结束角度确定的高度
        float[] point2 = getCoordinatePonit(mRadius, mStartAngle + mSweetAngle);

//        取最大值
        int max = (int) Math.max(height1, Math.max(point1[1] + mRadius + mStrokeWidth * 2, point2[1] + mRadius + mStrokeWidth * 2));

        setMeasuredDimension(width, max + getPaddingTop() + getPaddingBottom());

        mCenterX = mCenterY = getMeasuredWidth() / 2f;

        mRectFArc.set(getPaddingLeft() + mStrokeWidth, getPaddingTop() + mStrokeWidth, getMeasuredWidth() - getPaddingRight() - mStrokeWidth,
                getMeasuredWidth() - getPaddingBottom() - mStrokeWidth);


        mPaint.setTextSize(sp2px(10));


        mPaint.getTextBounds("0", 0, "0".length(), mRecttext);

        mRectFInnerArc.set(getPaddingLeft() + mLength2 + mRecttext.height(), getPaddingTop() + mLength2 + mRecttext.height(),
                getMeasuredWidth() - getPaddingRight() - mLength2 - mRecttext.height(),
                getMeasuredWidth() - getPaddingBottom() - mLength2 - mRecttext.height());

        mPLRadius = mRadius - (mLength2 + mRecttext.height() + dp2px(5));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//画圆弧
//
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        canvas.drawArc(mRectFArc, mStartAngle, mSweetAngle, false, mPaint);



    }

    /**
     *
     * @param radius 180.0
     * @param angle
     * @return
     */
    public float[] getCoordinatePonit(int radius, float angle) {
        float[] point = new float[2];
        double arcAngle = Math.toRadians(angle);
        if (angle < 90) {
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 90) {
            point[0] = mCenterX;
            point[1] = mCenterY + radius;
        } else if (angle < 90 && angle < 180) {
            arcAngle = Math.PI * (180 - angle) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 180) {
            point[0] = mCenterX + radius;
            point[1] = mCenterY;
        } else if (angle > 180 && angle < 270) {
            arcAngle = Math.PI * (angle - 180) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY - Math.sin(arcAngle) * radius);
        } else if (angle == 270) {
            point[0] = mCenterX;
            point[1] = mCenterY - radius;
        } else {
            arcAngle = Math.PI * (360 - angle) / 180.0;
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        }

        return point;


    }

    private int dp2px(int dp) {
        //TypedValue  Container for a dynamically typed data value
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());

    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

}
