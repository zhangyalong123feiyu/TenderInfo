package com.zyl_android.tenderinfo.project.ui.activity;

import android.view.View;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;

/**
 * Created by bibinet on 2017-12-16.
 */

public class TenderHelpHistoryActivity extends BaseActivity{
    @Override
    protected int getChildlayout() {
        return R.layout.activity_searchresult;
    }

    @Override
    protected void initView() {
        titleBackImage.setVisibility(View.VISIBLE);
        title.setText("投标历史");
    }
}
