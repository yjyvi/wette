package com.risenb.wette.ui.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.adapter.type.ClassifyRightAdapter;
import com.risenb.wette.beans.ClassifyBean;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yjyvi on 2018/2/1.
 */

public class TwoClassifyFragment extends LazyLoadFragment {

    @ViewInject(R.id.rv_category)
    private RecyclerView rv_category;

    public ClassifyRightAdapter mGoodsListAdapter;
    public List<ClassifyBean.DataBean.ListBeanX> mClassifyData;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_two_type_list, container, false);
    }

    @Override
    protected void setControlBasis() {
    }

    @Override
    protected void prepareData() {
        mClassifyData = (List<ClassifyBean.DataBean.ListBeanX>) getArguments().getSerializable("classifyData");
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setAutoMeasureEnabled(true);
        rv_category.setLayoutManager(layout);
        mGoodsListAdapter = new ClassifyRightAdapter(R.layout.item_classify_right, mClassifyData);
        rv_category.setAdapter(mGoodsListAdapter);
    }

    /**
     * 初始化实例
     *
     * @return
     */
    public static TwoClassifyFragment newInstance(List<ClassifyBean.DataBean.ListBeanX> classifyData) {
        Bundle bundle = new Bundle();
        TwoClassifyFragment twoClassifyFragment = new TwoClassifyFragment();
        bundle.putSerializable("classifyData", (Serializable) classifyData);
        twoClassifyFragment.setArguments(bundle);
        return twoClassifyFragment;
    }

}
