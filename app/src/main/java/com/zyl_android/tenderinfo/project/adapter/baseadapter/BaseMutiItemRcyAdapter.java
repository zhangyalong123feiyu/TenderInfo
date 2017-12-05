package com.zyl_android.tenderinfo.project.adapter.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bibinet on 2017-12-4.
 */

public abstract class BaseMutiItemRcyAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public BaseMutiItemRcyAdapter(final Context context, final int layoutId) {
        this(context, layoutId, new ArrayList<T>());
    }

    public BaseMutiItemRcyAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewType(new ItemViewType<T>() {
            @Override
            public int getLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void bindData(ViewHolder holder, T t, int position) {
                BaseMutiItemRcyAdapter.this.convert(holder, t, position);
            }
        });
    }
    protected abstract void convert(ViewHolder holder, T t, int position);
}
