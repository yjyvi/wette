package com.risenb.wette.ui.home;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.NetBaseBean;
import com.risenb.wette.network.OKHttpManager;
import com.risenb.wette.ui.PresenterBase;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/2/5.
 */

public class CollectionP extends PresenterBase {

    private  CollectionListener mCollectionListener;

    public CollectionP(FragmentActivity fragmentActivity, CollectionListener collectionListener){
        this.mCollectionListener = collectionListener;
        setActivity(fragmentActivity);
    }

    public void setCollection( String operation,String dataId,String type){
        NetworkUtils.getNetworkUtils().getCollection( operation, dataId, type, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                ToastUtils.showToast(e.getMessage());
            }

            @Override
            public void requestSuccess(String result) {
                NetBaseBean netBaseBean = JSON.parseObject(result,NetBaseBean.class);
                if (REQUEST_SUCCESS.equals(netBaseBean.getStatus())) {
                    mCollectionListener.collectionResult();
                }else {
                    ToastUtils.showToast(netBaseBean.getMsg());
                    mCollectionListener.collectionField();
                }
            }
        });
    }


    public interface CollectionListener{
        void collectionResult();
        void collectionField();
    }

}
