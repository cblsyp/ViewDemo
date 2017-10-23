package com.example.chen.viewdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.TestMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chen.viewdemo.R;
import com.example.chen.viewdemo.adapter.TextRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuiyanping on 2017/10/19.
 */

public class RecyclerViewActivity extends Activity {
    private RecyclerView recyclerView;
    private List<String> list;
    private TextRecyclerViewAdapter textRecyclerViewAdapter;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        initDate();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_veiw);
//        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
//        layoutManager.setItemPrefetchEnabled(true);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        textRecyclerViewAdapter = new TextRecyclerViewAdapter(this, list);
        recyclerView.setAdapter(textRecyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    private void initDate() {
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item" + i);
        }
    }


}
