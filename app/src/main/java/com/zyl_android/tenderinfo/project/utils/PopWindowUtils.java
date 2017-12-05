package com.zyl_android.tenderinfo.project.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.zyl_android.tenderinfo.R;


/**
 * Created by bibinet on 2017-6-9.
 */

public class PopWindowUtils {
    private Context context;
    private View popview;
    private PopupWindow popupWindow;
    public PopWindowUtils(Context context) {
        this.context = context;
    }
    //创建一个popwindow
    public void createPopWindow(int layoutId) {
         popupWindow = new PopupWindow(context);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(500);
        popview= LayoutInflater.from(context).inflate(layoutId,null);
        popupWindow.setContentView(popview);
        //点击popupWindow以外的区域自动关闭popupWindow
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.anim.tr_void);
    }
    public void showPopWindow(View view){//view为popwindow弹出的基点，再view下方弹出
        popupWindow.showAsDropDown(view, 0, 0);
    }
    //设置popwindow消失
    public void dissMisPopWindow(){
        popupWindow.dismiss();
    }
    //获取popwindow的布局
    public View getPopview() {
        return popview;
    }

}
