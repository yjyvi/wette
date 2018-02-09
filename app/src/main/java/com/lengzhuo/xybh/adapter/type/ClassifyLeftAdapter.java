package com.lengzhuo.xybh.adapter.type;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.ClassifyBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class ClassifyLeftAdapter extends BaseQuickAdapter<ClassifyBean.DataBean, BaseViewHolder> {


    public ClassifyLeftAdapter(int layoutResId, @Nullable List<ClassifyBean.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(final BaseViewHolder helper, ClassifyBean.DataBean item) {
        helper.setText(R.id.tv_name, item.getName());
        if (helper.getLayoutPosition() == 0) {
            helper.getView(R.id.v_line).setSelected(true);
        }
    }
}
