package com.zyl_android.tenderinfo.project.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseWebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-11-10.
 */

public class ProjectDetailaActivity extends BaseWebViewActivity {
    @BindView(R.id.Webview)
    WebView projectWebview;

    @Override
    protected void onrefresh() {

    }

    @Override
    protected void onloadMore() {

    }

    @Override
    protected int getChildlayout() {
        return R.layout.activity_h5;
    }

    @Override
    protected void loadData(boolean isLoadMore) {

    }

    @Override
    protected int getWebview() {
        return R.id.Webview;
    }

    @Override
    protected void initWebView() {
        projectWebview.loadUrl("http://www.baidu.com");
    }
}
