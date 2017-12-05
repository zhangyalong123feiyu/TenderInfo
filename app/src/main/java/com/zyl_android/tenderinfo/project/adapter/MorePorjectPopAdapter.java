package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;
import android.view.View;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.adapter.baseadapter.BaseRecyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bibinet on 2017-12-5.
 */

public class MorePorjectPopAdapter extends BaseRecyAdapter<String> {
    private ItemClickListioner itemClickListioner;
    public MorePorjectPopAdapter(Context context, List<String> data) {
        super(context, data);
    }
    public void setItemClickListioner(ItemClickListioner itemClickListioner){
        this.itemClickListioner=itemClickListioner;
    }
    @Override
    protected int getChildLayout() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, final List<String> datas, final int position) {
        holder.setText(android.R.id.text1,datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListioner.onItemClickListioner(datas.get(position));
            }
        });
    }
  public  interface ItemClickListioner{
        void onItemClickListioner(String descrp);
    }
}
