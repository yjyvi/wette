package com.risenb.wette.adapter.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.ui.BaseViewHolder;

import java.util.List;

/**
 *
 * @author yjyvi
 * @date 2018/1/31
 */

public class ProductListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ProductListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
