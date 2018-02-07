package com.risenb.wette.ui.home;

import android.text.TextUtils;

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
 * 收藏
 */

public class CollectionP extends PresenterBase {

    private  CollectionListener mCollectionListener;

    public CollectionP( CollectionListener collectionListener){
        this.mCollectionListener = collectionListener;
    }

    public void setCollection(final String operation, String dataId, String type){
        NetworkUtils.getNetworkUtils().getCollection( operation, dataId, type, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                mCollectionListener.collectionField();
            }

            @Override
            public void requestSuccess(String result) {
                NetBaseBean netBaseBean = JSON.parseObject(result,NetBaseBean.class);
                if (REQUEST_SUCCESS.equals(netBaseBean.getStatus())) {
                    mCollectionListener.collectionSuccess();
                    if (TextUtils.equals("1",operation)) {
                        ToastUtils.showToast("收藏成功");
                    }else {
                        ToastUtils.showToast("取消收藏");
                    }
                }else {
                    ToastUtils.showToast(netBaseBean.getErrorMsg());
                    mCollectionListener.collectionField();
                }
            }
        });
    }


    public interface CollectionListener{
        void collectionSuccess();
        void collectionField();
    }

}
