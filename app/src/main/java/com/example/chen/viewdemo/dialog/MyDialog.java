package com.example.chen.viewdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;

import com.example.chen.viewdemo.R;

/**
 * Created by shuiyanping on 2017/12/6.
 */

public class MyDialog extends Dialog {
    private Window window=null;
    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public void showDialog(int layoutResID,int x,int y){
        setContentView(layoutResID);

    }

    /**
     * @param x
     * @param y
     */
    public void windowDeploy(int x,int y){
        window=getWindow();

        window.setGravity(Gravity.BOTTOM|Gravity.CENTER);
    //    window.setWindowAnimations(R.style.dialogWindowAnimation);//设置窗口弹出动画
        window.setBackgroundDrawableResource(R.color.vifrification);
    }
}
