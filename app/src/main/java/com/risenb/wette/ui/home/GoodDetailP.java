package com.risenb.wette.ui.home;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.GoodDetailsBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by yjyvi on 2018/2/4.
 */

public class GoodDetailP extends PresenterBase {

    private GoodsDetailsListener mGoodsDetailsListener;

    public GoodDetailP(FragmentActivity fragmentActivity, GoodsDetailsListener productDetailsListener) {
        setActivity(fragmentActivity);
        this.mGoodsDetailsListener = productDetailsListener;
    }

    public void setProductDetailsData(String goodsId, String userId) {
        NetworkUtils.getNetworkUtils().getProductDetails(goodsId, userId, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                ToastUtils.showToast(e.getMessage());
            }

            @Override
            public void requestSuccess(String result) {
                GoodDetailsBean goodDetailsBean = JSON.parseObject(result, GoodDetailsBean.class);
                mGoodsDetailsListener.goodsData(goodDetailsBean.getData());
            }
        });
    }


    public interface GoodsDetailsListener {
        void goodsData(GoodDetailsBean.DataBean dataBean);
    }
}
