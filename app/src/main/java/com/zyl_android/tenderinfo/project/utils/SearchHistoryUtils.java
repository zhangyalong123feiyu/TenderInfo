package com.zyl_android.tenderinfo.project.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Wh on 2017-7-18.
 */

public class SearchHistoryUtils {
    public static void writeData(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        List<String> l = getData(context);
//        if(dataList.size()>20){
//            dataList.remove(dataList.size()-1);
//        }
        l.remove(key);
        l.add(0, key);
        editor.putString("history", listToString(l));
        editor.commit();
    }

    public static void removeData(Context context, String str){
        SharedPreferences sp = context.getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        List<String> datas = stringToList(sp.getString("history", ""));
        datas.remove(str);
        String ns = listToString(datas);
        editor.putString("history", ns).commit();

    }
    public static List<String> getData(Context context) {
        SharedPreferences sp = context.getSharedPreferences("history", Context.MODE_PRIVATE);
        String s = sp.getString("history", "");
        List<String> l = stringToList(s);
        return l;
    }

    public static void clearData(Context context) {
        SharedPreferences sp = context.getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("history", "").commit();
    }

    public static String listToString(List<String> list){
        if(list==null){
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for(String string :list) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

    private static List<String> stringToList(String strs){
        if(strs.equals("")){
            return new ArrayList<String>();
        }
        String str [] = strs.split(",");
        List<String> l = new ArrayList<String>();
        for(int i=0 ; i<str.length ; i++){
            l.add(str[i]);
        }
        return l;
    }
}
