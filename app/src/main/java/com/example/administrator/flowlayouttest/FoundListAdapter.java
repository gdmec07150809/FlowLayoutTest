package com.example.administrator.flowlayouttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2016/11/28.
 */
public class FoundListAdapter extends BaseAdapter {

    private Context context;
    private ViewHolder holder;

    public FoundListAdapter(Context context){
        this.context = context;
        holder = new ViewHolder();
    }

    @Override
    public int getCount() {
        return 10;
    }//加载条数

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View contentView = null;
        if (contentView == null ){
            contentView = LayoutInflater.from(context).inflate(R.layout.found_list_item,viewGroup,false);//先获取inflate,再加载xml,添加到viewGroup
            contentView.setTag(holder);
        }else{
            holder = (ViewHolder) contentView.getTag();
        }

        return contentView;
    }

    private class ViewHolder{

    }

}
