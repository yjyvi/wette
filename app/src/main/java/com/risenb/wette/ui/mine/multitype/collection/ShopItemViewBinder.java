package com.risenb.wette.ui.mine.multitype.collection;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.risenb.wette.R;
import com.risenb.wette.beans.CollectionBean;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.ui.home.ShopDetailUI;
import com.risenb.wette.utils.GlideApp;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : CommodityItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class ShopItemViewBinder extends ItemViewBinder<CollectionBean,BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_collection_shop,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final BaseViewHolder holder, @NonNull final CollectionBean item) {
        holder.<TextView>getView(R.id.tv_name).setText(item.getName());
        GlideApp.with(holder.itemView.getContext())
                .load(item.getImage())
                .into(holder.<ImageView>getView(R.id.iv_logo));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopDetailUI.start(holder.itemView.getContext(),String.valueOf(item.getDataId()));
            }
        });
    }

}
