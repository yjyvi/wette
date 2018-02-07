package com.risenb.wette.ui.home;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.risenb.wette.beans.HomeBean;
import com.risenb.wette.network.DataCallBack;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

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
        NetworkUtils.getNetworkUtils().getHomeData(new DataCallBack<HomeBean>() {
            @Override
            public void onSuccess(HomeBean result) {
                if (TextUtils.equals(REQUEST_SUCCESS, result.getStatus())) {
                    mHomeListener.homeDataSuccess(result);
                } else {
                    ToastUtils.showToast(result.getErrorMsg());
                    mHomeListener.requestField();
                }
            }

            @Override
            public void onStatusError(String errorMsg) {
                mHomeListener.requestField();
            }
        });
    }

    public interface HomeListener {
        void homeDataSuccess(HomeBean result);

        void requestField();
    }
}
