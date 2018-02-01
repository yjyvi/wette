package com.risenb.wette.ui.classify;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.adapter.type.ClassifyLeftAdapter;
import com.risenb.wette.adapter.type.ClassifyRightAdapter;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.utils.ToastUtils;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by yjyvi on 2018/1/30.
 */

public class ClassifyFragment extends LazyLoadFragment {

    @ViewInject(R.id.rv_left)
    private RecyclerView rv_left;

    @ViewInject(R.id.rv_right)
    private RecyclerView rv_right;
    public ArrayList<String> mLeftData;
    private ArrayList<String> mRightData;
    public ClassifyRightAdapter mClassifyRightAdapter;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_classify, container, false);
    }

    @Override
    protected void setControlBasis() {
        setTitle("品类");
        leftVisible(R.mipmap.home_search);
        rightVisible(R.mipmap.home_cart);
    }

    @Override
    protected void prepareData() {

        leftData();
        rightData();

        //左侧列表
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setAutoMeasureEnabled(true);
        rv_left.setLayoutManager(layout);
        ClassifyLeftAdapter classifyLeftAdapter = new ClassifyLeftAdapter(R.layout.item_classify_left, mLeftData);
        rv_left.setAdapter(classifyLeftAdapter);
        rv_left.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        classifyLeftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                rv_left.scrollToPosition(position);
                view.setBackgroundResource(R.drawable.type_selected);
                ToastUtils.showToast(String.valueOf(position));
            }
        });


        //右则分类列表
        LinearLayoutManager layout2 = new LinearLayoutManager(getActivity());
        layout2.setAutoMeasureEnabled(true);
        rv_right.setLayoutManager(layout2);
        mClassifyRightAdapter = new ClassifyRightAdapter(R.layout.item_classify_right, mRightData);
        rv_right.setAdapter(mClassifyRightAdapter);
        rv_right.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    private void leftData() {
        mLeftData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mLeftData.add("一级分类" + i);
        }
    }

    private void rightData() {
        mRightData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mRightData.add("二级分类" + i);
        }
    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static ClassifyFragment newInstance() {
        Bundle bundle = new Bundle();
        ClassifyFragment classifyFragment = new ClassifyFragment();
        classifyFragment.setArguments(bundle);
        return classifyFragment;
    }
}
