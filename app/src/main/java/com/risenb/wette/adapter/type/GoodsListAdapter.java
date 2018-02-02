package com.risenb.wette.adapter.type;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.ui.classify.TwoClassifyFragment;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodsListAdapter extends BaseQuickAdapter<TwoClassifyFragment.Category, BaseViewHolder> {


    public GoodsListAdapter(int layoutResId, @Nullable List<TwoClassifyFragment.Category> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, TwoClassifyFragment.Category item) {
        helper.setText(R.id.tv_good_name, item.getName());
    }
}
