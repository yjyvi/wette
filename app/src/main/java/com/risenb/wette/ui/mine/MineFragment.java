package com.risenb.wette.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.ui.LazyLoadFragment;

/**
 * Created by yjyvi on 2018/1/30.
 */

public class MineFragment extends LazyLoadFragment {
    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater=inflater;
        view = inflater.inflate(R.layout.fragment_mine, container,false);
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {

    }


    /**
     * 初始化实例
     * @return
     */
    public static MineFragment newInstance() {
        Bundle bundle = new Bundle();
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }
}
