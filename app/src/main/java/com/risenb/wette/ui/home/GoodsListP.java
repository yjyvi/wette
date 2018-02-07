package com.risenb.wette.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.GoodsListBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @date 2018/2/2
 */

public class GoodsListP extends PresenterBase {

    private GoodsListListener mGoodsListListener;

    public GoodsListP(GoodsListListener goodsListListener) {
        this.mGoodsListListener = goodsListListener;
    }

    public void setGoodsList(int categoryTid, int pageNo, int pageSize) {
        NetworkUtils.getNetworkUtils().getGoodList(categoryTid, pageSize, pageNo, new OKHttpManager.StringCallBack() {
            @Override
            public void requestSuccess(String result) {
                GoodsListBean goodsListBean = JSON.parseObject(result, GoodsListBean.class);
                if (TextUtils.equals(REQUEST_SUCCESS, goodsListBean.getStatus())) {
                    mGoodsListListener.resultGoodsListData(goodsListBean);
                } else {
                    ToastUtils.showToast(goodsListBean.getErrorMsg());
                    mGoodsListListener.goodsListField();
                }
            }

            @Override
            public void requestFailure(Call call, IOException e) {
                mGoodsListListener.goodsListField();
            }
        });

    }

    public interface GoodsListListener {
        void resultGoodsListData(GoodsListBean result);

        void goodsListField();
    }
}
