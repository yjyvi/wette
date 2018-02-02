package com.risenb.wette.ui.home;

import android.support.v4.app.FragmentActivity;

import com.risenb.wette.beans.GoodsListBean;
import com.risenb.wette.network.DataCallBack;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

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
        NetworkUtils.getNetworkUtils().getGoodsList(categoryTid, pageSize, pageNo, new DataCallBack<GoodsListBean>() {
            @Override
            public void onSuccess(GoodsListBean result) {
                mGoodsListListener.resultGoodsListData(result);
            }

            @Override
            public void onStatusError(String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }
        });

    }

    public interface GoodsListListener {
        void resultGoodsListData(GoodsListBean result);
    }
}
