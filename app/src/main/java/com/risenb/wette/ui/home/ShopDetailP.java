package com.risenb.wette.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.ShopDetailBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/5.
 */

public class ShopDetailP extends PresenterBase {
    private  ShopDataListener mShopDataListener;

    public ShopDetailP(ShopDataListener shopDataListener){
        this.mShopDataListener = shopDataListener;
    }

    public void setShopData(String shopId,int page,int limit){
        NetworkUtils.getNetworkUtils().getShopDetail(shopId, String.valueOf(page), String.valueOf(limit), new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mShopDataListener.getDataField();
            }

            @Override
            public void requestSuccess(String result) {
                ShopDetailBean shopDetailBean= JSON.parseObject(result,ShopDetailBean.class);
                if (TextUtils.equals(REQUEST_SUCCESS,shopDetailBean.getStatus())) {
                    mShopDataListener.getDataSuccess(shopDetailBean.getData());
                }else {
                    ToastUtils.showToast(shopDetailBean.getErrorMsg());
                    mShopDataListener.getDataField();
                }
            }
        });
    }


    public interface ShopDataListener{
        void getDataSuccess(ShopDetailBean.DataBean data);
        void getDataField();
    }
}
