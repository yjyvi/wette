package com.risenb.wette.ui.mine.multitype;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.beans.OrderBean;
import com.risenb.wette.ui.BaseViewHolder;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : OrderItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class OrderItemViewBinder extends ItemViewBinder<OrderBean,OrderItemViewBinder.OrderViewHolder> {

    @NonNull
    @Override
    protected OrderViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new OrderViewHolder(inflater.inflate(R.layout.item_order,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderViewHolder holder, @NonNull OrderBean item) {

    }

    public static class OrderViewHolder extends BaseViewHolder {

        public OrderViewHolder(View itemView) {
            super(itemView);
        }
    }

}
