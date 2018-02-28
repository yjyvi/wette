package com.lengzhuo.xybh.ui.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.beans.NetBaseBean;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.PresenterBase;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;

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

    public void setCollection(final int operation, String dataId, String type){
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
                    if (TextUtils.equals("1",String.valueOf(operation))) {
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
