package com.zyl_android.tenderinfo.mvp.presenter;

import com.google.gson.Gson;
import com.zyl_android.tenderinfo.mvp.model.AdviceSubmitActivityModel;
import com.zyl_android.tenderinfo.mvp.view.AdviceSubmitActivityView;
import com.zyl_android.tenderinfo.project.bean.BaseBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bibinet on 2017-12-19.
 */

public class AdviceSubmitActivityPresenter {
    private AdviceSubmitActivityModel adviceSubmitActivityModel;
    private AdviceSubmitActivityView adviceSubmitActivityView;

    public AdviceSubmitActivityPresenter(AdviceSubmitActivityView adviceSubmitActivityView) {
        this.adviceSubmitActivityView = adviceSubmitActivityView;
        this.adviceSubmitActivityModel=new AdviceSubmitActivityModel();
    }
    public void postAdvice(String content, String phone){
        adviceSubmitActivityModel.submitAdvice(content, phone, new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseBean baseInfo = response.body();
                adviceSubmitActivityView.onSubmitAdviceSucess(baseInfo);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                adviceSubmitActivityView.onSubmitAdviceFailed(t.getMessage());
            }
        });
    }
}
