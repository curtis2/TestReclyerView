package com.mainbo.uclass_v4.testreclyerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView_one;
    private TestRecyclerAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开始设置RecyclerView
        recyclerView_one=(RecyclerView)this.findViewById(R.id.recyclerView_one);
        //设置固定大小
        recyclerView_one.setHasFixedSize(true);
        //创建线性布局
/*//     mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager = new GridLayoutManager(this, 3);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView_one.setLayoutManager(mLayoutManager);

//      recyclerView_one.addItemDecoration(new TestDecoration(getApplicationContext()));
//      recyclerView_one.addItemDecoration(new AdvanceDecoration(this,OrientationHelper.HORIZONTAL));*/
        GridLayoutManager girdLayoutManager=new GridLayoutManager(this,3);
        recyclerView_one.setLayoutManager(girdLayoutManager);

/*      StaggeredGridLayoutManager staggeredGridLayoutManager=new  StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        recyclerView_one.setLayoutManager(staggeredGridLayoutManager);*/
        //创建适配器，并且设置
        mAdapter = new TestRecyclerAdapter(this, new TestRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击了第"+position+"项", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               mAdapter.addItem("additem",5);
                mLayoutManager.scrollToPositionWithOffset(6,0);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //删除数据
//               mAdapter.removeItem("item4");
                mLayoutManager.scrollToPositionWithOffset(8,0);
            }
        });

        //添加默认的动画效果
        recyclerView_one.setItemAnimator(new DefaultItemAnimator());
        recyclerView_one.setAdapter(mAdapter);
//        mLayoutManager.scrollToPositionWithOffset(6,0);
    }


}
