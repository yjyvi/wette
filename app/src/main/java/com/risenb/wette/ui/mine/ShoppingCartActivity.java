package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.risenb.wette.R;
import com.risenb.wette.beans.shoppingcart.CommodityBean;
import com.risenb.wette.beans.shoppingcart.ShopBean;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.mine.multitype.shoppingcart.CommodityItemViewBinder;
import com.risenb.wette.ui.mine.multitype.shoppingcart.ShopItemViewBinder;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/03
 *     desc   : 购物车
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_shopping_cart)
public class ShoppingCartActivity extends BaseUI {

    private Items mItems;

    private MultiTypeAdapter mAdapter;

    @ViewInject(R.id.rv_shopping_cart)
    private RecyclerView rv_shopping_cart;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mAdapter.register(ShopBean.class, new ShopItemViewBinder());

        mAdapter.register(CommodityBean.class, new CommodityItemViewBinder());
        rv_shopping_cart.addItemDecoration(new RecyclerView.ItemDecoration() {

            Paint mPaint;

            {
                mPaint = new Paint();
                mPaint.setColor(Color.parseColor("#e5e5e5"));
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                if (position == 0) return;
                if (isShow(position)) {
                    outRect.set(0, AutoUtils.getPercentHeightSize(10), 0, 0);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                View childView;
                for (int i = 0; i < parent.getChildCount(); i++) {
                    childView = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView
                            .getLayoutParams();
                    int position = params.getViewLayoutPosition();
                    if (position != 0 && isShow(position)) {
                        int right = childView.getRight();
                        int bottom = childView.getTop() + AutoUtils.getPercentHeightSize(10);
                        c.drawRect(childView.getLeft(), childView.getTop(), right, bottom, mPaint);
                    }
                }
            }

            private boolean isShow(int position) {
                Object item = mItems.get(position);
                Object lastItem = mItems.get(position - 1);
                return position != 1
                        && item.getClass().getName().equals(ShopBean.class.getName())
                        && !item.getClass().getName().equals(lastItem.getClass().getName());
            }

        });
        rv_shopping_cart.setLayoutManager(new LinearLayoutManager(this));
        rv_shopping_cart.setAdapter(mAdapter);
    }

    @Override
    protected void prepareData() {
        for (int i = 0; i < 5; i++) {
            mItems.add(new ShopBean());
            for (int i1 = 0; i1 < 3; i1++) {
                mItems.add(new CommodityBean());
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, ShoppingCartActivity.class);
        context.startActivity(intent);
    }

}
