package com.zyl_android.tenderinfo.project.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.TenderHelpActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.TenderHelpActivityView;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.bean.BaseBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-12-16.
 */

public class TenderHelpActivity extends BaseActivity implements TenderHelpActivityView{
    @BindView(R.id.writeBookInput)
    EditText writeBookInput;
    @BindView(R.id.contactPersonInput)
    EditText contactPersonInput;
    @BindView(R.id.contactType)
    EditText contactType;
    @BindView(R.id.postTenderhelp)
    Button postTenderhelp;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_tenderhelp;
    }

    @Override
    protected void initView() {
        title.setText("投标协助");
        titleBackImage.setVisibility(View.VISIBLE);
        titleImageRight.setVisibility(View.VISIBLE);
        titleImageRight.setImageResource(R.mipmap.daixiebiaoshu_lishijilv);
    }
    @OnClick({R.id.postTenderhelp,R.id.title_imageright})
    public void onViewClicked(View view) {
        	switch (view.getId()) {
        			case R.id.postTenderhelp:
                        postData();
        				break;
                case R.id.title_imageright:
                    startActivity(new Intent(this,TenderHelpHistoryActivity.class));
                    break;
        			default:
        				break;
        			}
    }

    protected void postData() {
        String content = writeBookInput.getText().toString().trim();
        String phoneNumb=contactType.getText().toString().trim();
        String person=contactPersonInput.getText().toString().trim();
        TenderHelpActivityPresenter tenderHelpActivityPresenter=new TenderHelpActivityPresenter(this);
        tenderHelpActivityPresenter.postTenderHelpData(person,phoneNumb,content, Constants.loginResultInfo.getUser().getUserId());
    }

    @Override
    public void onPostDataSucess(BaseBean postInfo) {
        if (postInfo.getResCode().equals("0000")) {
            toast("提交成功");
            finish();
        		}else {
            toast(postInfo.getResMessage());
        }
    }

    @Override
    public void onPostDataFailed(String msg) {
        log("投标帮助错误",msg);
    }
}
