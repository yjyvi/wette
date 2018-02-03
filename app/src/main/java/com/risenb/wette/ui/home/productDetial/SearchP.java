package com.risenb.wette.ui.home.productDetial;

import android.support.v4.app.FragmentActivity;

import com.risenb.wette.beans.SearchBean;
import com.risenb.wette.network.DataCallBack;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

/**
 *
 * @author yjyvi
 * @date 2018/2/3
 * 搜索商品
 */

public class SearchP extends PresenterBase {
    private SearchGoodsListener mSearchGoodsListener;

    public SearchP(FragmentActivity fragmentActivity, SearchGoodsListener searchGoodsListener) {
        setActivity(fragmentActivity);
        this.mSearchGoodsListener = searchGoodsListener;
    }


    public void setSearchData(String keyword) {
        NetworkUtils.getNetworkUtils().getSearch(keyword, new DataCallBack<SearchBean>() {
            @Override
            public void onSuccess(SearchBean result) {
                mSearchGoodsListener.searchData(result);
            }

            @Override
            public void onStatusError(String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    public interface SearchGoodsListener {
        void searchData(SearchBean searchBean);
    }
}
