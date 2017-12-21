package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;
import android.view.View;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.adapter.baseadapter.BaseRecyAdapter;
import com.zyl_android.tenderinfo.project.bean.HelpTenderHistoryReusltBean;

import java.util.List;

/**
 * Created by bibinet on 2017-12-18.
 */

public class TenderHistoryActivityAdapter extends BaseRecyAdapter<HelpTenderHistoryReusltBean.ItemBean> {
    private DeleteHistory deleteHistory;
    public TenderHistoryActivityAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getChildLayout() {
        return R.layout.adapter_hisitorytender_item;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, final List<HelpTenderHistoryReusltBean.ItemBean> datas, final int position) {
        holder.setText(R.id.tenderHelpContent,datas.get(position).getContent()).setText(R.id.contactPerson,datas.get(position).getContact())
                .setText(R.id.contactType,datas.get(position).getCellPhone()).setText(R.id.tenderHelpDate, String.valueOf(datas.get(position).getCreateDate()
        ));
        holder.getView(R.id.delete_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteHistory.onDeletHistory(datas.get(position).getObjectId());
            }
        });
    }
    public void setOnDeleteHistoryData(DeleteHistory deleteHistoryData){
        this.deleteHistory=deleteHistoryData;
    }
  public  interface DeleteHistory{
        void onDeletHistory(int objectId);
    }
}
