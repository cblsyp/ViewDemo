package com.example.chen.viewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.chen.viewdemo.R;
import com.example.chen.viewdemo.view.ProgressView;

/**
 * Created by shuiyanping on 2017/12/21.
 */

public class TestActivity extends Activity {
    ProgressView progressView;
    private Button  button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        progressView= (ProgressView) findViewById(R.id.progresss_view);
        button= (Button) findViewById(R.id.buton_process_etxt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.setProcess(0.5f);
            }
        });



    }
}
