package com.risenb.wette.ui.home;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.HomeBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @date 2018/2/2
 * 首页接口
 */

public class HomeP extends PresenterBase {

    private HomeListener mHomeListener;

    public HomeP(FragmentActivity fragmentActivity, HomeListener homeListener) {
        setActivity(fragmentActivity);
        this.mHomeListener = homeListener;
    }


    public void setHomeData() {
        NetworkUtils.getNetworkUtils().getHomeData(new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mHomeListener.requestField();
            }

            @Override
            public void requestSuccess(String result) {

                HomeBean homeBean = JSON.parseObject(result, HomeBean.class);

                if (TextUtils.equals(REQUEST_SUCCESS, homeBean.getStatus())) {
                    mHomeListener.homeDataSuccess(homeBean.getData());
                } else {
                    ToastUtils.showToast(homeBean.getErrorMsg());
                    mHomeListener.requestField();
                }
            }

        });
    }

    public interface HomeListener {
        void homeDataSuccess(HomeBean.DataBean result);

        void requestField();
    }
}
