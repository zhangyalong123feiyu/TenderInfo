package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.adapter.baseadapter.BaseRecyAdapter;
import com.zyl_android.tenderinfo.project.bean.ExpertsAskAnswerResultBean;

import java.util.List;

/**
 * Created by bibinet on 2017-12-16.
 */

public class ExpertsTalkHistoryAdapter extends BaseRecyAdapter<ExpertsAskAnswerResultBean.ItemsBean> {
    public ExpertsTalkHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getChildLayout() {
        return R.layout.item_expertsanserhistory;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, List<ExpertsAskAnswerResultBean.ItemsBean> datas, int position) {
            holder.setText(R.id.answerTitle,datas.get(position).getTitle()).setText(R.id.content,datas.get(position).getContent())
                    .setText(R.id.answerExperts,datas.get(position).getexpertName()).setText(R.id.createTime,datas.get(position).getCreateDateStr());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
