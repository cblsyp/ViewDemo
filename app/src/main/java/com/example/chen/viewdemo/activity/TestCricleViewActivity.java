package com.example.chen.viewdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.chen.viewdemo.view.TestCricleView;

/**
 * Created by shuiyanping on 2017/10/27.
 */

public class TestCricleViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TestCricleView(this));
    }
}
