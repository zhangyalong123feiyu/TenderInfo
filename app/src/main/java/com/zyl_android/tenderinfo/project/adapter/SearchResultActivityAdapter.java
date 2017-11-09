package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.bean.SearchResultBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchResultActivityAdapter extends RecyclerView.Adapter<SearchResultActivityAdapter.SearchResultViewHolder> {

    private List<SearchResultBean.ItemsBean> searchResultList = new ArrayList<>();
    private Context context;

    public SearchResultActivityAdapter(List<SearchResultBean.ItemsBean> searchResultList, Context context) {
        this.searchResultList = searchResultList;
        this.context = context;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_searchresult_adapter_item, null);
        SearchResultViewHolder searchResultViewHolder = new SearchResultViewHolder(view);
        return searchResultViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.projcetName.setText(searchResultList.get(position).getProjectName());
        holder.procjetdescrp.setText(searchResultList.get(position).getProjectDescrp());
        holder.projcetlocation.setText(searchResultList.get(position).getProjectLocation());
        holder.projectpulishtime.setText(searchResultList.get(position).getProjectPublishTime());
        if (searchResultList.get(position).getProjectType().equals("A")) {
            holder.projectype.setText("工程");
        }else if (searchResultList.get(position).getProjectType().equals("B")) {
            holder.projectype.setText("货物");
        }else{
            holder.projectype.setText("服务");
        }
    }

    @Override
    public int getItemCount() {
        return searchResultList.size() > 0 ? searchResultList.size() : 0;
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.projcetName)
        TextView projcetName;
        @BindView(R.id.procjetdescrp)
        TextView procjetdescrp;
        @BindView(R.id.projectype)
        TextView projectype;
        @BindView(R.id.projcetlocation)
        TextView projcetlocation;
        @BindView(R.id.projectpulishtime)
        TextView projectpulishtime;
        public SearchResultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
