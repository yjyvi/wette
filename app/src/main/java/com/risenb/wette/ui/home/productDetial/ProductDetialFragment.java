package com.risenb.wette.ui.home.productDetial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.risenb.wette.R;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class ProductDetialFragment extends LazyLoadFragment {

    @ViewInject(R.id.web_content)
    private WebView web_content;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater=inflater;
        view=inflater.inflate(R.layout.fragment_product_detial,container,false);
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
    public static ProductDetialFragment newInstance() {
        Bundle bundle = new Bundle();
        ProductDetialFragment productFragment = new ProductDetialFragment();
        productFragment.setArguments(bundle);
        return productFragment;
    }
}
