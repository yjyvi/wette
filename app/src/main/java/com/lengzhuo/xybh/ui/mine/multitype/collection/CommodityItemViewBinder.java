package com.lengzhuo.xybh.ui.mine.multitype.collection;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.CollectionBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.ui.home.GoodDetailsUI;
import com.lengzhuo.xybh.utils.GlideApp;
import com.lengzhuo.xybh.utils.PlaceholderUtils;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/07
 *     desc   : 收藏 商品 ItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class CommodityItemViewBinder extends ItemViewBinder<CollectionBean,BaseViewHolder> {


    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_good_list,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final BaseViewHolder holder, @NonNull final CollectionBean item) {
        holder.<TextView>getView(R.id.tv_good_name).setText(item.getName());
        holder.<TextView>getView(R.id.tv_good_price).setText(PlaceholderUtils.pricePlaceholder(item.getPrice()));
        GlideApp.with(holder.itemView.getContext())
                .load(item.getImage())
                .into(holder.<ImageView>getView(R.id.iv_good_img));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodDetailsUI.start(holder.itemView.getContext(),String.valueOf(item.getDataId()));
            }
        });
    }
}
