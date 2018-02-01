package com.risenb.wette.ui.home.productDetial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.ui.LazyLoadFragment;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class ProductFragment extends LazyLoadFragment {




    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {

    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static ProductFragment newInstance() {
        Bundle bundle = new Bundle();
        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);
        return productFragment;
    }
}
