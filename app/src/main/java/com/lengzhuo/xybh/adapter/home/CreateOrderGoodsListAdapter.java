package com.lengzhuo.xybh.adapter.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.CreateOrderGoodsBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.utils.GlideImgUtils;
import com.lengzhuo.xybh.utils.PlaceholderUtils;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/27.
 */

public class CreateOrderGoodsListAdapter extends BaseQuickAdapter<CreateOrderGoodsBean, BaseViewHolder> {

    public CreateOrderGoodsListAdapter(int layoutResId, @Nullable List<CreateOrderGoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CreateOrderGoodsBean item) {

        final TextView tv_goods_num = helper.getView(R.id.tv_goods_num);
        helper.setText(R.id.tv_goods_name, item.getGoodsTitle());
        helper.setText(R.id.tv_price, PlaceholderUtils.pricePlaceholder(item.getGoodsPrice()));
        helper.setText(R.id.tv_goods_properties_Name, item.getGoodsSkuContent());
        GlideImgUtils.loadImg(helper.itemView.getContext(), item.getGoodsImageUrl(), (ImageView) helper.getView(R.id.iv_cover));
        tv_goods_num.setText(String.format("x%1$s", item.getGoodsAmount()));
    }

}
