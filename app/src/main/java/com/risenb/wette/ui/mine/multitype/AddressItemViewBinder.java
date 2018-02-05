package com.risenb.wette.ui.mine.multitype;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class AddressItemViewBinder extends ItemViewBinder<AddressBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_address_list,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull AddressBean item) {
        holder.<TextView>getView(R.id.tv_addressee).setText("收货人："+item.getAddressee());
        holder.<TextView>getView(R.id.tv_phone_number).setText("联系方式："+item.getTelephone());
        holder.<TextView>getView(R.id.tv_address).setText("收货地址："+item.getAddress());
        holder.<TextView>getView(R.id.tv_postal_code).setText("邮政编码："+item.getPostalcode());
    }

}
