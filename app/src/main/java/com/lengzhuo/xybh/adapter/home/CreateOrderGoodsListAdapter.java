package com.lengzhuo.xybh.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.beans.CreateOrderGoodsBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/27.
 */

public class CreateOrderGoodsListAdapter extends BaseQuickAdapter<CreateOrderGoodsBean,BaseViewHolder> {
    public CreateOrderGoodsListAdapter(int layoutResId, @Nullable List<CreateOrderGoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreateOrderGoodsBean item) {

    }
}
