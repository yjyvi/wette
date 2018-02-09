package com.risenb.wette.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.OrderGoodsBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.ui.home.CommentOrderUI;
import com.lengzhuo.xybh.ui.home.GoodDetailsUI;
import com.lengzhuo.xybh.utils.GlideImgUtils;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/8.
 */

public class OrderGoodsCommentListAdapter extends BaseQuickAdapter<OrderGoodsBean.DataBean, BaseViewHolder> {

    public OrderGoodsCommentListAdapter(int layoutResId, @Nullable List<OrderGoodsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final OrderGoodsBean.DataBean item) {
        helper.setText(R.id.tv_goods_name, item.getGoodsName());
        GlideImgUtils.loadImg(helper.itemView.getContext(), item.getCover(), (ImageView) helper.getView(R.id.iv_good_img));
        TextView tv_state = helper.getView(R.id.tv_state);
        if (1 == item.getIsEvaluate()) {
            tv_state.setText("评价");
        } else {
            tv_state.setText("查看");
        }
        tv_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (1 == item.getIsEvaluate()) {
                    CommentOrderUI.start(view.getContext());
                }else {
                    GoodDetailsUI.start(view.getContext(),item.getGoodsId(),item.getShopId(),3);
                }
            }
        });
    }
}
