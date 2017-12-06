package com.example.chen.viewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.chen.viewdemo.R;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by shuiyanping on 2017/10/23.
 */

public class FrescoActivity extends Activity {
    private static final String TAG = "chen debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.myview_layout);
        DisplayMetrics displayMetrics = getDisplayMetrics();

        Log.e(TAG,"屏幕相关的一些信息："+displayMetrics.toString());
    }

    /**
     * 测试DispalyMetrics 这个类的相关的信息  主要是屏幕的一些相关的信息
     * @return
     */
    @NonNull
    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
