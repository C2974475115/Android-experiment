package com.example.interface_components;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterCur extends BaseAdapter {
    List<Item> list;  //item的list对象
    Context context;  //上下文对象
//初始化
    public AdapterCur(List<Item> list,Context context){
        this.list=list;
        this.context=context;
//        列表同步方法
        notifyDataSetChanged();
    }
//    得到当前列表的选项数量
    public  int getCount(){
        return list.size();
    }
//    根据下标得到列表项
    public Item getItem(int position){
        return list.get(position);
    }
    public long getItemId(int position){
        return 0;
    }

    public View getView(final int position,View convertView,ViewGroup parent) {
        final ViewHolder viewHolder;
//        如果还没加载
        if (convertView == null) {
//            加载布局文件，并将各个选项以及每个选项中的内容一一对应
            convertView = View.inflate(context, R.layout.actionmode_row, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        得到十六进制的颜色的int值
        int lightblue = Color.parseColor("#9FDBDB");
        int white = Color.parseColor("#FFFFFF");
        viewHolder.textView.setText(list.get(position).getName());
//        如果被选中,改变颜色
        if (list.get(position).isBo() == true) {
            viewHolder.textView.setBackgroundColor(lightblue);
            viewHolder.imageView.setBackgroundColor(lightblue);
        }else {
            viewHolder.textView.setBackgroundColor(white);
            viewHolder.imageView.setBackgroundColor(white);
        }
        return convertView;

    }
    //创建一个内部类，定义每一个列表项所包含的东西
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }



}
