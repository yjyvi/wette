package com.lengzhuo.xybh.ui.mytest;

import com.lengzhuo.xybh.beans.SearchBean;

/**
 * @author yjyvi
 * @data 2018/3/9.
 */

public interface  SearchView {
    void showResult(SearchBean searchBean);
    void showProgressDialog();
    void hideProgressDialog();
    void showError(String errMsg);
}
