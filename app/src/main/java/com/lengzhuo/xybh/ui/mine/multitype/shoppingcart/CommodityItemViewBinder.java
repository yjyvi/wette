package com.lengzhuo.xybh.ui.mine.multitype.shoppingcart;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.shoppingcart.CommodityBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.ui.home.GoodDetailsUI;
import com.lengzhuo.xybh.utils.GlideApp;
import com.lengzhuo.xybh.utils.evntBusBean.BaseEvent;

import org.greenrobot.eventbus.EventBus;

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
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull final CommodityBean item) {
        holder.<ImageView>getView(R.id.iv_is_selected).setImageResource(item.isSelected()?R.drawable.shopping_cart_selected :R.drawable.shopping_cart_unselected);
        holder.<TextView>getView(R.id.tv_name).setText(item.getGoodsName());
        holder.<TextView>getView(R.id.tv_price).setText("¥"+item.getPrice());
        holder.<TextView>getView(R.id.tv_count).setText("x"+item.getAmount());
        GlideApp.with(holder.itemView.getContext())
                .load(item.getCover())
                .into(holder.<ImageView>getView(R.id.iv_cover));

        holder.getView(R.id.fl_is_selected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new BaseEvent<CommodityBean>().setEventType(1).setData(item));
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodDetailsUI.start(v.getContext(),String.valueOf(item.getGoodsId()),item.getShopId());
            }
        });

    }


}
