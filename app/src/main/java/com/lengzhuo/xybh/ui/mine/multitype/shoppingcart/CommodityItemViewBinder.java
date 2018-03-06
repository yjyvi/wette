package com.lengzhuo.xybh.ui.mine.multitype.shoppingcart;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.shoppingcart.CommodityBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.ui.home.GoodDetailsUI;
import com.lengzhuo.xybh.utils.GlideApp;
import com.lengzhuo.xybh.utils.PlaceholderUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.evntBusBean.BaseEvent;

import org.greenrobot.eventbus.EventBus;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/03
 *     desc   : 购物车 商品 ItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class CommodityItemViewBinder extends ItemViewBinder<CommodityBean, BaseViewHolder> {

    private CommodityItemViewBinder.GoodsNumListener mGoodsNumListener;

    public CommodityItemViewBinder(GoodsNumListener goodsNumListener) {
        mGoodsNumListener = goodsNumListener;
    }

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_shopping_cart_commodity, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final BaseViewHolder holder, @NonNull final CommodityBean item) {
        holder.<ImageView>getView(R.id.iv_is_selected).setImageResource(item.isSelected() ? R.drawable.shopping_cart_selected : R.drawable.shopping_cart_unselected);
        holder.<TextView>getView(R.id.tv_name).setText(item.getGoodsName());
        holder.<TextView>getView(R.id.tv_price).setText(PlaceholderUtils.pricePlaceholder(item.getPrice()));
        final TextView tvGoodsNum = holder.getView(R.id.tv_goods_num);
        tvGoodsNum.setText(String.valueOf(item.getAmount()));
        holder.<TextView>getView(R.id.tv_format).setText(item.getPropertiesName());
        GlideApp.with(holder.itemView.getContext())
                .load(item.getCover())
                .into(holder.<ImageView>getView(R.id.iv_cover));

        holder.getView(R.id.fl_is_selected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new BaseEvent<CommodityBean>().setEventType(1).setData(item));
            }
        });


        holder.<ImageView>getView(R.id.iv_cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodDetailsUI.start(v.getContext(), String.valueOf(item.getGoodsId()), String.valueOf(item.getShopId()));
            }
        });

        if (item.isSelected()) {
            holder.<ImageView>getView(R.id.iv_reduce).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numAddOrReduce(tvGoodsNum, false, holder.getLayoutPosition(), item.getSkuStock());
                }
            });

            holder.<ImageView>getView(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numAddOrReduce(tvGoodsNum, true, holder.getLayoutPosition(), item.getSkuStock());
                }
            });
        }
    }


    /**
     * 加减
     *
     * @param isAdd
     */
    private void numAddOrReduce(TextView goodsNum, boolean isAdd, int position, int goodsMax) {
        String num = goodsNum.getText().toString().trim();
        int i = Integer.parseInt(num);
        
        if (isAdd && i < goodsMax) {
            i++;
        }

        if (i > goodsMax) {
            ToastUtils.showToast("超出库存数量!");
        }

        if (!isAdd && i > 1) {
            i--;
        }

        goodsNum.setText(String.valueOf(i));
        mGoodsNumListener.addOrReduce(Integer.parseInt(goodsNum.getText().toString().trim()), position);
    }


    public interface GoodsNumListener {
        void addOrReduce(int num, int position);
    }


}
