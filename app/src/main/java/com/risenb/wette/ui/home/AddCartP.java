package com.risenb.wette.ui.home;

import android.support.v4.app.FragmentActivity;

import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/6.
 * 添加到购物车
 */

public class AddCartP extends PresenterBase {

    private  AddCartListener mAddCartListener;

    public AddCartP(FragmentActivity fragmentActivity, AddCartListener addCartListener){
        setActivity(fragmentActivity);
        this.mAddCartListener = addCartListener;
    }

    public void setAddCart(String shopId, String goodsId, String skuId, String addressId, String amount){

        NetworkUtils.getNetworkUtils().addCart(shopId, goodsId, skuId, addressId, amount, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mAddCartListener.addCartField();
            }

            @Override
            public void requestSuccess(String result) {
                mAddCartListener.addCartSuccess();
            }
        });
    }

    public interface AddCartListener{
        void addCartSuccess();
        void addCartField();
    }
}
