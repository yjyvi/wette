package com.lengzhuo.xybh.ui.mine.multitype;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.order.OrderListBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.ui.mine.OrderActivity;
import com.lengzhuo.xybh.utils.GlideApp;
import com.lengzhuo.xybh.utils.evntBusBean.BaseEvent;

import org.greenrobot.eventbus.EventBus;

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
public class OrderItemViewBinder extends ItemViewBinder<OrderListBean.DataBean, BaseViewHolder> {

    private static final SparseArray<String[]> ORDER_STATUS_MAP = new SparseArray<>();

    static {
        ORDER_STATUS_MAP.put(1, new String[]{"待支付", "完成支付"});
        ORDER_STATUS_MAP.put(2, new String[]{"已取消", "再次购买"});
        ORDER_STATUS_MAP.put(3, new String[]{"发货中", "完成"});
        ORDER_STATUS_MAP.put(4, new String[]{"已完成", "评价"});
    }

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_order, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull final OrderListBean.DataBean item) {
        //商品adapter配置
        Adapter adapter = new Adapter(R.layout.item_order_goods, item.getGoodList());
        RecyclerView recyclerView = holder.getView(R.id.goods_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        GlideApp.with(holder.itemView.getContext())
                .load(item.getLogo())
                .into(holder.<ImageView>getView(R.id.iv_shop_img));
        ((TextView) holder.getView(R.id.tv_shop_name)).setText(item.getShopName());
        ((TextView) holder.getView(R.id.tv_order_des)).setText("共" + item.getGoodsAmount() + "件商品 合计¥" + item.getTotalFee() + "(含运费¥" +
                item.getFreight() + ")");
        ((TextView) holder.getView(R.id.bt_order_state)).setText(ORDER_STATUS_MAP.get(item.getOrderStatus())[1]);
        holder.<TextView>getView(R.id.tv_status).setText(ORDER_STATUS_MAP.get(item.getOrderStatus())[0]);
        holder.getView(R.id.bt_order_state).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseEvent<OrderListBean.DataBean> event = new BaseEvent<>();
                switch (item.getOrderStatus()) {
                    case OrderActivity.ORDER_STATE_WAITING_PAY://待支付
                        event.setEventType(1);
                        break;
                    case OrderActivity.ORDER_STATE_CANCELED_ORDER://已取消
                        event.setEventType(2);
                        break;
                    case OrderActivity.ORDER_STATE_SENDING_GOODS://发货中
                        event.setEventType(3);
                        break;
                    case OrderActivity.ORDER_STATE_WAITING_EVA://待评价
                        event.setEventType(4);
                        break;
                }
                event.setData(item);
                EventBus.getDefault().post(event);
            }
        });
    }

    /**
     * 商品item
     */
    class Adapter extends BaseQuickAdapter<OrderListBean.DataBean.GoodListBean, BaseViewHolder> {

        public Adapter(int layoutResId, @Nullable List<OrderListBean.DataBean.GoodListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final OrderListBean.DataBean.GoodListBean item) {
            helper.setText(R.id.tv_goods_name, item.getGoodsName());
            helper.setText(R.id.tv_price, "￥" + item.getPrice());
            helper.setText(R.id.tv_count, "x" + item.getAmount());
            GlideApp.with(helper.itemView.getContext())
                    .load(item.getCover())
                    .into(helper.<ImageView>getView(R.id.iv_logo));
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //暂时先不跳转
//                    GoodDetailsUI.start(v.getContext(), item.getGoodsId(), item.getShopId());
                }
            });
        }
    }


}
