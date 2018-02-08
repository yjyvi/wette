package com.lengzhuo.xybh.adapter.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.GoodCommentListBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.utils.GlideImgUtils;
import com.lengzhuo.xybh.utils.TimeUtils;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public  class GoodCommentAdapter extends BaseQuickAdapter<GoodCommentListBean.DataBean,BaseViewHolder> {
    public GoodCommentAdapter(int layoutResId, @Nullable List<GoodCommentListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodCommentListBean.DataBean item) {
        helper.setText(R.id.tv_user_name,item.getNickname());
        helper.setText(R.id.tv_time, TimeUtils.unixTimeStamp2FormatString(item.getCreateTime()));
        helper.setText(R.id.tv_content,item.getContent());

        GlideImgUtils.loadImg(helper.itemView.getContext(),item.getHeadImg(),(ImageView)helper.getView(R.id.iv_user_icon));
    }
}
