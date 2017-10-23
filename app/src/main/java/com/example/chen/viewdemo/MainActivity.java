package com.example.chen.viewdemo;

import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "chen debug";


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testconview();

    }

    /**
     * 通过LayoutInflater动态加载布局  以及一系列测试
     */
    private void testconview() {
        RelativeLayout layout= (RelativeLayout) findViewById(R.id.activity_main);
        LayoutInflater layoutInflater= LayoutInflater.from(this);
//        LinearLayout linearLayout= (LinearLayout) layoutInflater.inflate(R.layout.button,null);
        Button button= (Button) layoutInflater.inflate(R.layout.button_rootlayout,null);
        layout.addView(button);


        ViewParent viewParent=layout.getParent();
        Log.e(TAG,"the parent of layout is "+viewParent);
        Log.e(TAG,"hahha");

//        这里打印的父类是一个FrameLayout   其实任何一个Activty都包含一个标题栏和一个内容  内容通过setContentView（）方法设置
    }
}
