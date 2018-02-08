package com.risenb.wette.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.beans.OrderGoodsCommentLisBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/8.
 */

public class OrderGoodsCommentListAdapter extends BaseQuickAdapter<OrderGoodsCommentLisBean,BaseViewHolder> {

    public OrderGoodsCommentListAdapter(int layoutResId, @Nullable List<OrderGoodsCommentLisBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderGoodsCommentLisBean item) {

    }
}
