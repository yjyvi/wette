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

public class GoodsSkuP extends PresenterBase {

    private  GoodsSkuListener mGoodsSkuListener;

    public GoodsSkuP(GoodsSkuListener goodsSkuListener){
        this.mGoodsSkuListener = goodsSkuListener;
    }

    public void setGoodsSku(String goodsId, String properties){
        NetworkUtils.getNetworkUtils().goodsSku(goodsId, properties, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mGoodsSkuListener.requestSkuField();
            }

            @Override
            public void requestSuccess(String result) {

                mGoodsSkuListener.requestSkuSuccess();

                mGoodsSkuListener.requestSkuField();
            }
        });
    }

    public interface GoodsSkuListener{
        void requestSkuSuccess();
        void requestSkuField();

    }
}
