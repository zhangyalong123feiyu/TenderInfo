package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.DeleteHistoryBean;
import com.zyl_android.tenderinfo.project.bean.HelpTenderHistoryReusltBean;

import java.util.List;

/**
 * Created by bibinet on 2017-12-18.
 */

public interface TenderHelpHistoryActivityView {
    void onLoadTenderHistoryDataSucess(List<HelpTenderHistoryReusltBean.ItemBean> helpHistoryInfo);
    void onLoadTenderHistoryDataFailed(String msg);
    void onDeleteHistorySucess(DeleteHistoryBean delteHistoryInfo);
    void onDeleteHistoryFailed(String msg);
}
