package com.lengzhuo.xybh.adapter.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.SearchBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.utils.GlideImgUtils;

import java.util.List;

/**
 *
 * @author yjyvi
 * @date 2018/1/31
 */

public class SearchGoodsListAdapter extends BaseQuickAdapter<SearchBean.DataBean.GoodsListBean,BaseViewHolder> {

    public SearchGoodsListAdapter(int layoutResId, @Nullable List<SearchBean.DataBean.GoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean.DataBean.GoodsListBean item) {
        helper.setText(R.id.tv_good_name,item.getGoodsName());
        helper.setText(R.id.tv_good_price, String.valueOf(item.getPrice()));
        GlideImgUtils.loadImg(helper.itemView.getContext(),item.getCover(),(ImageView) helper.getView(R.id.iv_good_img));
    }
}
