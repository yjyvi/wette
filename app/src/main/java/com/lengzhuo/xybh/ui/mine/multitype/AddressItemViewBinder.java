package com.lengzhuo.xybh.ui.mine.multitype;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.AddressBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.ui.mine.EditAddressActivity;
import com.lengzhuo.xybh.utils.evntBusBean.BaseEvent;

import org.greenrobot.eventbus.EventBus;

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
        return new BaseViewHolder(inflater.inflate(R.layout.item_address_list, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final BaseViewHolder holder, @NonNull final AddressBean item) {
        holder.<TextView>getView(R.id.tv_addressee).setText("收货人：" + item.getAddressee());
        holder.<TextView>getView(R.id.tv_phone_number).setText("联系方式：" + item.getTelephone());
        holder.<TextView>getView(R.id.tv_address).setText("收货地址：" + item.getAddress());
        holder.<TextView>getView(R.id.tv_postal_code).setText("邮政编码：" + item.getPostalcode());
        holder.<ImageView>getView(R.id.iv_is_default).setImageResource(item.getIsDefault() == 0 ? R.drawable.address_list_unselected : R.drawable.address_list_selected);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAddressActivity.toUpdateAddressActivity((Activity) holder.itemView.getContext(), item);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(v.getContext())
                        .setTitle("提示")
                        .setMessage("是否要删除？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EventBus.getDefault().post(new BaseEvent<AddressBean>().setEventType(2).setData(item));
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

                return true;
            }
        });
        holder.getView(R.id.iv_is_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getIsDefault() == 0)
                    EventBus.getDefault().post(new BaseEvent<AddressBean>().setEventType(1).setData(item));
            }
        });
    }

}
