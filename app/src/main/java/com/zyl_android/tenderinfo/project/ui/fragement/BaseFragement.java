package com.zyl_android.tenderinfo.project.ui.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zyl_android.tenderinfo.R;

import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-11-6.
 */

public abstract class BaseFragement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_base, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        onchildViewCreated(view,savedInstanceState);
        initView();
        initData();
    }

    protected abstract void onchildViewCreated(View view, Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();

    void toast(String str){
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }
}
