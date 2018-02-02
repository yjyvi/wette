package com.risenb.wette.ui.mine.multitype;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.beans.CollectionShopBean;
import com.risenb.wette.ui.BaseViewHolder;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : CollectionCommodityItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class CollectionShopItemViewBinder extends ItemViewBinder<CollectionShopBean,CollectionShopItemViewBinder.CollectionShopViewHolder> {

    @NonNull
    @Override
    protected CollectionShopViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new CollectionShopViewHolder(inflater.inflate(R.layout.item_collection_shop,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull CollectionShopViewHolder holder, @NonNull CollectionShopBean item) {

    }

    public static class CollectionShopViewHolder extends BaseViewHolder{

        public CollectionShopViewHolder(View itemView) {
            super(itemView);
        }
    }

}
