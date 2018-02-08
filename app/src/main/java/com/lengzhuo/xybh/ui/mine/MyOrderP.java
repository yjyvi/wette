package com.lengzhuo.xybh.ui.mine;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.order.OrderListBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.PresenterBase;
import com.lengzhuo.xybh.utils.NetworkUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by yong hao zeng on 2018/2/4.
 */

public class MyOrderP extends PresenterBase {
    private Listener listener;
    private int total;

    public MyOrderP(Listener listener) {
        this.listener = listener;
    }

    public int getTotal() {
        return total;
    }

    /**
     *
     * @param state 订单状态
     * @param page
     * @param limit
     */
    public void loadList(int state, int page, final int limit){
        NetworkUtils.getNetworkUtils().getOrderList(state,page,limit, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                listener.loadListError(e.getMessage());
            }

            @Override
            public void requestSuccess(String result) {
               OrderListBean orderListBean =  JSON.parseObject(result,OrderListBean.class);
               if (!TextUtils.equals(orderListBean.getStatus(),"1"))
                    listener.loadListError(orderListBean.getErrorMsg());
               else {
                    total = orderListBean.getTotal();
                   listener.loadListSuccess(orderListBean.getData());

               }
            }
        });
    }


    public interface Listener{
        void loadListSuccess(List<OrderListBean.DataBean> orderList);
        void loadListError(String msg);


    }
}
