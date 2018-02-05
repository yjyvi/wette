package com.risenb.wette.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.beans.GoodCommentBean;
import com.risenb.wette.ui.BaseViewHolder;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public  class GoodCommentAdapter extends BaseQuickAdapter<GoodCommentBean.DataBean,BaseViewHolder> {
    public GoodCommentAdapter(int layoutResId, @Nullable List<GoodCommentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodCommentBean.DataBean item) {

    }
}
