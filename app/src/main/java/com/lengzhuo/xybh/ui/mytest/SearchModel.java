package com.lengzhuo.xybh.ui.mytest;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.SearchBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.utils.NetworkUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;


/**
 * @author yjyvi
 * @data 2018/3/9.
 */

public class SearchModel {

    public Observable<SearchBean> getSearchResult(final String searchContent) {

        return Observable.create(new ObservableOnSubscribe<SearchBean>() {
            @Override
            public void subscribe(final ObservableEmitter<SearchBean> subscriber) throws Exception {
                NetworkUtils.getNetworkUtils().getSearch(searchContent, "1", "10", new OKHttpManager.StringCallBack() {
                    @Override
                    public void requestFailure(Call call, IOException e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void requestSuccess(String result) {
                        SearchBean searchBean = JSON.parseObject(result, SearchBean.class);
                        if (TextUtils.equals(searchBean.getStatus(), "1")) {
                            subscriber.onNext(searchBean);
                        } else {
                            subscriber.onError(new Exception(searchBean.getErrorMsg()));
                        }
                        subscriber.onComplete();
                    }
                });
            }
        });
    }
}
