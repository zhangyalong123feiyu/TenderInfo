package com.zyl_android.tenderinfo.project.ui.fragement;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.sql.UserTable;
import com.zyl_android.tenderinfo.project.ui.activity.ExpertsTalkActivity;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseFragement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAsk extends BaseFragement {

    @BindView(R.id.customService)
    LinearLayout customService;
    @BindView(R.id.experts)
    LinearLayout experts;
    @BindView(R.id.tenderBook)
    LinearLayout tenderBook;
    @BindView(R.id.tenderHelp)
    LinearLayout tenderHelp;
    Unbinder unbinder;

    @Override
    protected int getFragementHomeLayout() {
        return R.layout.fragment_ask;
    }

    @Override
    protected void initView() {
        title_textView.setText("比比驿站");
        title_backImage.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        //以下为练习litepal所写
        UserTable userTable = new UserTable();
        userTable.setAddress("太原大马村");
        userTable.setCompanyName("山西比比");
        userTable.setUserId(21);
        userTable.setUserName("张亚龙");
        userTable.save();
    }
    @OnClick({R.id.customService, R.id.experts, R.id.tenderBook, R.id.tenderHelp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.customService:
                break;
            case R.id.experts:
                startActivity(new Intent(getActivity(),ExpertsTalkActivity.class));
                break;
            case R.id.tenderBook:
                break;
            case R.id.tenderHelp:
                break;
        }
    }
}
