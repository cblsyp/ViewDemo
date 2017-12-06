package com.example.chen.viewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.chen.viewdemo.R;
import com.example.chen.viewdemo.bean.AbilityBean;
import com.example.chen.viewdemo.view.AbilityMapView;

/**
 * Created by shuiyanping on 2017/11/12.
 */

public class DaojishiActivity extends Activity {
    private static  final String TAG="chen debug";
    AbilityMapView abilityMapView;
//    private AbilityBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daojshu_layout);
//        bean=new AbilityBean(100,50,89,78,56,23,45);
        this.abilityMapView= (AbilityMapView) findViewById(R.id.ability_map_view);
        Log.e(TAG,"hahha ");
        abilityMapView.setData(new AbilityBean(65,70,80,78,80,80,80));
        Log.e(TAG,"hahhahhah");


//        这里设计到顺序问题  我很疑惑   需要好好 深究以下  
    }
}
