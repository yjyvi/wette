package com.risenb.wette.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.HomeAdapter;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by yjyvi on 2018/1/30.
 */

public class HomeFragment extends LazyLoadFragment {

    @ViewInject(R.id.rv_home)
    private RecyclerView rv_home;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater=inflater;
        view = inflater.inflate(R.layout.fragment_home, container,false);
    }

    @Override
    protected void setControlBasis() {
        setImgTitle(R.mipmap.home_logo);
        leftVisible(R.mipmap.home_search);
        rightVisible(R.mipmap.home_cart);
    }

    @Override
    protected void prepareData() {
        rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_home.setAdapter(new HomeAdapter());
    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }
}
