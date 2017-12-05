package com.zyl_android.tenderinfo.project.ui.baseui;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by bibinet on 2017-12-1.
 */

public abstract class LazyFragement extends Fragment {
    protected boolean isVisible=false;
    private boolean isFirstLoad=true;
    protected boolean isInitView;//是否关联到子view

    @Override
    public void onHiddenChanged(boolean hidden) {//当Fragement显示状态发生变化时执行，优先于oncreateView执行
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible=true;
            onVisible();
        }else {
            isVisible=false;
            onInvisleBle();
        }
    }
    protected void onInvisleBle(){};

    private void onVisible() {
        lazyLoadData();
    }

    protected  void lazyLoadData(){
        if (isFirstLoad) {
            Log.i("TAG","第一次加载 " + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
        } else {
            Log.i("TAG","不是第一次加载" + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
        }
        if (!isFirstLoad || !isVisible || !isInitView) {
            Log.i("TAG","!isFirstLoad" + "   " + !isFirstLoad);
            Log.i("TAG","!isVisible" + "   " + !isVisible);
            Log.i("TAG","!isInitView" + "   " + !isInitView);
            Log.i("TAG","不加载" + "   " + this.getClass().getSimpleName());
            return;
        }
        Log.i("TAG","完成数据第一次加载"+ "   " + this.getClass().getSimpleName());
        initData();
        isFirstLoad = false;
    }
    protected abstract void initData();
}
