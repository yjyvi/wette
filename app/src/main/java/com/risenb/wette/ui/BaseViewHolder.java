package com.risenb.wette.ui;

import android.graphics.Color;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;


/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/04/27
 *     desc   : BaseViewHolder RecyclerView 的 ViewHolder 需要继承此类
 *     version: 1.0
 * </pre>
 */
public class BaseViewHolder extends com.chad.library.adapter.base.BaseViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        AutoUtils.auto(itemView);
        if (itemView.getBackground() == null) {
            itemView.setBackgroundColor(Color.WHITE);
        }
    }
}
