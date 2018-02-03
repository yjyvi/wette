package com.risenb.wette.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.Event;

/**
 * Created by yjyvi on 2018/1/30.
 */

public class MineFragment extends LazyLoadFragment {
    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Event(value = {
            R.id.ll_login_or_register,
            R.id.ll_account_setting,
            R.id.fl_feedback,
            R.id.tv_collection,
            R.id.ll_order
    }, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_login_or_register:
                LoginActivity.toActivity(view.getContext());
                break;
            case R.id.ll_account_setting:
                AccountSettingActivity.toActivity(view.getContext());
                break;
            case R.id.fl_feedback:
                FeedbackActivity.toActivity(view.getContext());
                break;
            case R.id.tv_collection:
                CollectionActivity.toActivity(view.getContext());
                break;
            case R.id.ll_order:
                OrderActivity.toActivity(view.getContext());
                break;
        }
    }

    /**
     * 初始化实例
     */
    public static MineFragment newInstance() {
        Bundle bundle = new Bundle();
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }
}
