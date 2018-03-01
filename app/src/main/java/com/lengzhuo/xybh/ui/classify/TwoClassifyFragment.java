package com.lengzhuo.xybh.ui.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.type.ClassifyRightAdapter;
import com.lengzhuo.xybh.beans.ClassifyBean;
import com.lengzhuo.xybh.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.List;

/**
 * @author yjyvi
 * @date 2018/2/1
 * 右侧二级分类界面
 */

public class TwoClassifyFragment extends LazyLoadFragment {

    @ViewInject(R.id.rv_category)
    private RecyclerView rv_category;

    @ViewInject(R.id.ll_empty_view)
    private LinearLayout ll_empty_view;

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

        if (mClassifyData != null) {
            LinearLayoutManager layout = new LinearLayoutManager(getActivity());
            layout.setAutoMeasureEnabled(true);
            rv_category.setLayoutManager(layout);
            mGoodsListAdapter = new ClassifyRightAdapter(R.layout.item_classify_right, mClassifyData);
            rv_category.setAdapter(mGoodsListAdapter);
            showEmptyView(mClassifyData,ll_empty_view);
        }

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
