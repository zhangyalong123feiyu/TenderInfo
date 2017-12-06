package com.zyl_android.tenderinfo.project.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.zyl_android.tenderinfo.R;

import org.litepal.LitePalApplication;

/**
 * Created by bibinet on 2017-11-4.
 */

public class TenderApplication extends LitePalApplication {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
    static {//static 代码段可以防止内存泄露
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.black);//全局设置主题颜色
                layout.setEnableHeaderTranslationContent(false);
                return new MaterialHeader(context).setColorSchemeColors(Color.argb(255,200,100,100),Color.argb(255,50,100,200),Color.argb(255,150,30,100));//指定为经典Header，默认是 贝塞尔雷达Header
            }

        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                layout.setFooterHeight(45);
                layout.setPrimaryColors(R.color.gray);
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });

    }
}
