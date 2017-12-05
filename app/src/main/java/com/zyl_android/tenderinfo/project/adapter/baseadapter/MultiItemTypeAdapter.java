package com.zyl_android.tenderinfo.project.adapter.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bibinet on 2017-12-4.
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder>
{
    protected Context mContext;
    protected List<T> mDatas;

    protected ItemViewTypeManager mItemViewDelegateManager;
    protected OnItemClickListener<T> mOnItemClickListener;
    public int offset = 0;

    public MultiItemTypeAdapter(Context context, List<T> datas)
    {
        mContext = context;
        mDatas = datas;
        mItemViewDelegateManager = new ItemViewTypeManager();
    }

    public MultiItemTypeAdapter(Context context)
    {
        this(context, new ArrayList<T>());
    }

    @Override
    public int getItemViewType(int position)
    {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        int layoutId = mItemViewDelegateManager.getItemViewLayoutId(viewType);
        ViewHolder holder = ViewHolder.createViewHolder(mContext,parent, layoutId);
        setListener(parent, holder, viewType);
        return holder;
    }

    public void convert(ViewHolder holder, T t)
    {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    protected boolean isEnabled(int viewType)
    {
        return true;
    }


    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType)
    {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder, mDatas.get(position - offset), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemClickListener.onItemLongClick(v, viewHolder, mDatas.get(position - offset), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount()
    {
        int itemCount = mDatas.size();
        return itemCount;
    }


    public List<T> getDatas()
    {
        return mDatas;
    }

    public MultiItemTypeAdapter addItemViewType(ItemViewType<T> itemViewType)
    {
        mItemViewDelegateManager.addItemView(itemViewType);
        return this;
    }

    public MultiItemTypeAdapter addItemViewType(int viewType, ItemViewType<T> itemViewDelegate)
    {
        mItemViewDelegateManager.addItemView(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager()
    {
        return mItemViewDelegateManager.itemViewTypeCount() > 0;
    }

    public interface OnItemClickListener<T>
    {
        void onItemClick(View view, RecyclerView.ViewHolder holder, T o, int position);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, T o, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void addDataAll(List data) {
        mDatas.addAll(data);
    }

    public void clearData() {
        mDatas.clear();
    }

}