package com.example.chen.viewdemo.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.example.chen.viewdemo.R;

/**
 * 这是一个测试对话框透明的Dialog  我们需要的效果已经实现了
 * Created by shuiyanping on 2017/12/6.
 */

public class SelectDialog extends AlertDialog {
    protected SelectDialog(Context context) {
        super(context);
    }

    public SelectDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.al_layout);
    }
}
