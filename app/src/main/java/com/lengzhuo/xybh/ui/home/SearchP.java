package com.lengzhuo.xybh.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.SearchBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.PresenterBase;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;

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


    public void setSearchData(String keyword, int page, int limit) {
        NetworkUtils.getNetworkUtils().getSearch(keyword, String.valueOf(page), String.valueOf(limit), new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mSearchGoodsListener.searchField();
            }

            @Override
            public void requestSuccess(String result) {

                SearchBean searchBean = JSON.parseObject(result, SearchBean.class);

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
