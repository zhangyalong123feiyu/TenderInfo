package com.zyl_android.tenderinfo.project.adapter.baseadapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by bibinet on 2017-12-4.
 */

public interface ItemViewType<T> {
    int getLayoutId();
    boolean isForViewType(T item,int postion);
    void bindData(ViewHolder viewHolder,T t,int position);
}
