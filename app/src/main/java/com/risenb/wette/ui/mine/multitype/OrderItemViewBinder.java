package com.risenb.wette.ui.mine.multitype;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
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
public class OrderItemViewBinder extends ItemViewBinder<OrderBean,BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_order,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull OrderBean item) {

    }

}
