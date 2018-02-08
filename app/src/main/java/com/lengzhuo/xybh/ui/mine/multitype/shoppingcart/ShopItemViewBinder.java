package com.lengzhuo.xybh.ui.mine.multitype.shoppingcart;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.shoppingcart.ShopBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.utils.GlideApp;
import com.lengzhuo.xybh.utils.evntBusBean.BaseEvent;

import org.greenrobot.eventbus.EventBus;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/03
 *     desc   : 购物车 店铺 ItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class ShopItemViewBinder extends ItemViewBinder<ShopBean,BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_shopping_cart_shop,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull final ShopBean item) {
        holder.<ImageView>getView(R.id.iv_is_selected).setImageResource(item.isSelected()?R.drawable.shopping_cart_selected :R.drawable.shopping_cart_unselected);
        holder.<TextView>getView(R.id.tv_name).setText(item.getShopName());
        GlideApp.with(holder.itemView.getContext())
                .load(item.getLogo())
                .loadAvatar()
                .into(holder.<ImageView>getView(R.id.iv_logo));
        holder.getView(R.id.fl_is_selected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new BaseEvent<ShopBean>().setEventType(2).setData(item));
            }
        });
    }
}
