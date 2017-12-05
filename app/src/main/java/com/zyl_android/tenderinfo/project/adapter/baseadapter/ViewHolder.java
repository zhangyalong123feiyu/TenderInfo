package com.zyl_android.tenderinfo.project.adapter.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bibinet on 2017-12-4.
 */

class ViewHolder extends RecyclerView.ViewHolder{
    private View convertView;
    public ViewHolder(View itemView) {
        super(itemView);
        this.convertView=itemView;
    }
    public static ViewHolder createViewHolder(Context context,ViewGroup parent, int layoutId){
        View view=LayoutInflater.from(context).inflate(layoutId,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public  View getConvertView(){
        return convertView;
    }
}