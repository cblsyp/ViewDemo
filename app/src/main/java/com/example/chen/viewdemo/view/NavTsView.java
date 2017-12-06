package com.example.chen.viewdemo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by shuiyanping on 2017/11/1.
 */

public class NavTsView extends LinearLayout {


    private int mSelectedColor = 0xffffff;
    private int mNotSeletedColor = 0x313a3f;


    private int mIndicatorColor = 0x313a3f;

    private LinearLayout mTabsContainer;//放置tab的容器
    private View mIndicator, mBottomLine;//指示器和底部横线
    private OnTabsItemClickListener listener;

    public NavTsView(Context context) {
        super(context);
    }

    public NavTsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavTsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        //初始化容器
        mTabsContainer = new LinearLayout(getContext());
        mTabsContainer.setOrientation(HORIZONTAL);
        mTabsContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mTabsContainer);

        //初始化指示器
        mIndicator = new View(getContext());
        mIndicator.setBackgroundColor(mIndicatorColor);
        mIndicator.setLayoutParams(new LayoutParams(300, 8));
        addView(mIndicator);

        //初始化底部横线
        mBottomLine = new View(getContext());
        mBottomLine.setBackgroundColor(mIndicatorColor);
        mBottomLine.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
        addView(mBottomLine);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetIndicator();
    }

    /**
     * 重现设置指示器
     */
    private void resetIndicator() {
        int childCount = mTabsContainer.getChildCount();
        ViewGroup.LayoutParams layoutParams = mIndicator.getLayoutParams();
        if (childCount <= 0) {
            layoutParams.width = 0;
        } else {
            layoutParams.width = getWidth() / childCount;
        }

        mIndicator.setLayoutParams(layoutParams);
    }

    /**
     * @param titles
     */
    public void setTabs(String... titles) {
        mTabsContainer.removeAllViews();
        if (titles != null) {
            for (int i = 0; i < titles.length; i++) {
                TextView textView = new TextView(getContext());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textView.setText(titles[i]);
                textView.setClickable(true);
                textView.setPadding(0, 10, 0, 10);
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                textView.getTag(i);
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        setCurrentTab(position, true);
                        if (listener != null) {
                            listener.onClick(v, position);
                        }
                    }
                });

                mTabsContainer.addView(textView);
            }
        }
        // 初始化 ，默认选择第一个
        setCurrentTab(0, false);
        post(new Runnable() {
            @Override
            public void run() {
                resetIndicator();
            }
        });


    }


    public void setCurrentTab(int position, boolean anim) {
        int childCount = mTabsContainer.getChildCount();
        if (position < 0 || position >= childCount) {
            return;
        }
//        设置每一个tab的状态
        for (int i = 0; i < childCount; i++) {
            TextView childView = (TextView) mTabsContainer.getChildAt(i);
            if (i == position) {
                childView.setTextColor(mSelectedColor);
            } else {
                childView.setTextColor(mNotSeletedColor);
            }
        }
        //指示器的移动
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mIndicator, "x", position * mIndicator.getWidth());
        if (anim) {
            objectAnimator.setDuration(200).start();
        } else {
            objectAnimator.setDuration(0).start();
        }
    }

    public void setOnTabsItemClickListener(OnTabsItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnTabsItemClickListener {
        public void onClick(View view, int position);
    }
}
