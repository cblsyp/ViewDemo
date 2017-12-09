package com.example.chen.viewdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.chen.viewdemo.R;

/**
 * Created by shuiyanping on 2017/12/6.
 */

public class MyDialog extends Dialog {
    private Window window = null;
    private Context context;
    private ImageView imageView;
    private OnImageDialogClickListener listener;

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    private void init() {

    }

    public void showDialog(int x, int y) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.my_dialog_layout, null);
        imageView = (ImageView) contentView.findViewById(R.id.image_dialog);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDetailClick();
            }
        });
        setContentView(contentView);
        windowDeploy(x, y);
        //设置在点击对话框意外的位置  对话框消失
        setCanceledOnTouchOutside(true);
        show();
    }

    /**
     * @param x
     * @param y
     */
    public void windowDeploy(int x, int y) {
        window = getWindow();

        window.setGravity(Gravity.BOTTOM | Gravity.CENTER);

        //   这里的动画包括弹出和弹入的动画
        window.setWindowAnimations(R.style.dialogWindowAnim);//设置窗口弹出动画


        window.setBackgroundDrawableResource(R.color.vifrification);
        WindowManager.LayoutParams params = window.getAttributes();
// 根据x y的位置  设置窗口需要显示的位置  这个上下移动真的很让人费解
        //  x小于0左移  大于0右移     y小于0上移  大于0下移  这根View的坐标系是对应的
        params.x = x;
        params.y = y;

        params.width = window.getWindowManager().getDefaultDisplay().getWidth();// 这是dialog的宽度
        params.height = 350;
        window.setAttributes(params);


    }

    public void setOnImageDialogClickListener(OnImageDialogClickListener onImageDialogClickListener) {
        if (onImageDialogClickListener != null) {
            this.listener = onImageDialogClickListener;
        }
    }

    public interface OnImageDialogClickListener {
        void onDetailClick();
    }
}
