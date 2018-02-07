package com.risenb.wette.ui.home;

import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/6.
 */

public class CreateOrderP extends PresenterBase {
    private  CreateOrderListener mCreateOrderListener;

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

                mCreateOrderListener.createSuccess();
            }
        });
    }


    public interface CreateOrderListener{
        void createSuccess();
        void createField();
    }
}
