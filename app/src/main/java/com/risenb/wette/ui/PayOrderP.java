package com.risenb.wette.ui;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/6.
 */

public class PayOrderP extends PresenterBase {

    private PayOrderListener mPayOrderListener;

    public PayOrderP(PayOrderListener payOrderListener) {
        this.mPayOrderListener = payOrderListener;

    }

    public void setPayOrder(String orderId, String payChannel) {
        NetworkUtils.getNetworkUtils().payOrder(orderId, payChannel, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {

            }

            @Override
            public void requestSuccess(String result) {


                JSONObject parseObject = JSON.parseObject(result);


                if (TextUtils.equals(REQUEST_SUCCESS, parseObject.getString("status"))) {
                    String data = parseObject.getString("data");

                    if (!TextUtils.isEmpty(data)) {
                        JSONObject jsonObject = JSON.parseObject(data);
                        String sign = jsonObject.getString("Sign");
                        if (TextUtils.isEmpty(sign)) {
                            sign = jsonObject.getString("WeCharPay");
                        }
                        mPayOrderListener.getSignSuccess(sign);
                    }
                } else {
                    String message = parseObject.getString("errorMsg");
                    mPayOrderListener.getSignField(message);
                }

            }
        });
    }


    public interface PayOrderListener {
        void getSignSuccess(String sign);

        void getSignField(String message);
    }
}
