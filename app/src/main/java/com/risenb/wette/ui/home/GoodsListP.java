package com.risenb.wette.ui.home;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.GoodsListBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by yjyvi on 2018/2/2.
 */

public class GoodsListP extends PresenterBase {

    private GoodsListListener mGoodsListListener;

    public GoodsListP(FragmentActivity fragmentActivity, GoodsListListener goodsListListener) {
        setActivity(fragmentActivity);
        this.mGoodsListListener = goodsListListener;
    }

    public void setGoodsList(int categoryTid, int pageSize, int pageNo) {
        NetworkUtils.getNetworkUtils().getGoodsList(categoryTid, pageSize, pageNo, new OKHttpManager.StringCallBack() {
            @Override
            public void requestSuccess(String result) {
                GoodsListBean goodsListBean = JSON.parseObject(result,GoodsListBean.class);
                mGoodsListListener.resultGoodsListData(goodsListBean);
            }

            @Override
            public void requestFailure(Call call, IOException e) {
                ToastUtils.showToast(e.getMessage());
            }
        });

    }

    public interface GoodsListListener {
        void resultGoodsListData(GoodsListBean result);
    }
}
