package com.example.chen.viewdemo.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.chen.viewdemo.R;
import com.example.chen.viewdemo.dialog.SelectDialog;

/**
 * Created by shuiyanping on 2017/12/6.
 */

public class DialogActivity extends Activity {
    private Button button1;
    SelectDialog selectDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        button1= (Button) findViewById(R.id.button1);

        selectDialog=new SelectDialog(this,R.style.dialog);
        Window window=selectDialog.getWindow();
        WindowManager.LayoutParams  params=new WindowManager.LayoutParams();
        params.x=-80;
        params.y=-60;
        window.setAttributes(params);
        selectDialog.setCancelable(true);


        initView();
    }

    private void initView() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDialog.show();
            }
        });
    }
}
