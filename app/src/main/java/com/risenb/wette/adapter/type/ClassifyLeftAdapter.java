package com.risenb.wette.adapter.type;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;

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
    protected void convert(final BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);
        LinearLayout ll_type = helper.getView(R.id.ll_type);
    }
}
