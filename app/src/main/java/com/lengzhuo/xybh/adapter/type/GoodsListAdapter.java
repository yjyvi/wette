package com.lengzhuo.xybh.adapter.type;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.ClassifyBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.utils.GlideImgUtils;

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
