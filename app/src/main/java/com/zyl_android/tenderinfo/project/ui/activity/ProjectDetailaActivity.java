package com.zyl_android.tenderinfo.project.ui.activity;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseWebViewActivity;


/**
 * Created by bibinet on 2017-11-10.
 */

public  class ProjectDetailaActivity extends BaseWebViewActivity {

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
    protected void initWebView() {
        getWebView().loadUrl("http://www.baidu.com");
    }
}
