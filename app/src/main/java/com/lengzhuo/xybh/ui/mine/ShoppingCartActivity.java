package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.CreateOrderGoodsBean;
import com.lengzhuo.xybh.beans.shoppingcart.CommodityBean;
import com.lengzhuo.xybh.beans.shoppingcart.ShopBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.home.CreateOrderUI;
import com.lengzhuo.xybh.ui.mine.multitype.shoppingcart.CommodityItemViewBinder;
import com.lengzhuo.xybh.ui.mine.multitype.shoppingcart.ShopItemViewBinder;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.Utils;
import com.lengzhuo.xybh.utils.evntBusBean.BaseEvent;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayout;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayoutListener;
import com.zhy.autolayout.utils.AutoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class ShoppingCartActivity extends BaseUI implements MyRefreshLayoutListener {

    private Items mItems;

    private MultiTypeAdapter mAdapter;

    @ViewInject(R.id.rv_shopping_cart)
    private RecyclerView rv_shopping_cart;

    @ViewInject(R.id.rl_shopping_cart)
    private MyRefreshLayout rl_shopping_cart;

    @ViewInject(R.id.iv_all_selected)
    private ImageView iv_all_selected;

    @ViewInject(R.id.tv_total_price)
    private TextView tv_total_price;

    @ViewInject(R.id.tv_settlement)
    private TextView tv_settlement;

    @ViewInject(R.id.tv_delete)
    private TextView tv_delete;

    @ViewInject(R.id.fl_empty_data)
    private FrameLayout fl_empty_data;

    //已选中的商品
    List<CommodityBean> mSelectedCommodityList = new ArrayList<>();

    boolean mIsEdit = false;

    boolean mIsSubmitOrder = false;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("购物车");
        rightVisible("管理");
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
        rl_shopping_cart.setPullDownRefreshEnable(false);
        rl_shopping_cart.setMyRefreshLayoutListener(this);
    }

    private int mPageIndex;

    private void getShoppingCartList() {
        NetworkUtils.getNetworkUtils().getShoppingCartList(String.valueOf(mPageIndex), new CommonCallBack<List<ShopBean>>() {
            @Override
            protected void onSuccess(List<ShopBean> data) {
                if (Utils.isShowEmptyLayout(mPageIndex,data, rl_shopping_cart, fl_empty_data)) return;
                if (data.size() < 10) rl_shopping_cart.setIsLoadingMoreEnabled(false);
                rl_shopping_cart.refreshComplete();
                rl_shopping_cart.loadMoreComplete();
                for (ShopBean shopBean : data) {
                    mItems.add(shopBean);
                    for (CommodityBean commodityBean : shopBean.getGoodList()) {
                        mItems.add(commodityBean);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void prepareData() {
        getShoppingCartList();
    }

    @Event(value = {R.id.ll_all_selected, R.id.tv_settlement, R.id.rl_right_title, R.id.tv_delete})
    private void onClick(View view) {
        //全选
        if (view.getId() == R.id.ll_all_selected) {
            view.setTag(view.getTag().equals("selected") ? "unSelected" : "selected");
            boolean isSelected = view.getTag().equals("selected");
            for (Object item : mItems) {
                if (item instanceof CommodityBean) {
                    ((CommodityBean) item).setSelected(isSelected);
                } else if (item instanceof ShopBean) {
                    ((ShopBean) item).setSelected(isSelected);
                    if (isSelected) {
                        for (CommodityBean commodityBean : ((ShopBean) item).getGoodList()) {
                            addSelectedCommodity(commodityBean);
                        }
                    } else {
                        mSelectedCommodityList.clear();
                    }
                }
            }
            mAdapter.notifyDataSetChanged();
            onCommodityOrShopSelected();
        } else if (view.getId() == R.id.rl_right_title) {
            //编辑
            for (Object item : mItems) {
                if (item instanceof ShopBean) {
                    ((ShopBean) item).setSelected(false);
                } else if (item instanceof CommodityBean) {
                    ((CommodityBean) item).setSelected(false);
                }
            }
            mAdapter.notifyDataSetChanged();
            iv_all_selected.setImageResource(R.drawable.shopping_cart_unselected);
            mSelectedCommodityList.clear();
            onCommodityOrShopSelected();
            mIsEdit = !mIsEdit;
            if (mIsEdit)
                rightVisible("完成");
            else
                rightVisible("管理");
            tv_delete.setVisibility(mIsEdit ? View.VISIBLE : View.GONE);
        } else if (view.getId() == R.id.tv_delete) {
            //删除
            if (mSelectedCommodityList.isEmpty()) {
                ToastUtils.showToast("请选择要删除的商品");
                return;
            }
            String[] shoppingCartIdArray = new String[mSelectedCommodityList.size()];
            for (int i = 0; i < mSelectedCommodityList.size(); i++) {
                shoppingCartIdArray[i] = String.valueOf(mSelectedCommodityList.get(i).getCartId());
            }
            NetworkUtils.getNetworkUtils().deleteShoppingCarCommodity(shoppingCartIdArray, new CommonCallBack<String>() {
                @Override
                protected void onSuccess(String data) {
                    ToastUtils.showToast("删除成功");
                    mIsEdit = false;
                    rightVisible("管理");
                    tv_delete.setVisibility(View.GONE);
                    mSelectedCommodityList.clear();
                    onCommodityOrShopSelected();
                    mItems.clear();
                    mPageIndex = 1;
                    getShoppingCartList();
                }
            });

        } else {
            if (mSelectedCommodityList.isEmpty()) {
                ToastUtils.showToast("请选择商品");
                return;
            }
            if (mIsEdit) {
                ToastUtils.showToast("请完成编辑");
                return;
            }
            //结算
            ArrayList<CreateOrderGoodsBean> orderGoodsBeanList = new ArrayList<>(mSelectedCommodityList.size());

            for (CommodityBean commodityBean : mSelectedCommodityList) {
                CreateOrderGoodsBean goodsBean = new CreateOrderGoodsBean();
                goodsBean.setGoodsId(String.valueOf(commodityBean.getGoodsId()));
                goodsBean.setGoodsAmount(String.valueOf(commodityBean.getAmount()));
                goodsBean.setShopId(String.valueOf(commodityBean.getShopId()));
                goodsBean.setSkuId(String.valueOf(commodityBean.getSkuId()));
                goodsBean.setGoodsPrice(String.valueOf(commodityBean.getPrice()));
                goodsBean.setGoodsImageUrl(commodityBean.getCover());
                goodsBean.setGoodsSkuContent(commodityBean.getPropertiesName());
                goodsBean.setGoodsTitle(commodityBean.getGoodsName());
                orderGoodsBeanList.add(goodsBean);
            }

            CreateOrderUI.start(this, null, orderGoodsBeanList);
            mIsSubmitOrder = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSelectedCommodityEvent(BaseEvent<CommodityBean> event) {
        CommodityBean commodityBean = event.getData();
        if (event.getEventType() == 1) {
            commodityBean.setSelected(!commodityBean.isSelected());

            //商品和店铺的联动
            ShopBean shopBean = getShopByCommodity(commodityBean);
            boolean result = true;
            for (CommodityBean bean : shopBean.getGoodList()) {
                if (!bean.isSelected()) {
                    result = !result;
                    break;
                }
            }
            shopBean.setSelected(result);

            if (commodityBean.isSelected()) {
                addSelectedCommodity(commodityBean);
            } else {
                mSelectedCommodityList.remove(commodityBean);
            }

            onCommodityOrShopSelected();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe
    public void onSelectedShopEvent(BaseEvent<ShopBean> event) {
        if (event.getEventType() == 2) {
            boolean isSelected = !event.getData().isSelected();
            for (CommodityBean commodityBean : event.getData().getGoodList()) {
                commodityBean.setSelected(isSelected);
            }
            for (CommodityBean commodityBean : event.getData().getGoodList()) {
                if (isSelected)
                    addSelectedCommodity(commodityBean);
                else {
                    mSelectedCommodityList.remove(commodityBean);
                }

            }
            event.getData().setSelected(isSelected);
            onCommodityOrShopSelected();
            mAdapter.notifyDataSetChanged();
        }
    }

    private ShopBean getShopByCommodity(CommodityBean commodityBean) {
        ShopBean result = null;
        for (Object item : mItems) {
            if (item instanceof ShopBean) {
                for (CommodityBean bean : ((ShopBean) item).getGoodList()) {
                    if (bean.equals(commodityBean)) {
                        result = (ShopBean) item;
                        break;
                    }
                }
            }
            if (result != null) break;
        }
        return result;
    }

    private void addSelectedCommodity(CommodityBean commodityBean) {
        if (!mSelectedCommodityList.contains(commodityBean))
            mSelectedCommodityList.add(commodityBean);
    }

    private boolean isAllSelected() {
        boolean isAllSelected = true;
        for (Object item : mItems) {
            if (item instanceof ShopBean) {
                if (!((ShopBean) item).isSelected()) {
                    isAllSelected = !isAllSelected;
                    break;
                }
            } else if (item instanceof CommodityBean) {
                if (!((CommodityBean) item).isSelected()) {
                    isAllSelected = !isAllSelected;
                    break;
                }
            }
        }
        return isAllSelected;
    }

    private void setAllSelectedImageView(boolean isSelected) {
        if (isSelected) {
            iv_all_selected.setImageResource(R.drawable.shopping_cart_selected);
            iv_all_selected.setTag("selected");
        } else {
            iv_all_selected.setImageResource(R.drawable.shopping_cart_unselected);
            iv_all_selected.setTag("unSelected");
        }
    }

    private void onCommodityOrShopSelected() {
        boolean isAllSelected = isAllSelected();
        setAllSelectedImageView(isAllSelected);
        if (mIsEdit) return;
        //设置结算按钮和设置结算金额
        tv_settlement.setBackgroundColor(mSelectedCommodityList.isEmpty() ? Color.parseColor("#aaaaaa") : Color.parseColor("#ee4716"));
        double totalPrice = 0;
        for (CommodityBean commodityBean : mSelectedCommodityList) {
            double commodityTotalPrice = commodityBean.getPrice() * commodityBean.getAmount();
            BigDecimal b1 = new BigDecimal(Double.toString(totalPrice));
            BigDecimal b2 = new BigDecimal(Double.toString(commodityTotalPrice));
            totalPrice = b1.add(b2).doubleValue();
        }
        tv_total_price.setText(String.valueOf("¥" + totalPrice));
        tv_settlement.setText("结算(" + mSelectedCommodityList.size() + ")");
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, ShoppingCartActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onRefresh(View view) {
        mItems.clear();
        mSelectedCommodityList.clear();
        mPageIndex = 1;
        rl_shopping_cart.setIsLoadingMoreEnabled(true);
        getShoppingCartList();
    }

    @Override
    public void onLoadMore(View view) {
        mPageIndex++;
        getShoppingCartList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsSubmitOrder) {
            mItems.clear();
            mSelectedCommodityList.clear();
            mPageIndex = 1;
            rl_shopping_cart.setIsLoadingMoreEnabled(true);
            mIsSubmitOrder = !mIsSubmitOrder;
        }
    }
}
