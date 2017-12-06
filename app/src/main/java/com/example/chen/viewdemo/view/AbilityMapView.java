package com.example.chen.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.chen.viewdemo.bean.AbilityBean;

import java.util.ArrayList;

/**
 * Created by shuiyanping on 2017/11/16.
 * 一个仿掌上英雄联盟的七星能力图   设计到的知识点  正多边形的绘制  Canvas 的坐标系的
 * 所欠缺是  对于Padding  的支持  把大小设定死了
 */
//
public class AbilityMapView extends View {

    private static final String TAG = AbilityMapView.class.getSimpleName();
    private AbilityBean data;//元数据
    private int n;//边的数量或者能力的个数
    private float R;//最外圈的半径，顶点到中心点的距离
    private int intervalCount;//间隔数量  就把半径分成几段
    private float angle;//两条顶点到中线点的线之间的角度

    private Paint linePaint;//划线的笔
    private Paint textPaint;//画文字的笔

    private int viewHeight;//控件高度
    private int viewWidth;//控件宽度

    private ArrayList<ArrayList<PointF>> pointsArrayList;//  储存多边形顶点数组的数组
    private ArrayList<PointF> abilityPoints;//储存能力点的数组


    public AbilityMapView(Context context) {
//        这个地方改为this 使的不管怎么初始化都会进入第三个构造器
        this(context, null);
    }

    public AbilityMapView(Context context, @Nullable AttributeSet attrs) {
        //        这个地方改为this 使的不管怎么初始化都会进入第三个构造器
        this(context, attrs, 0);
    }

    public AbilityMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSize(context);
        initPoints();
        initPaint(context);
    }


    /**
     * 初始化一些固定数据
     *
     * @param context
     */
    private void initSize(Context context) {
        n = 7;
        R = dp2pxF(context, 100);//半径暂时设为100dp

        intervalCount = 4; //有四层
        angle = (float) ((2 * Math.PI) / n);

        //拿到屏幕的宽高  ，单位是像素
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        Log.e(TAG, "屏幕的宽高：" + screenWidth);
        //控件设置为正方向
        viewWidth = screenWidth;
        viewHeight = screenWidth;
    }


    /**
     *
     */
    private void initPoints() {
// 一个数组中每一个元素又是一个点数组，有几个多边形就有几个数组
        pointsArrayList = new ArrayList<>();
        float x;
        float y;
        for (int i = 0; i < intervalCount; i++) {
            //创建一个储存点的数组
            ArrayList<PointF> pointFS = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                float r = R * ((float) (4 - i) / intervalCount);   //从1  3/4  2/4  1/4 这四个数值
                x = (float) (r * Math.cos(j * angle - Math.PI / 2));
                y = (float) (r * Math.sin(j * angle - Math.PI / 2));
                if (i == 0) {
                    Log.e(TAG, "一层的七个点的数据，x：" + (int) x + "   y:" + y);
                }
                pointFS.add(new PointF(x, y));
            }


            pointsArrayList.add(pointFS);
        }
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //之所以  设置控件的大小没有用就是因为  这里设置死了  都是屏幕的宽度
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initSize(getContext());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把画笔的原点移动到控件的中心  这个是很重要的一点
        //  这一点很重要 因为  我们算的多边形的顶点坐标的时候原点也在控件中心    当然这个是相当于View的坐标系来说的   这个和数学中的卡迪尔坐标系是有区别的
        // 第一个坐标的知道是   （0，-300）   这个-300 表明View的坐标和数学中卡迪尔的坐标的y轴是相反的
        //这里要特别注意View的坐标系的问题
        //这个想法  给我抽触动挺大的
        canvas.translate(viewWidth / 2, viewHeight / 2);
        drawPolygon(canvas);
        drawOutLine(canvas);
        drawAbilityLine(canvas);

        drawAbilityText(canvas);

    }

    /**
     * 画能力描述的文字
     *
     * @param canvas
     */
    private void drawAbilityText(Canvas canvas) {
        canvas.save();

        ArrayList<PointF> textPonits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            float r = R + dp2pxF(getContext(), 15f);
            float x = (float) (r * Math.cos(i * angle - Math.PI / 2));
            float y = (float) (r * Math.sin(i * angle - Math.PI / 2));

            textPonits.add(new PointF(x, y));
        }
        //拿到字体测量器
        Paint.FontMetrics metrics = textPaint.getFontMetrics();

        String[] abilitys = AbilityBean.getAbilitys();
        for (int i = 0; i < n; i++) {
            float x = textPonits.get(i).x;
            float y = textPonits.get(i).y - (metrics.ascent + metrics.descent) / 2;
            canvas.drawText(abilitys[i], x, y, textPaint);
        }

        canvas.restore();

    }

    /**
     * 画能力线
     *
     * @param canvas
     */
    private void drawAbilityLine(Canvas canvas) {
        canvas.save();
        //先把能力点初始化
        abilityPoints = new ArrayList<>();
        Log.e(TAG, "drawAbilityLine ");
//        现在出现问题是  data  是一个null  这样会报空指针错误
//        if (data==null){
//            Log.e(TAG,"data  is null");
//            data=new AbilityBean(0,0,3,5,8,78,49);
//        }
        int[] allAbility = data.getAllAbility();
        Log.e(TAG, data.toString());
        for (int i = 0; i < n; i++) {
            float r = R * (allAbility[i] / 100f);
            float x = (float) (r * (Math.cos(i * angle - Math.PI / 2)));
            float y = (float) (r * (Math.sin(i * angle - Math.PI / 2)));
            abilityPoints.add(new PointF(x, y));
        }

        linePaint.setStrokeWidth(dp2px(getContext(), 2f));
        linePaint.setColor(Color.parseColor("#E96153"));
        linePaint.setStyle(Paint.Style.STROKE);


        Path path = new Path();
        for (int i = 0; i < n; i++) {
            float x = abilityPoints.get(i).x;
            float y = abilityPoints.get(i).y;
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path, linePaint);
        canvas.restore();
    }

    /**
     * 1.  先画外面多边形的轮廓
     * 2。 再画顶点到中心的线
     *
     * @param canvas
     */
    private void drawOutLine(Canvas canvas) {
        canvas.save();
        linePaint.setColor(Color.parseColor("#99DCE2"));
        linePaint.setStyle(Paint.Style.STROKE);//设置为空心的

        //先画最外面的多边形轮廓
        Path path = new Path();
        for (int i = 0; i < n; i++) {
            //只需要第一组的点
            float x = pointsArrayList.get(0).get(i).x;
            float y = pointsArrayList.get(0).get(i).y;

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }

        path.close();
        canvas.drawPath(path, linePaint);

        //再画顶点到中心的线
        for (int i = 0; i < n; i++) {
            float x = pointsArrayList.get(0).get(i).x;
            float y = pointsArrayList.get(0).get(i).y;
            canvas.drawLine(0, 0, x, y, linePaint);//起点都是中心点
        }
        canvas.restore();
    }

    /**
     * 绘制多边形  ，每一圈都绘制
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        canvas.save();//保存画布当前的状态（平移 放缩  旋转 裁剪等）。和canvas.restore() 配合使用
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置为填充且描边
        Path path = new Path();

        for (int i = 0; i < intervalCount; i++) {
            switch (i) {
                case 0:
                    linePaint.setColor(Color.parseColor("#D4F0F3"));
                    break;
                case 1:
                    linePaint.setColor(Color.parseColor("#99DCE2"));
                    break;
                case 2:
                    linePaint.setColor(Color.parseColor("#56C1C7"));
                    break;
                case 3:
                    linePaint.setColor(Color.parseColor("#278891"));
                    break;
            }


            for (int j = 0; j < n; j++) {
                float x = pointsArrayList.get(i).get(j).x;
                float y = pointsArrayList.get(i).get(j).y;
                if (j == 0) {
//                    如果是每层的第一个点就把path 的起点设置为这个点
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
//            for (int j=0;j<n-2;j++) {
//                float x = pointsArrayList.get(0).get(j).x;
//                float y = pointsArrayList.get(0).get(j).y;
//                if (j == 0) {
////                    如果是每层的第一个点就把path 的起点设置为这个点
//                    path.moveTo(x, y);
//                } else {
//                    path.lineTo(x, y);
//                }
//            }
            path.close();  // 设置为闭合的
            canvas.drawPath(path, linePaint);
            path.reset();//清除Path储存的路径

        }
    }

    private void initPaint(Context context) {
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(dp2px(context, 1f));

//画文字的笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(dp2spF(context, 14f));

    }

    public void setData(AbilityBean bean) {
        if (bean == null) {
            return;
        }
        this.data = bean;
        Log.e(TAG, "setData: ");
        invalidate();

    }

    public static float dp2pxF(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float dp2spF(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static int dp2sp(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }


}
