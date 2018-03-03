package com.lengzhuo.xybh.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.NetBaseBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.PresenterBase;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/6.
 * 添加到购物车
 */

public class AddCartP extends PresenterBase {

    private AddCartListener mAddCartListener;

    public AddCartP(AddCartListener addCartListener) {
        this.mAddCartListener = addCartListener;
    }

    public void setAddCart(String shopId, String goodsId, String skuId,  String amount) {

        NetworkUtils.getNetworkUtils().addCart(shopId, goodsId, skuId,  amount, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mAddCartListener.addCartField();
            }

            @Override
            public void requestSuccess(String result) {
                NetBaseBean netBaseBean = JSON.parseObject(result, NetBaseBean.class);
                if (TextUtils.equals(REQUEST_SUCCESS, netBaseBean.getStatus())) {
                    mAddCartListener.addCartSuccess();
                } else {
                    ToastUtils.showToast(netBaseBean.getErrorMsg());
                    mAddCartListener.addCartField();
                }
            }
        });
    }

    public interface AddCartListener {
        void addCartSuccess();

        void addCartField();
    }
}
