package com.example.zhanggang.zhanggangshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.quanxuan) TextView quanxuan;
    @BindView(R.id.fanxuan) TextView fanxuan;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SwipyRefreshLayout swipyRefreshLayout;

    List<String> list = new ArrayList<>();
    HashMap<Integer, Boolean> hashMap = new HashMap<>();
    private MyAdapter adapter;
    private TextView showTv;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        showTv = (TextView) findViewById(R.id.showTv);

        //设置是否支持刷新和加载更多
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                list.clear();
                init();
                adapter.notifyDataSetChanged();
                swipyRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onLoad(int index) {
                init();
                adapter.notifyDataSetChanged();
                swipyRefreshLayout.setRefreshing(false);
            }
        });

        init();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.addItemDecoration(new Decoration());

        adapter = new MyAdapter(list,hashMap,MainActivity.this,showTv);
        recyclerView.setAdapter(adapter);

        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.selectAll();
            }
        });
        fanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.selectFan();
            }
        });


    }

    private void init() {
        for (int i = 0; i < 50; i++) {
            list.add("商品" + i);
            hashMap.put(i, false); //默认全不选中
        }
    }


}
