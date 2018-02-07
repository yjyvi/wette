package com.risenb.wette.adapter.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.beans.GoodCommentListBean;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.utils.GlideImgUtils;
import com.risenb.wette.utils.TimeUtils;

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
