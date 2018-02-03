package com.risenb.wette.adapter.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.beans.SearchBean;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.utils.GlideImgUtils;

import java.util.List;

/**
 *
 * @author yjyvi
 * @date 2018/1/31
 */

public class SearchGoodsListAdapter extends BaseQuickAdapter<SearchBean.GoodsListBean,BaseViewHolder> {

    public SearchGoodsListAdapter(int layoutResId, @Nullable List<SearchBean.GoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean.GoodsListBean item) {
        helper.setText(R.id.tv_good_name,item.getGoodsName());
        helper.setText(R.id.tv_good_price, String.valueOf(item.getPrice()));
        GlideImgUtils.loadImg(helper.itemView.getContext(),item.getCover(),(ImageView) helper.getView(R.id.iv_good_img));
    }
}
