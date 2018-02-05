package com.risenb.wette.ui.home;

import android.support.v4.app.FragmentActivity;

import com.risenb.wette.ui.PresenterBase;

/**
 * @author yjyvi
 * @data 2018/2/5.
 */

public class ShopDetailP extends PresenterBase {
    private  ShopDataListener mShopDataListener;

    public ShopDetailP(FragmentActivity fragmentActivity, ShopDataListener shopDataListener){
        setActivity(fragmentActivity);
        this.mShopDataListener = shopDataListener;
    }

    public void setShopData(String shopId,String properties){

    }


    public interface ShopDataListener{
        void shopData();
    }
}
