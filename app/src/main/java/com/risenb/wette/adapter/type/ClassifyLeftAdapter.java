package com.risenb.wette.adapter.type;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.ui.BaseViewHolder;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class ClassifyLeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public ClassifyLeftAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setTag(R.id.tv_name,item);
    }
}
