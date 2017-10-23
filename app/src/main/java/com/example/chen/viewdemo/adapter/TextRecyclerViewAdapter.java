package com.example.chen.viewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.viewdemo.R;

import java.util.List;

/**
 * Created by shuiyanping on 2017/10/19.
 */

public class TextRecyclerViewAdapter extends RecyclerView.Adapter<TextRecyclerViewAdapter.ViewHolderA> {
    Context context;
    private List<String> mlist;

    public TextRecyclerViewAdapter(Context context,List<String> list){
        this.context=context;
        this.mlist=list;
    }

    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
//        此处动态加载ViewHolder 的布局文件并返回holder
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent,false);
        ViewHolderA viewHolderA = new ViewHolderA(view);
        return viewHolderA;
    }

    @Override
    public void onBindViewHolder(ViewHolderA holder, final int position) {
//        此处设置Item中view的数据
        holder.mtextveiw.setText(mlist.get(position));
        holder.mtextveiw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"item"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * @return
     */
    @Override
    public int getItemCount() {
//        生成的Item的数量
        return mlist.size();
    }

    class ViewHolderA extends RecyclerView.ViewHolder {
        TextView mtextveiw;

        public ViewHolderA(View itemView) {
            super(itemView);
            mtextveiw = (TextView) itemView.findViewById(R.id.recycler_textview);
        }
    }
}

