package com.risenb.wette.network;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.beans.BaseBean;
import com.risenb.wette.utils.ToastUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class CommonCallBack<T> implements OKHttpManager.StringCallBack {

    @Override
    public void requestFailure(Call call, IOException e) {

    }

    @Override
    public void requestSuccess(String result) {
        BaseBean baseBean = JSON.parseObject(result, BaseBean.class);
        if (baseBean.getStatus().equals("0")) {
            onError(baseBean.getErrorMsg());
        } else {
            Type genType = this.getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            T data = JSON.parseObject(baseBean.getData(), params[0]);
            onSuccess(data);
        }
    }

    protected abstract void onSuccess(T data);

    protected void onError(String msg) {
        ToastUtils.showToast(msg);
    }

}
