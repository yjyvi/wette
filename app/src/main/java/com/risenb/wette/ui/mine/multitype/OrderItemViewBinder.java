package com.risenb.wette.ui.mine.multitype;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.beans.order.OrderListBean;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.ui.mine.OrderActivity;
import com.risenb.wette.utils.GlideImgUtils;

import java.util.List;

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
public class OrderItemViewBinder extends ItemViewBinder<OrderListBean.DataBean,BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_order,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull OrderListBean.DataBean item) {
        //商品adapter配置
        Adapter adapter = new Adapter(R.layout.item_order_goods, item.getGoodList());
        RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.goods_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);

        ((TextView) holder.getView(R.id.tv_shop_name)).setText(item.getShopName());
        ((TextView) holder.getView(R.id.tv_order_des)).setText("共"+item.getGoodsAmount()+"件商品 合计¥"+item.getTotalFee()+"(含运费¥"+
        item.getFreight()+")");
        String statesString = "";
        switch (item.getOrderStatus()){
            case OrderActivity.ORDER_STATE_WAITING_EVA://待评价
                statesString = "评价";
                break;
            case OrderActivity.ORDER_STATE_WAITING_PAY://待支付
                statesString = "支付";
                break;
            case OrderActivity.ORDER_STATE_CANCELED_ORDER://已取消
                statesString = "再次购买";
                break;
            case OrderActivity.ORDER_STATE_SENDING_GOODS://发货中
                statesString = "再次购买";
                break;

        }
        ((TextView) holder.getView(R.id.bt_order_state)).setText(statesString);

    }

    /**
     * 商品item
     */
    class Adapter extends BaseQuickAdapter<OrderListBean.DataBean.GoodListBean,BaseViewHolder>{

        public Adapter(int layoutResId, @Nullable List<OrderListBean.DataBean.GoodListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrderListBean.DataBean.GoodListBean item) {
            helper.setText(R.id.tv_goods_name,item.getGoodsName());
            GlideImgUtils.loadImg(mContext,item.getCover(), (ImageView) helper.getView(R.id.iv_goods_img));
            helper.setText(R.id.tv_price,"￥"+item.getPrice());
        }
    }





}
