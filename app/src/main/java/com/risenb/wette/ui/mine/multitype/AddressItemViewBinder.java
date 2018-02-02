package com.risenb.wette.ui.mine.multitype;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.beans.AddressBean;
import com.risenb.wette.ui.BaseViewHolder;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : AddressItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class AddressItemViewBinder extends ItemViewBinder<AddressBean,AddressItemViewBinder.AddressViewHolder> {

    @NonNull
    @Override
    protected AddressViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new AddressViewHolder(inflater.inflate(R.layout.item_address_list,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull AddressViewHolder holder, @NonNull AddressBean item) {

    }

    public static class AddressViewHolder extends BaseViewHolder {

        public AddressViewHolder(View itemView) {
            super(itemView);
        }
    }

}
