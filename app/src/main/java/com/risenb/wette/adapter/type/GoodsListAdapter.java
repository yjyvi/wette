package com.risenb.wette.adapter.type;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.ui.BaseViewHolder;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodsListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public GoodsListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_good_name, item);
    }
}
