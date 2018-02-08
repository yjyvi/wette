package com.lengzhuo.xybh.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.CreateOrderBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.PresenterBase;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/6.
 */

public class CreateOrderP extends PresenterBase {
    private CreateOrderListener mCreateOrderListener;

    public CreateOrderP(CreateOrderListener createOrderListener) {
        this.mCreateOrderListener = createOrderListener;
    }


    public void setCreateOrder(String goodsId, String addressId) {
        NetworkUtils.getNetworkUtils().createOrder(goodsId, addressId, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mCreateOrderListener.createField();
            }

            @Override
            public void requestSuccess(String result) {
                CreateOrderBean createOrderBean = JSON.parseObject(result, CreateOrderBean.class);
                if (TextUtils.equals(REQUEST_SUCCESS, createOrderBean.getStatus())) {
                    mCreateOrderListener.createSuccess(String.valueOf(createOrderBean.getData().getOrderId()));
                } else {
                    ToastUtils.showToast(createOrderBean.getErrorMsg());
                    mCreateOrderListener.createField();
                }
            }
        });
    }


    public interface CreateOrderListener {
        void createSuccess(String orderNo);

        void createField();
    }
}