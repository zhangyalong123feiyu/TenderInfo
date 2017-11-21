package com.zyl_android.tenderinfo.project.ui.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.sql.UserTable;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseFragement;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAsk extends BaseFragement {

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
        UserTable userTable=new UserTable();
        userTable.setAddress("太原大马村");
        userTable.setCompanyName("山西比比");
        userTable.setUserId(21);
        userTable.setUserName("张亚龙");
        userTable.save();
    }

}
