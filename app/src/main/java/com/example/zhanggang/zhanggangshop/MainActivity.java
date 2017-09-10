package com.example.zhanggang.zhanggangshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.entries;

public class MainActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.quanxuan) TextView quanxuan;
    @BindView(R.id.fanxuan) TextView fanxuan;
    @BindView(R.id.recyclerview)
    XRecyclerView recyclerView;

    List<String> list = new ArrayList<>();
    HashMap<Integer, Boolean> hashMap = new HashMap<>();
    private MyAdapter adapter;
    @BindView(R.id.showTv)
    TextView showTv;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new Decoration());

        adapter = new MyAdapter(list,hashMap,MainActivity.this,showTv);
        recyclerView.setAdapter(adapter);

        recyclerView.setLoadingListener(this);
        //全选
        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<Map.Entry<Integer, Boolean>> entries = adapter.selectAll();
                int count=0;
                for (Map.Entry<Integer, Boolean> bean:entries) {
                    if (bean.getValue()==true){
                        count++;
                    }
                }
//                Toast.makeText(MainActivity.this, "111111111111"+count, Toast.LENGTH_SHORT).show();
                showTv.setText("个数："+count+"");
            }
        });
        //反选
        fanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.selectFan();
            }
        });
        //调用adapter的计数接口
        adapter.setCounter1(new MyAdapter.countener() {
            @Override
            public void setCount(HashMap<Integer, Boolean> hashMap) {
                int i = 0;
                Set<Map.Entry<Integer, Boolean>> entries = hashMap.entrySet();
                for (Map.Entry<Integer, Boolean> bean: entries) {
                    if (bean.getValue()==true){
                        i++;
                    }
                }
                showTv.setText("个数："+i+"");
            }
        });


    }

    private void init() {
        for (int i = 0; i < 50; i++) {
            list.add("商品" + i);
            hashMap.put(i, false); //默认全不选中
        }
    }

    @Override
    public void onRefresh() {
        list.clear();
        init();
        adapter.notifyDataSetChanged();
        recyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        init();
        adapter.notifyDataSetChanged();
        recyclerView.loadMoreComplete();
    }
}
