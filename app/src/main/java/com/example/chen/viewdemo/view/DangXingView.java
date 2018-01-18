package com.example.chen.viewdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chen.viewdemo.R;

/**
 * Created by shuiyanping on 2017/12/26.
 */

public class DangXingView extends LinearLayout {
    private static final String TAG="chencc";
    private View contentView;
    private LinearLayout linearLayout;
    private ImageView imageView_1, imageView_2, imageView_3, imageView_4, imageView_5;
    float ly_width;
    float start_x;
    float star_x1;
    float star_x2;
    float star_x3;
    float star_x4;
    float star_x5;

    public DangXingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        contentView = inflate(getContext(), R.layout.daxing_layout, this);
        linearLayout = (LinearLayout) contentView.findViewById(R.id.daxing_ly);
        imageView_1 = (ImageView) contentView.findViewById(R.id.star_1);
        imageView_2 = (ImageView) contentView.findViewById(R.id.star_2);
        imageView_3 = (ImageView) contentView.findViewById(R.id.star_3);
        imageView_4 = (ImageView) contentView.findViewById(R.id.star_4);
        imageView_5 = (ImageView) contentView.findViewById(R.id.star_5);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x_down = event.getX();
                Log.e("chencc", "x_down:" + x_down);
                setImagePic(x_down);
                break;

            case MotionEvent.ACTION_MOVE:
                float x_move = event.getX();
                Log.e("chencc", "x_move:" + x_move);
                setImagePic(x_move);
                break;


            case MotionEvent.ACTION_UP:
                float x_up = event.getX();
                Log.e("chencc", "x_up:" + x_up);

                break;
        }
        return true;

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e(TAG, "onLayout: ");
        meaSureWidth();
        measureX();
    }

    private void meaSureWidth() {
        Log.e(TAG, "meaSureWidth: " );
        ly_width = linearLayout.getWidth();
        Log.e("chencc", "线性布局的宽度：" + ly_width);



    }

    private void measureX() {
        Log.e(TAG, "measureX: ");
        start_x = linearLayout.getX();
        star_x1 = imageView_1.getX();
        star_x2 = imageView_2.getX();
        star_x3 = imageView_3.getX();
        star_x4 = imageView_4.getX();
        star_x5 = imageView_5.getX();
        Log.e("chencc", "起始x:" + start_x);
        Log.e("chencc","第一个星星的X："+star_x1);
        Log.e("chencc","第二个星星的X："+star_x2);
        Log.e("chencc","第三个星星的X："+star_x3);
        Log.e("chencc","第四个星星的X："+star_x4);
        Log.e("chencc","第五个星星的X："+star_x5);
    }

    private void setImagePic(float x){
        float offset=x-start_x;
        if (offset<=0){
            return;
        }else if (offset>0&&offset<=star_x2){
            imageView_1.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_2.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
            imageView_3.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
            imageView_4.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
            imageView_5.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
        }else if (offset>star_x2&&offset<=star_x3){
            imageView_1.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_2.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_3.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
            imageView_4.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
            imageView_5.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
        }else if (offset>star_x3&&offset<=star_x4){
            imageView_1.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_2.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_3.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_4.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
            imageView_5.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
        }else if (offset>star_x4&&offset<=star_x5){
            imageView_1.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_2.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_3.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_4.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_5.setImageResource(R.mipmap.con_shoucang_hui_xx_4);
        }else if (offset>star_x5&&offset<=ly_width){
            imageView_1.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_2.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_3.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_4.setImageResource(R.mipmap.con_shoucang_xingxing_4);
            imageView_5.setImageResource(R.mipmap.con_shoucang_xingxing_4);
        }
    }
}
