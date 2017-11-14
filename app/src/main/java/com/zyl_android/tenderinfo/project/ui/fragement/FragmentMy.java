package com.zyl_android.tenderinfo.project.ui.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseFragement;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMy extends BaseFragement {


    @Override
    protected void refreshData() {

    }

    @Override
    protected void loadMoreData() {

    }

    @Override
    protected int getFragementHomeLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
      titleLayout.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }
}
