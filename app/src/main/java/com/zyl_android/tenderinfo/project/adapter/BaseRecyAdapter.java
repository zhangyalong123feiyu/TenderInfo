package com.zyl_android.tenderinfo.project.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.utils.CacheUtils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bibinet on 2017-12-2.
 */

public abstract class BaseRecyAdapter<T> extends RecyclerView.Adapter<BaseRecyAdapter.BaseViewHolder> {
    private List<T> datas=new ArrayList<>();
    private Context context;

    public BaseRecyAdapter(Context context,List<T> data) {
        this.context = context;
        this.datas=data;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(getChildLayout(),null);
        BaseViewHolder baseViewHolder=new BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyAdapter.BaseViewHolder holder, int position) {
        onBindData(holder,datas,position);
    }

    protected abstract int getChildLayout();


    //子view用于绑定数据
    protected abstract void onBindData(BaseViewHolder holder, List<T> datas, int position);



    @Override
    public int getItemCount() {
        return datas.size()>0?datas.size():0;
    }

    //添加数据
    public void addData(List<T> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }
    //刷新数据
    public void refreshData(List<T> datas){
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder{
        private SparseArray<View> views;//存放item的控件
        public BaseViewHolder(View itemView) {
            super(itemView);
            views=new SparseArray<>();
        }
        //根据item中的控件id获取控件
        public <T extends View>T getView(int viewId){
            View childView = views.get(viewId);
            if (childView==null) {
            			childView=itemView.findViewById(viewId);
                views.put(viewId,childView);
            		}
            		return (T)childView;
        }
        public void setText(int id, String text) {
            TextView tx = (TextView) itemView.findViewById(id);
            tx.setText(text);
    }
        public void setImageView(int id,String url){
//            Glide.with(context).load(url).error(R.mipmap.ic_launcher).into(imageView);
            AppCompatImageView imageView = (AppCompatImageView) itemView.findViewById(id);
            ImageLoader imageLoader=new ImageLoader(context,imageView);
            imageLoader.loadBitmaps(imageView,url);
        }
    }
}
