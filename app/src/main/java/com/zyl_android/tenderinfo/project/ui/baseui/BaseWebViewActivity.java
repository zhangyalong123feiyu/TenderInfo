package com.zyl_android.tenderinfo.project.ui.baseui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.zyl_android.generalutils.WaitUtils;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.builder.CustomerWebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-11-9.
 */

public abstract class BaseWebViewActivity extends BaseActivity {
    @BindView(R.id.Webview)
    WebView webView;
    @BindView(R.id.netErrorView)
    ImageView netErrorView;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_h5;
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.Webview);
        initWebView();
        webView.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings seting = webView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        webView.setVerticalScrollbarOverlay(true); //指定的垂直滚动条有叠加样式
        seting.setUseWideViewPort(true);//设定支持viewport
        seting.setLoadWithOverviewMode(true);
        seting.setSupportZoom(true);//设定支持缩放
        webView.setWebViewClient(new CustomerWebViewClient(this, webView, new WaitUtils(waitView), netErrorView));
    }

    protected abstract void initWebView();

    protected WebView getWebView() {
        return webView;
    }

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webView.goBack();
                return true;
            } else {
                //    System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
