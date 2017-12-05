package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.adapter.baseadapter.BaseRecyAdapter;
import com.zyl_android.tenderinfo.project.bean.SearchResultBean;

import java.util.List;


/**
 * Created by bibinet on 2017-11-25.
 */

public class SearchResultActivityAdapter extends BaseRecyAdapter<SearchResultBean.ItemsBean> {
    public SearchResultActivityAdapter(Context context, List<SearchResultBean.ItemsBean> data) {
        super(context, data);
    }

    @Override
    protected int getChildLayout() {
        return  R.layout.activity_searchresult_adapter_item;
    }
    @Override
    protected void onBindData(BaseViewHolder holder, List<SearchResultBean.ItemsBean> datas, int position) {
        holder.setText(R.id.projcetName,datas.get(position).getProjectName()).
                setText(R.id.procjetdescrp,datas.get(position).getProjectDescrp()).
                setText(R.id.projcetlocation,datas.get(position).getProjectLocation()).
                setText(R.id.projectpulishtime,datas.get(position).getProjectPublishTime());
        if (datas.get(position).getProjectType().equals("A")) {
            holder.setText(R.id.projectype,"工程");
        }else if (datas.get(position).getProjectType().equals("B")) {
            holder.setText(R.id.projectype,"货物");
        }else{
            holder.setText(R.id.projectype,"服务");
        }
    }
}
