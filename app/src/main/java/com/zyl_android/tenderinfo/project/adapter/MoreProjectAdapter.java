package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.adapter.baseadapter.BaseRecyAdapter;
import com.zyl_android.tenderinfo.project.bean.ProjectInfoBean;

import java.util.List;

/**
 * Created by bibinet on 2017-12-5.
 */

public class MoreProjectAdapter extends BaseRecyAdapter<ProjectInfoBean.ItemsBean> {
    public MoreProjectAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getChildLayout() {
        return R.layout.frag_home_item_two;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, List<ProjectInfoBean.ItemsBean> datas, int position) {
        holder.setText(R.id.companyName,datas.get(position).getProjectName()).
                setText(R.id.projectDescrp,datas.get(position).getProjectDescrp()).
                setText(R.id.projectLoaction,datas.get(position).getProjectLocation()).
                setText(R.id.projectTime,datas.get(position).getProjectTime());;
        if (datas.get(position).getProjectType().equals("A")) {
            holder.setImageView(R.id.projectImage,R.mipmap.shouye_gongcheng);
        }else if (datas.get(position).getProjectType().equals("B")) {
            holder.setImageView(R.id.projectImage,R.mipmap.shouye_huowu);
        }else{
            holder.setImageView(R.id.projectImage,R.mipmap.shouye_fuw);
        }
    }
}
