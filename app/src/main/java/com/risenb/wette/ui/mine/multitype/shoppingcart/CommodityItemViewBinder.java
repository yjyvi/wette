package com.risenb.wette.ui.mine.multitype.shoppingcart;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.beans.shoppingcart.CommodityBean;
import com.risenb.wette.ui.BaseViewHolder;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/03
 *     desc   : 购物车 商品 ItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class CommodityItemViewBinder extends ItemViewBinder<CommodityBean,BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_shopping_cart_commodity,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull CommodityBean item) {

    }


}
