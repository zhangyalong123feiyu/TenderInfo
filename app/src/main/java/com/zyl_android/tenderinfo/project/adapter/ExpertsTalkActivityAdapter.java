package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.zyl_android.tenderinfo.project.utils.B64PhotoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-25.
 */

public class ExpertsTalkActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ExpertsDataBean.ItemsBean> expertDataList = new ArrayList<>();
    private Context context;
    private static int contentTypeView = 1;
    private static int expertsTypeView = 2;
    private static int postTypeView = 3;

    private String askContent;
    private String askTitle;

    public ExpertsTalkActivityAdapter(List<ExpertsDataBean.ItemsBean> expertDataList, Context context) {
        this.expertDataList = expertDataList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return contentTypeView;
        } else if (position == expertDataList.size()+1) {
            return postTypeView;
        }
        return expertsTypeView ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == contentTypeView) {
            View view = LayoutInflater.from(context).inflate(R.layout.activityexperts_item_one, null);
            ExpertsViewHolderOne expertsViewHolderOne = new ExpertsViewHolderOne(view);
            return expertsViewHolderOne;
        } else if (viewType == expertsTypeView) {
            View view = LayoutInflater.from(context).inflate(R.layout.activityexperts_item_two, null);
            ExpertsViewHolderTwo expertsViewHoldertwo = new ExpertsViewHolderTwo(view);
            return expertsViewHoldertwo;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.activityexperts_item_three, null);
        ExpertsViewHolderThree expertsViewHolderthree = new ExpertsViewHolderThree(view);
        return expertsViewHolderthree;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExpertsViewHolderOne) {
            askContent = ((ExpertsViewHolderOne) holder).questionContent.getText().toString().trim();
            askTitle = ((ExpertsViewHolderOne) holder).questionContent.getText().toString().trim();
        } else if (holder instanceof ExpertsViewHolderTwo) {
            ((ExpertsViewHolderTwo) holder).expertsIntrodction.setText(expertDataList.get(position - 1).getDescription());
            ((ExpertsViewHolderTwo) holder).expertsMoney.setText(expertDataList.get(position - 1).getPrice());
            ((ExpertsViewHolderTwo) holder).expertsTitle.setText(expertDataList.get(position - 1).getTitle());
            Bitmap bitmap = B64PhotoUtils.stringToBitmap(expertDataList.get(position - 1).getHeadPortrait());
            ((ExpertsViewHolderTwo) holder).expertsPhoto.setImageBitmap(B64PhotoUtils.makeRoundCorner(bitmap));
        }
    }

    @Override
    public int getItemCount() {
        return expertDataList.size() > 0 ? expertDataList.size() + 2 : 2;
    }

    @OnClick({R.id.expertsLayout,R.id.postData})
    public void onViewClicked(View view) {
        	switch (view.getId()) {
        			case R.id.expertsLayout:

        				break;
        			case R.id.postData:

        				break;

        			default:
        				break;
        			}
    }

    class ExpertsViewHolderOne extends RecyclerView.ViewHolder {
        @BindView(R.id.userQuestion)
        EditText userQuestion;
        @BindView(R.id.questionContent)
        EditText questionContent;

        public ExpertsViewHolderOne(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ExpertsViewHolderTwo extends RecyclerView.ViewHolder {
        @BindView(R.id.expertsPhoto)
        ImageView expertsPhoto;
        @BindView(R.id.expertsMoney)
        TextView expertsMoney;
        @BindView(R.id.expertsTitle)
        TextView expertsTitle;
        @BindView(R.id.expertsIntrodction)
        TextView expertsIntrodction;
        @BindView(R.id.expertsLayout)
        LinearLayout expertsLayout;

        public ExpertsViewHolderTwo(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ExpertsViewHolderThree extends RecyclerView.ViewHolder {
        @BindView(R.id.postData)
        Button postData;
        public ExpertsViewHolderThree(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
