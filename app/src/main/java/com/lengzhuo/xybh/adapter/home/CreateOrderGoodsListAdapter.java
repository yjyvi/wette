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
    private  GoodsNumListener mGoodsNumListener;

    public CreateOrderGoodsListAdapter(int layoutResId, @Nullable List<CreateOrderGoodsBean> data, GoodsNumListener goodsNumListener) {
        super(layoutResId, data);
        this.mGoodsNumListener = goodsNumListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, CreateOrderGoodsBean item) {

        ImageView iv_reduce = helper.getView(R.id.iv_reduce);
        final TextView tv_goods_num = helper.getView(R.id.tv_goods_num);
        ImageView iv_add = helper.getView(R.id.iv_add);

        helper.setText(R.id.tv_goods_name, item.getGoodsTitle());
        helper.setText(R.id.tv_price, PlaceholderUtils.pricePlaceholder(item.getGoodsPrice()));
        helper.setText(R.id.tv_goods_properties_Name, item.getGoodsSkuContent());
        GlideImgUtils.loadImg(helper.itemView.getContext(), item.getGoodsImageUrl(), (ImageView) helper.getView(R.id.iv_cover));
        tv_goods_num.setText(item.getGoodsAmount());


//        iv_reduce.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                numAddOrReduce(tv_goods_num, false, helper.getLayoutPosition());
//            }
//        });
//
//        iv_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                numAddOrReduce(tv_goods_num, true, helper.getLayoutPosition());
//            }
//        });
    }


    /**
     * 加减
     *
     * @param isAdd
     */
    private void numAddOrReduce(TextView goodsNum, boolean isAdd, int position) {
        String num = goodsNum.getText().toString().trim();
        int i = Integer.parseInt(num);
        if (isAdd && i < 99) {
            i++;
        } else if (i > 1) {
            i--;
        }
        goodsNum.setText(String.valueOf(i));
        mGoodsNumListener.addOrReduce(Integer.parseInt(goodsNum.getText().toString().trim()),position);
    }


    public interface GoodsNumListener {
        void addOrReduce(int num, int position);
    }


}
