package com.mainbo.uclass_v4.testreclyerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */

public class MainActivity2 extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<RecyclerViewAdapter.ItemObject> mData = new ArrayList<>();
    private List<RecyclerViewAdapter.ItemObject> mDownlingList = new ArrayList<>();
    private List<RecyclerViewAdapter.ItemObject> mFinishList = new ArrayList<>();

    private Handler mTestHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // setlayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //setAdapter

        mData = new ArrayList<>();
        for (int i = 0; i <1000; i++) {
            RecyclerViewAdapter.ItemObject object=new RecyclerViewAdapter.ItemObject();
            object.setContentStr("this is content:" + i);
            if(i<500){
                object.setItemType(0);
                mDownlingList.add(object);
            }else{
                object.setItemType(1);
                mFinishList.add(object);
            }
            mData.add(object);
        }
        mAdapter = new RecyclerViewAdapter(this,mData,mDownlingList,mFinishList);
        mRecyclerView.setAdapter(mAdapter);
        Runnable mRunable  =new mRunable();
        mTestHandler.postDelayed(mRunable,1000);
    }

    private class mRunable implements Runnable{
        @Override
        public void run() {
         List<RecyclerViewAdapter.ItemObject> addDatas = new ArrayList<>();
            for (int i = 0; i <500; i++) {
                RecyclerViewAdapter.ItemObject object = mData.get(i);
                addDatas.add(object);
            }

            for (int i = 0; i <addDatas.size(); i++) {
                RecyclerViewAdapter.ItemObject object = addDatas.get(i);
                object.setItemType(1);
                mDownlingList.remove(object);
                mFinishList.add(0,object);
                mData.clear();
                mData.addAll(mDownlingList);
                mData.addAll(mFinishList);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

}

