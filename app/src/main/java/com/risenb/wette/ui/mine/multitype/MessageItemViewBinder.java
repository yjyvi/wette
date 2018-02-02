package com.risenb.wette.ui.mine.multitype;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.beans.MessageBean;
import com.risenb.wette.ui.BaseViewHolder;

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
public class MessageItemViewBinder extends ItemViewBinder<MessageBean,MessageItemViewBinder.MessageViewHolder> {

    @NonNull
    @Override
    protected MessageViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MessageViewHolder(inflater.inflate(R.layout.item_message,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageViewHolder holder, @NonNull MessageBean item) {

    }

    public static class MessageViewHolder extends BaseViewHolder {

        public MessageViewHolder(View itemView) {
            super(itemView);
        }
    }

}
