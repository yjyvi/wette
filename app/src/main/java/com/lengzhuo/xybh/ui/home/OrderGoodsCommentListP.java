package com.lengzhuo.xybh.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.OrderGoodsBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.PresenterBase;
import com.lengzhuo.xybh.utils.NetworkUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/9.
 */

public class OrderGoodsCommentListP extends PresenterBase {

    private  OrderGoodsListener mOrderGoodsListener;

    public OrderGoodsCommentListP(OrderGoodsListener orderGoodsListener){
        this.mOrderGoodsListener = orderGoodsListener;
    }


    public void setOrderGoodsCommentList(final String orderId){

        NetworkUtils.getNetworkUtils().orderGoods(orderId, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mOrderGoodsListener.orderGoodsListField();
            }

            @Override
            public void requestSuccess(String result) {
                OrderGoodsBean  orderGoodsBean = JSON.parseObject(result,OrderGoodsBean.class);
                if (TextUtils.equals(REQUEST_SUCCESS,orderGoodsBean.getStatus())) {
                    mOrderGoodsListener.orderGoodsListSuccess(orderGoodsBean.getData());
                }

            }
        });
    }

    public interface OrderGoodsListener{
        void orderGoodsListSuccess(List<OrderGoodsBean.DataBean> dataBeans);
        void orderGoodsListField();
    }

}
