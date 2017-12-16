package com.zyl_android.tenderinfo.mvp.presenter;

import com.google.gson.Gson;
import com.zyl_android.tenderinfo.mvp.base.basemodel.BaseModel;
import com.zyl_android.tenderinfo.mvp.model.TenderHelpActivityModel;
import com.zyl_android.tenderinfo.mvp.view.TenderHelpActivityView;
import com.zyl_android.tenderinfo.project.bean.BaseBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by bibinet on 2017-12-16.
 */

public class TenderHelpActivityPresenter {
    private TenderHelpActivityModel tenderHelpActivityModel;
    private TenderHelpActivityView tenderHelpActivityView;

    public TenderHelpActivityPresenter(TenderHelpActivityView tenderHelpActivityView) {
        this.tenderHelpActivityView = tenderHelpActivityView;
        this.tenderHelpActivityModel=new TenderHelpActivityModel();
    }
    public void postTenderHelpData(String contact, String cellPhone, String content, String customerId){
        tenderHelpActivityModel.postTenderHelpData(contact, cellPhone, content, customerId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                tenderHelpActivityView.onPostDataFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                String string=response.body().string();
                BaseBean postInfo = gson.fromJson(string, BaseBean.class);
                tenderHelpActivityView.onPostDataSucess(postInfo);
            }
        });
    }
}
