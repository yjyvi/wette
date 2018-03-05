package com.lengzhuo.xybh.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.GoodsListBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.PresenterBase;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;

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
                if (goodsListBean != null) {
                    if (TextUtils.equals(REQUEST_SUCCESS, goodsListBean.getStatus())) {
                        mGoodsListListener.resultGoodsListData(goodsListBean);
                    } else {
                        ToastUtils.showToast(goodsListBean.getErrorMsg());
                        mGoodsListListener.goodsListField();
                    }
                }
            }

            @Override
            public void requestFailure(Call call, IOException e) {
                mGoodsListListener.goodsListField();
            }
        });

    }

    public void setGoodsList(int categoryTid, String shopId, int pageNo, int pageSize) {
        NetworkUtils.getNetworkUtils().getGoodList(categoryTid, shopId, pageSize, pageNo, new OKHttpManager.StringCallBack() {
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
