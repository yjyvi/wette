package com.risenb.wette.ui.mine.multitype;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.risenb.wette.R;
import com.risenb.wette.beans.MessageBean;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.ui.mine.MessageDetailActivity;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : MessageItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class MessageItemViewBinder extends ItemViewBinder<MessageBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_message, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull final MessageBean item) {
        holder.<TextView>getView(R.id.tv_title).setText(item.getTitle());
        holder.<TextView>getView(R.id.tv_create_time).setText(item.getCreateTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDetailActivity.toActivity(v.getContext(), item);
            }
        });
    }


}
