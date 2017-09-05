package com.example.zhanggang.zhanggangshop;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 类作用：分割线
 * 时  间：2017/9/5 - 8:26.
 * 创建人：张刚
 */

public class Decoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(10,10,10,10);
    }
}
