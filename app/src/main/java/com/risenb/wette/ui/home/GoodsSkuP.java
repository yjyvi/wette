package com.risenb.wette.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.GoodSkuBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/6.
 */

public class GoodsSkuP extends PresenterBase {

    private GoodsSkuListener mGoodsSkuListener;

    public GoodsSkuP(GoodsSkuListener goodsSkuListener) {
        this.mGoodsSkuListener = goodsSkuListener;
    }

    public void setGoodsSku(final String goodsId, String properties) {
        NetworkUtils.getNetworkUtils().goodsSku(goodsId, properties, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mGoodsSkuListener.requestSkuField();
            }

            @Override
            public void requestSuccess(String result) {
                GoodSkuBean goodSkuBean = JSON.parseObject(result, GoodSkuBean.class);
                if (TextUtils.equals(REQUEST_SUCCESS, goodSkuBean.getStatus())) {
                    mGoodsSkuListener.requestSkuSuccess(goodSkuBean.getData());
                } else {
                    ToastUtils.showToast(goodSkuBean.getErrorMsg());
                    mGoodsSkuListener.requestSkuField();
                }

            }
        });
    }

    public interface GoodsSkuListener {
        void requestSkuSuccess(GoodSkuBean.DataBean data);

        void requestSkuField();

    }
}
