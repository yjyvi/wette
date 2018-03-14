package com.lengzhuo.xybh.ui.mytest;

import com.lengzhuo.xybh.beans.SearchBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yjyvi
 * @data 2018/3/9.
 */

public class SearchPresenter {

    private SearchView mSearchView;
    private SearchModel mSearchModel;

    public SearchPresenter(SearchView searchView) {
        this.mSearchView = searchView;
        this.mSearchModel = new SearchModel();
    }

    public void getSearchResult(String searchContent) {
        mSearchView.showProgressDialog();
        mSearchModel.getSearchResult(searchContent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {

                    @Override
                    public void onComplete() {
                        mSearchView.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSearchView.hideProgressDialog();
                        mSearchView.showError(e.getMessage());
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        mSearchView.showResult(searchBean);
                    }
                });
    }
}
