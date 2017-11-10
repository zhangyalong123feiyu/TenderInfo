package com.zyl_android.tenderinfo.project.ui.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseWebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-9.
 */

public class PlatformActivity extends BaseWebViewActivity {
    @BindView(R.id.Webview)
    WebView platformWebview;

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
        title.setText("招标平台");
        titleBackImage.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String type = intent.getStringExtra("Type");
        switch (Integer.parseInt(type)) {
            case 1:
                platformWebview.loadUrl(Constants.baseUrl_pis + "appPage/detail.jsp");
                title.setText("比比招标采购网");
                break;
            case 2:
                platformWebview.loadUrl(Constants.baseUrl_pis + "appPage/detail1.jsp");
                title.setText("企业招标采购数字平台");
                break;
            case 3:
                platformWebview.loadUrl(Constants.baseUrl_pis + "appPage/detail2.jsp");
                title.setText("比比招投标金融服务平台");
                break;
            case 4:
                platformWebview.loadUrl(Constants.baseUrl_pis + "appPage/detail3.jsp");
                title.setText("比比服务中心");
                break;

            default:
                break;
        }
    }

    @OnClick(R.id.title_imageleft)
    public void onViewClicked() {
        finish();
    }
}
