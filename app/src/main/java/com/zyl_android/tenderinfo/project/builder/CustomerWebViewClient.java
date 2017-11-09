package com.zyl_android.tenderinfo.project.builder;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zyl_android.generalutils.WaitUtils;

/**
 * Created by Wh on 2017-7-14.
 */

public class CustomerWebViewClient extends WebViewClient {
    private Context mContext;

    private View errorV;
    private WaitUtils waitV;
    private WebView webView;

    public CustomerWebViewClient(Context mContext, WebView webView, WaitUtils waitV, View errorV) {
        this.mContext = mContext;
        this.webView = webView;
        this.waitV = waitV;
        this.errorV = errorV;

        setWaitState();
    }

    // 旧版本，会在新版本中也可能被调用，所以加上一个判断，防止重复显示
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return;
        }
        // 在这里显示自定义错误页
//        Toast.makeText(mContext, "旧版本错误了", Toast.LENGTH_SHORT).show();
        setErrorState();
        isError = true;
    }

    // 新版本，只会在Android6及以上调用
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (request.isForMainFrame()){ // 或者： if(request.getUrl().toString() .equals(getUrl()))
            // 在这里显示自定义错误页
//            Toast.makeText(mContext, "新版本错误了", Toast.LENGTH_SHORT).show();
            setErrorState();
            isError = true;
        }
    }

    private boolean isError = false;
    //页面完成
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if(!isError){
            setNormalState();
        }
    }
    //页面启动
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        setWaitState();
    }

    private void setWaitState(){
        waitV.open();
        webView.setVisibility(View.GONE);
        errorV.setVisibility(View.GONE);
    }

    private void setErrorState(){
        waitV.close();
        webView.setVisibility(View.GONE);
        errorV.setVisibility(View.VISIBLE);
    }

    private void setNormalState(){
        waitV.close();
        webView.setVisibility(View.VISIBLE);
        errorV.setVisibility(View.GONE);
    }
}
