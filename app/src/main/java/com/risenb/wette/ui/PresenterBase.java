package com.risenb.wette.ui;

import android.support.v4.app.FragmentActivity;

import com.risenb.wette.R;

/**
 * Presenter 基类
 *
 * @author Administrator
 */
public abstract class PresenterBase{

    /**
     * 请求成功
     */
    public static final String REQUEST_SUCCESS="1";


    protected FragmentActivity activity;

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    protected String getUrl(int id) {
        return getActivity().getResources().getString(R.string.service_host_address).concat(getActivity().getString(id));
    }
}
