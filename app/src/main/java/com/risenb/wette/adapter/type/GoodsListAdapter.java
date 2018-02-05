package com.risenb.wette.adapter.type;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.beans.ClassifyBean;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.utils.GlideImgUtils;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodsListAdapter extends BaseQuickAdapter<ClassifyBean.DataBean.ListBeanX.ListBean, BaseViewHolder> {


    public GoodsListAdapter(int layoutResId, @Nullable List<ClassifyBean.DataBean.ListBeanX.ListBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, ClassifyBean.DataBean.ListBeanX.ListBean item) {
        helper.setText(R.id.tv_good_name, item.getName());
        GlideImgUtils.loadImg(helper.itemView.getContext(),item.getImage(),(ImageView) helper.getView(R.id.iv_good_img));
    }
}
