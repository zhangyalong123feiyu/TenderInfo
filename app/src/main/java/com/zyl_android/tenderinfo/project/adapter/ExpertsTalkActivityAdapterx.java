package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.bean.ExpertsDataBean;
import com.zyl_android.tenderinfo.project.bean.SearchResultBean;
import com.zyl_android.tenderinfo.project.utils.B64PhotoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-11-25.
 */

public class ExpertsTalkActivityAdapterx extends BaseRecyAdapter<SearchResultBean.ItemsBean> {
    public ExpertsTalkActivityAdapterx(Context context, List<SearchResultBean.ItemsBean> data) {
        super(context, data);

    }

    @Override
    protected int getChildLayout() {
        return  R.layout.activity_searchresult_adapter_item;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, List<SearchResultBean.ItemsBean> datas, int position) {
        ((TextView)holder.getView(R.id.projcetName)).setText(datas.get(position).getProjectName());
        ((TextView)holder.getView(R.id.procjetdescrp)).setText(datas.get(position).getProjectDescrp());
        ((TextView)holder.getView(R.id.projectype)).setText(datas.get(position).getProjectType());
        ((TextView)holder.getView(R.id.projcetlocation)).setText(datas.get(position).getProjectLocation());
        ((TextView)holder.getView(R.id.projectpulishtime)).setText(datas.get(position).getProjectPublishTime());
    }

//    R.layout.activity_searchresult_adapter_item;
//    ((TextView)holder.getView(R.id.projcetName)).setText(datas.get(position).getProjectName());
//        ((TextView)holder.getView(R.id.procjetdescrp)).setText(datas.get(position).getProjectDescrp());
//        ((TextView)holder.getView(R.id.projectype)).setText(datas.get(position).getProjectType());
//        ((TextView)holder.getView(R.id.projcetlocation)).setText(datas.get(position).getProjectLocation());
//        ((TextView)holder.getView(R.id.projectpulishtime)).setText(datas.get(position).getProjectPublishTime());
}
