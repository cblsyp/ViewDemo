package com.example.chen.viewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.chen.viewdemo.R;
import com.example.chen.viewdemo.view.CountdownView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shuiyanping on 2017/12/16.
 * <p>
 * 这是一个测试Seekbar的简单的应用  采用其样式
 */

public class SeekBarActivity extends Activity implements SeekBar.OnSeekBarChangeListener {


    private static final String TAG = "chen debug";
    // 与“系统默认SeekBar”对应的TextView
    private TextView mTvDef;
    // 与“自定义SeekBar”对应的TextView
    private TextView mTvSelf;
    // “系统默认SeekBar”
    private SeekBar mSeekBarDef;
    // “自定义SeekBar”
    private SeekBar mSeekBarSelf;
    private SeekBar goalSeekbar;
    private Button button, start_bt;

    private int time=18;

    private CountdownView countdownView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbar_layout);
        mTvDef = (TextView) findViewById(R.id.tv_def);
        button = (Button) findViewById(R.id.button_self);

        start_bt = (Button) findViewById(R.id.butt_start);
        countdownView = (CountdownView) findViewById(R.id.countdown);
        // “系统默认SeekBar”
        mSeekBarDef = (SeekBar) findViewById(R.id.seekbar_def);
        goalSeekbar = (SeekBar) findViewById(R.id.seekbar_self02);

//        goalSeekbar.setClickable(false);
//        goalSeekbar.setEnabled(false);
        mSeekBarDef.setOnSeekBarChangeListener(this);

        // 与“自定义SeekBar”对应的TextView
        mTvSelf = (TextView) findViewById(R.id.tv_self);
        // “自定义SeekBar”
        mSeekBarSelf = (SeekBar) findViewById(R.id.seekbar_self);

        mSeekBarSelf.setOnSeekBarChangeListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBarSelf.setProgress(50);
            }
        });

        initCountView();

    }

    private void initCountView() {
        countdownView.setCountdown(time);
        countdownView.setOnCountdownListener(new CountdownView.OnCountdownListener() {
            @Override
            public void countdown(int time) {
                SeekBarActivity.this.time = time;
            }
        });


        start_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                time--;
                                countdownView.setCountdown(time);
                                if (time == 0) {
                                    timer.cancel();
                                }
                            }
                        });
                    }
                }, 1000, 1000);
            }
        });


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e(TAG, "onProgressChanged:");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.e(TAG, "onStartTrackingTouch: ");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.e(TAG, "onStopTrackingTouch: ");
    }
}
