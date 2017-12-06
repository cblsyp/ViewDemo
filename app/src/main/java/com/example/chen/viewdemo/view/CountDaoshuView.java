package com.example.chen.viewdemo.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shuiyanping on 2017/11/12.
 */

public class CountDaoshuView extends AppCompatButton implements View.OnClickListener {
    private int count;
    private static final String TAG="chen debug";

    public CountDaoshuView(Context context, AttributeSet attrs, int defStyleAttr, int count) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG,"初始化三个构造器的的构造函数");
        init(count);
    }

    public CountDaoshuView(Context context, AttributeSet attrs, int count) {
        super(context, attrs);
        Log.e(TAG,"初始化2个构造器的的构造函数");
        init(count);
    }

    public CountDaoshuView(Context context, int count) {
        super(context);
        Log.e(TAG,"初始化1个构造器的的构造函数");
        init(count);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setText(count--+"秒");
        }
    };

    private void init(int count) {
        this.count = count;
        setText("发送验证码");
        setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        while (count>0) {
            new Timer().schedule(new TimerTask() {
                /**
                 * 这里是一个异步的线程   我们不能在非主线程中更新UI
                 */
                @Override
                public void run() {
                    setFocusable(false);



                }
            }, 0,1000);

        }
        setFocusable(true);
        setText("再次发送");
    }
}
