package com.risenb.wette.adapter.type;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.ui.home.GoodListUI;
import com.risenb.wette.utils.ToastUtils;

import java.util.List;

/**
 * @author yjyvi
 * @date 2018/1/31
 */

public class ClassifyRightAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private List<String> data;

    public ClassifyRightAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.data = data;
    }


    @Override
    protected void convert(com.risenb.wette.ui.BaseViewHolder helper, String item) {
        RecyclerView rv_goods = helper.getView(R.id.rv_goods);
        GridLayoutManager layout = new GridLayoutManager(helper.itemView.getContext(), 3);
        layout.setAutoMeasureEnabled(true);
        rv_goods.setLayoutManager(layout);

        //头部分类名称
        TextView tv_title = new TextView(helper.itemView.getContext());
        LinearLayout.LayoutParams titleLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_title.setLayoutParams(titleLayoutParams);
        tv_title.setText("组一" + helper.getOldPosition());
        tv_title.setPadding(0, 34, 0, 34);


        //底部分割线
        TextView lineView = new TextView(helper.itemView.getContext());
        LinearLayout.LayoutParams lineLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lineView.setLayoutParams(lineLayoutParams);
        lineView.setBackgroundResource(R.mipmap.gray_line);

        GoodsListAdapter goodsListAdapter = new GoodsListAdapter(R.layout.item_goods_list, data);
        goodsListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(String.valueOf(position));
                GoodListUI.start(view.getContext());
            }
        });

        goodsListAdapter.addHeaderView(tv_title);
        goodsListAdapter.addFooterView(lineView);
        rv_goods.setAdapter(goodsListAdapter);

    }
}
