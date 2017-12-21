package com.zyl_android.tenderinfo.project.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zyl_android.generalutils.PhoneNumberUtils;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.AdviceSubmitActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.AdviceSubmitActivityView;
import com.zyl_android.tenderinfo.project.bean.BaseBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-12-19.
 * retrofit测试使用页面
 */

public class AdviceSubmitActivity extends BaseActivity implements AdviceSubmitActivityView {
    @BindView(R.id.submitContent)
    EditText submitContent;
    @BindView(R.id.writePhoneNum)
    EditText writePhoneNum;
    @BindView(R.id.adviceSubmit)
    Button adviceSubmit;
    private AdviceSubmitActivityPresenter adviceSubmitActivityPresenter;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_advicesubmit;
    }

    @Override
    protected void initView() {
        title.setText("意见反馈");
        titleBackImage.setVisibility(View.VISIBLE);
        adviceSubmitActivityPresenter=new AdviceSubmitActivityPresenter(this);
    }

    @OnClick(R.id.adviceSubmit)
    public void onViewClicked() {
        String content = submitContent.getText().toString().trim();
        String phone = writePhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(content)||TextUtils.isEmpty(phone)) {
            toast("请确保内容不为空");
        }else if (!PhoneNumberUtils.isMobileNumber(phone)) {
            toast("请输入正确的手机号");
        }else {
            adviceSubmitActivityPresenter.postAdvice(content,phone);
        }
    }

    @Override
    public void onSubmitAdviceSucess(BaseBean baseInfo) {
        toast(baseInfo.getResMessage());
    }

    @Override
    public void onSubmitAdviceFailed(String msg) {
        log("意见反馈错误",msg);
    }
}
