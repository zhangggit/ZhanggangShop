package com.example.zhanggang.zhanggangshop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类作用：recyclerview适配器
 * 时  间：2017/9/5 - 8:27.
 * 创建人：张刚
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<String> list;
    HashMap<Integer, Boolean> hashMap;

    public MyAdapter() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            list.add("商品"+i);
            hashMap.put(i, false); //默认全不选中
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.shangping.setText(list.get(position));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hashMap.put(position, !hashMap.get(position)); //点击一次 就把它设成自己的反值
                notifyDataSetChanged();
            }
        });
        //将状态赋给checkbox
        holder.checkBox.setChecked(hashMap.get(position));
    }
    //全选方法
    public void selectAll() {
        Set<Map.Entry<Integer, Boolean>> entries = hashMap.entrySet();  //的到集合
        boolean isSelected = false;   //保存当前的状态
        for (Map.Entry<Integer, Boolean> bean : entries) {
            Boolean value = bean.getValue();
            if (!value) { //只要有一个是未选中  就都设成 true
                isSelected = true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> bean : entries) {
            bean.setValue(isSelected);
            notifyDataSetChanged();
        }
    }
    //反选方法
    public void selectFan(){
        Set<Map.Entry<Integer, Boolean>> entries = hashMap.entrySet();
        for (Map.Entry<Integer, Boolean> bean : entries) {
            bean.setValue(!bean.getValue());
            notifyDataSetChanged();
        }
    }
    public int count(){
        return 0;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview)
        ImageView imageView;
        @BindView(R.id.shangping)
        TextView shangping;
        @BindView(R.id.check)
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
