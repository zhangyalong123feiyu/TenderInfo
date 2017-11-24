package com.zyl_android.tenderinfo.project.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.application.CachePath;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;
import com.zyl_android.tenderinfo.project.utils.DataCleanManagerUtils;
import com.zyl_android.tenderinfo.project.utils.SharedPresUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-23.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.modifyPassword)
    LinearLayout modifyPassword;
    @BindView(R.id.advicePush)
    LinearLayout advicePush;
    @BindView(R.id.cachesize)
    TextView cachesize;
    @BindView(R.id.clearCache)
    LinearLayout clearCache;
    @BindView(R.id.loginOut)
    Button loginOut;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        titleBackImage.setVisibility(View.VISIBLE);
        title.setText("设置");
        initCaceSize();
    }

    private void initCaceSize() {
        try {
            cachesize.setText(DataCleanManagerUtils.getCacheSize(new File(CachePath.IMAGE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.modifyPassword, R.id.advicePush, R.id.clearCache, R.id.loginOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.modifyPassword:
                startActivity(new Intent(this,ModifyPasswordActivity.class));
                break;
            case R.id.advicePush:
                break;
            case R.id.clearCache:
                doClearCache();
                break;
            case R.id.loginOut:
                doLoginOut();
                break;
        }
    }

    private void doClearCache() {
        DataCleanManagerUtils.deleteFolderFile(CachePath.IMAGE_PATH, false);
        initCaceSize();
    }

    private void doLoginOut() {
        Constants.loginResultInfo=null;
        SharedPresUtils sharedPresUtils=SharedPresUtils.getsSharedPresUtils(this);
        sharedPresUtils.putString("loginUerInfo",null);
        finish();
    }
}
