package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.bean.HomeFiveProjectBean;
import com.zyl_android.tenderinfo.project.ui.activity.ProjectDetailaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-7.
 */

public class FragmentHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int TYPE_ONE = 0;
    private static int TYPE_TWO = 1;
    private List<HomeFiveProjectBean.ItemsBean> homeProjectInfoList = new ArrayList<>();
    private Context context;

    public FragmentHomeAdapter(List<HomeFiveProjectBean.ItemsBean> homeProjectInfoList, Context context) {
        this.homeProjectInfoList = homeProjectInfoList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = LayoutInflater.from(context).inflate(R.layout.frag_home_item_one, null);
            FragHomeViewHolderOne fragHomeViewHolderOne = new FragHomeViewHolderOne(view);
            return fragHomeViewHolderOne;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.frag_home_item_two, null);
            FragHomeViewHolderTwo fragHomeViewHolderOne = new FragHomeViewHolderTwo(view);
            return fragHomeViewHolderOne;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FragHomeViewHolderOne) {
        ((FragHomeViewHolderOne) holder).projcetName.setText("项目信息");
        }else if (holder instanceof FragHomeViewHolderTwo) {
            ((FragHomeViewHolderTwo) holder).companyName.setText(homeProjectInfoList.get(position-1).getProjectName());
            ((FragHomeViewHolderTwo) holder).projectDescrp.setText(homeProjectInfoList.get(position-1).getProjectDescrp());
            ((FragHomeViewHolderTwo) holder).projectLoaction.setText(homeProjectInfoList.get(position-1).getProjectLocation());
            ((FragHomeViewHolderTwo) holder).projectTime.setText(homeProjectInfoList.get(position-1).getProjectTime());
            if (homeProjectInfoList.get(position-1).getProjectType().equals("A")) {
                ((FragHomeViewHolderTwo) holder).projectImage.setImageResource(R.mipmap.shouye_gongcheng);
            } else if (homeProjectInfoList.get(position-1).getProjectType().equals("B")) {
                ((FragHomeViewHolderTwo) holder).projectImage.setImageResource(R.mipmap.shouye_huowu);
            } else if(homeProjectInfoList.get(position-1).getProjectType().equals("C")){
                ((FragHomeViewHolderTwo) holder).projectImage.setImageResource(R.mipmap.shouye_fuw);
            }
        		}
    }

    @Override
    public int getItemCount() {
        return homeProjectInfoList.size()>0?homeProjectInfoList.size()+1:0;
    }

    @OnClick({R.id.projectInfoMore,R.id.projectLayout})
    public void onViewClicked(View view) {
        	switch (view.getId()) {
        			case R.id.projectInfoMore:
                        Toast.makeText(context,"点击更多",Toast.LENGTH_SHORT).show();
        				break;
        			case R.id.projectLayout:

        				break;

        			default:
        				break;
        			}
    }

    class FragHomeViewHolderOne extends RecyclerView.ViewHolder {
        @BindView(R.id.projcetName)
        TextView projcetName;
        @BindView(R.id.projectInfoMore)
        TextView projectInfoMore;
        public FragHomeViewHolderOne(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class FragHomeViewHolderTwo extends RecyclerView.ViewHolder {
        @BindView(R.id.projectImage)
        ImageView projectImage;
        @BindView(R.id.companyName)
        TextView companyName;
        @BindView(R.id.projectDescrp)
        TextView projectDescrp;
        @BindView(R.id.projectLoaction)
        TextView projectLoaction;
        @BindView(R.id.projectIsTop)
        ImageView projectIsTop;
        @BindView(R.id.projectTime)
        TextView projectTime;
        @BindView(R.id.projectLayout)
        LinearLayout projectLayout;
        public FragHomeViewHolderTwo(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Context)context).startActivity(new Intent(context, ProjectDetailaActivity.class));
                }
            });
        }
    }
}
