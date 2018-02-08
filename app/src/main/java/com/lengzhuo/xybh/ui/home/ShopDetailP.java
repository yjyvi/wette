package com.lengzhuo.xybh.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.ShopDetailBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.PresenterBase;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;

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
