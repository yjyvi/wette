package com.risenb.wette.ui.home.productDetial;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.SearchBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @date 2018/2/3
 * 搜索商品
 */

public class SearchP extends PresenterBase {
    private SearchGoodsListener mSearchGoodsListener;

    public SearchP(SearchGoodsListener searchGoodsListener) {
        this.mSearchGoodsListener = searchGoodsListener;
    }


    public void setSearchData(String keyword) {
        NetworkUtils.getNetworkUtils().getSearch(keyword, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mSearchGoodsListener.searchField();
            }

            @Override
            public void requestSuccess(String result) {

                SearchBean searchBean = JSON.parseObject(result,SearchBean.class);

                if (TextUtils.equals(REQUEST_SUCCESS, searchBean.getStatus())) {
                    mSearchGoodsListener.searchData(searchBean.getData());
                } else {
                    ToastUtils.showToast(searchBean.getErrorMsg());
                    mSearchGoodsListener.searchField();
                }
            }


        });
    }

    public interface SearchGoodsListener {
        void searchData(SearchBean.DataBean searchBean);

        void searchField();
    }
}
