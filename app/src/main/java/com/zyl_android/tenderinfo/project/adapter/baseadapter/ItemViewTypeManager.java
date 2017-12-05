package com.zyl_android.tenderinfo.project.adapter.baseadapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

/**
 * Created by bibinet on 2017-12-4.
 */

public class ItemViewTypeManager<T> {
    private SparseArray<ItemViewType<T>> itemViewTypeSparseArray=new SparseArray<>();
    public  int itemViewTypeCount(){
        return itemViewTypeSparseArray.size();
    }
    public ItemViewTypeManager<T> addItemView(ItemViewType<T> itemViewType){
        int viewType = itemViewTypeSparseArray.size();
        if (itemViewType!=null) {
            itemViewTypeSparseArray.put(viewType,itemViewType);
            viewType++;
        }
        return this;
    }
    public ItemViewTypeManager<T> addItemView(int type,ItemViewType<T> itemViewType){
        if (itemViewType!=null) {
            itemViewTypeSparseArray.put(type,itemViewType);
        }
        return this;
    }
    public ItemViewTypeManager<T> removeItemView (ItemViewType<T> itemViewType){
        if (itemViewType==null) {
            throw new NullPointerException("itemviewType为空");
        }
        int indexRemove = itemViewTypeSparseArray.indexOfValue(itemViewType);
        itemViewTypeSparseArray.removeAt(indexRemove);
        return this;
    }
    public ItemViewTypeManager<T> removeItemView(int itemType){
        int removeIndex = itemViewTypeSparseArray.indexOfKey(itemType);
        if (removeIndex>=0) {
            itemViewTypeSparseArray.removeAt(removeIndex);
        }
        return this;
    }
    public int getItemViewType(T item, int position)
    {
        int delegatesCount = itemViewTypeSparseArray.size();
        for (int i = delegatesCount - 1; i >= 0; i--)
        {
            ItemViewType<T> delegate = itemViewTypeSparseArray.valueAt(i);
            if (delegate.isForViewType( item, position))
            {
                return itemViewTypeSparseArray.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void convert(ViewHolder holder, T item, int position)
    {
        int delegatesCount = itemViewTypeSparseArray.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            ItemViewType<T> delegate = itemViewTypeSparseArray.valueAt(i);

            if (delegate.isForViewType(item,position))
            {
                delegate.bindData(holder,item,position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }


    public int getItemViewLayoutId(int viewType)
    {
        return itemViewTypeSparseArray.get(viewType).getLayoutId();
    }

    public int getItemViewType(ItemViewType itemViewDelegate)
    {
        return itemViewTypeSparseArray.indexOfValue(itemViewDelegate);
    }
}
