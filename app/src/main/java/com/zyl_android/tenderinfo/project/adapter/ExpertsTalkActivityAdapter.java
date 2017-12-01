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
    private static final int contentTypeView = 1;
    private static final int expertsTypeView = 2;
    private static final int postTypeView = 3;
    private int oldPostion=-1;//初始没有点击时的位置
    private String askContent;
    private String askTitle;
    private OnPostDataClickListioner onPostDataClickListioner;
    private String expertsCode;

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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ExpertsViewHolderOne) {
            askContent = ((ExpertsViewHolderOne) holder).questionContent.getText().toString().trim();
            askTitle = ((ExpertsViewHolderOne) holder).questionContent.getText().toString().trim();
        } else if (holder instanceof ExpertsViewHolderTwo) {
            ((ExpertsViewHolderTwo) holder).expertsIntrodction.setText(expertDataList.get(position - 1).getDescription());
            ((ExpertsViewHolderTwo) holder).expertsMoney.setText(expertDataList.get(position - 1).getPrice());
            ((ExpertsViewHolderTwo) holder).expertsTitle.setText(expertDataList.get(position - 1).getTitle());
            Bitmap bitmap = B64PhotoUtils.stringToBitmap(expertDataList.get(position - 1).getHeadPortrait());
            ((ExpertsViewHolderTwo) holder).expertsPhoto.setImageBitmap(B64PhotoUtils.makeRoundCorner(bitmap));
            if (oldPostion!=position) {
                ((ExpertsViewHolderTwo) holder).expertsLayout.setSelected(false);
            		}else {
                ((ExpertsViewHolderTwo) holder).expertsLayout.setSelected(true);
                expertsCode=expertDataList.get(position-1).getCode();
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (oldPostion!=position) {
                        oldPostion=position;
                    }else {
                        oldPostion=-1;//还原初始值
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return expertDataList.size() > 0 ? expertDataList.size() + 2 : 2;
    }
    public void setOnPostDataClickListioner(OnPostDataClickListioner onPostDataClickListioner){
        this.onPostDataClickListioner=onPostDataClickListioner;
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

        public ExpertsViewHolderTwo(final View itemView) {
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
            postData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPostDataClickListioner.onPostDataClickListioner(askContent,askTitle,expertsCode);
                }
            });
        }
    }
   public interface OnPostDataClickListioner{
        void onPostDataClickListioner(String askContent,String questionContent,String expertsCode);
    }
}
