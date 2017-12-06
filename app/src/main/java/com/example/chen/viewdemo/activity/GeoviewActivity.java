package com.example.chen.viewdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.chen.viewdemo.R;
import com.example.chen.viewdemo.view.ArcView;
import com.example.chen.viewdemo.view.CricleView;
import com.example.chen.viewdemo.view.DrawTextView;
import com.example.chen.viewdemo.view.GEoView;
import com.example.chen.viewdemo.view.RotateClockView;
import com.example.chen.viewdemo.view.ScaleView;

/**
 * Created by shuiyanping on 2017/10/28.
 */

public class GeoviewActivity extends Activity {
    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.geoview_layout);
//       setContentView(new GEoView(this));
//        setContentView(new ScaleView(this));
//        setContentView(new CricleView(this));

//        setContentView(R.layout.mycricleview_layout);
//        setContentView(new ArcView(this));

        setContentView(new DrawTextView(this));
    }
}
