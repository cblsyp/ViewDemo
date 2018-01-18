package com.example.chen.viewdemo.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.chen.viewdemo.R;
import com.example.chen.viewdemo.dialog.MyDialog;
import com.example.chen.viewdemo.dialog.SelectDialog;
import com.example.chen.viewdemo.view.CountdownView;

/**
 * Created by shuiyanping on 2017/12/6.
 */

public class DialogActivity extends Activity {
    private static  final String TAG="chen debug";
    CountdownView countdownView;
    private Button button1;
    SelectDialog selectDialog;
    private Context context;
    MyDialog myDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        button1= (Button) findViewById(R.id.button1);
        context=this;

        testsimpledialog();
        initView();
    }

    private void testsimpledialog() {
        selectDialog=new SelectDialog(this, R.style.dialog);
        Window window=selectDialog.getWindow();
        WindowManager.LayoutParams  params=new WindowManager.LayoutParams();
        params.x=-80;
        params.y=-60;
        window.setAttributes(params);
        selectDialog.setCancelable(true);
    }

    private void initView() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog =new MyDialog(context,R.style.dialog);
                myDialog.showDialog(0,0);
                myDialog.setOnImageDialogClickListener(new MyDialog.OnImageDialogClickListener() {
                    @Override
                    public void onDetailClick() {
                        Log.e(TAG,"进行页面间的跳转的接口回掉");
                    }
                });

            }
        });
    }



    
}
