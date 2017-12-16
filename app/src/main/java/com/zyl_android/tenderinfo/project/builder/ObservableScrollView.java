package com.zyl_android.tenderinfo.project.builder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * Created by zyl on 2016/8/21 0021.17:23
 */
public class ObservableScrollView extends ScrollView {


    private ScrollViewListener mScrollViewListener=null;
    private int startY;
    private int startX;
    private int mTouchSlop;
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        mScrollViewListener = scrollViewListener;
    }

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
         mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();//滑动最短距离
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mScrollViewListener != null) {
            mScrollViewListener.onScrollChanged(x, y, oldx, oldy);
        }
    }
    public interface ScrollViewListener {

        void onScrollChanged(int x, int y, int oldx, int oldy);
    }
    @Override

    public boolean onInterceptTouchEvent(MotionEvent e) {//

        int action = e.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) e.getRawX();
                startY = (int) e.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - startY) > mTouchSlop) {
                    return true;
                }
        }

        return super.onInterceptTouchEvent(e);

    }

}
