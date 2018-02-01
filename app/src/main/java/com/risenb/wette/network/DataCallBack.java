package com.risenb.wette.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.risenb.wette.beans.NetBaseBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 通用网络访问callback 去除base头 返回data信息 如果base中含有error则直接回调为onError
 * Created by yonghao zeng on 2017/5/11.
 */

public abstract class DataCallBack<T>   {
    private Class<T> entityClass;


    public void requestSuccess(String response) {
        //反射获取泛型类名
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        this.entityClass = (Class) params[0];

        NetBaseBean<T> bean = JSON.parseObject(response, new TypeReference<NetBaseBean<T>>(entityClass) {
        });
        onResponse(bean);
    }

    /**
     * 返回结果
     * @param t
     */
    public void onResponse(NetBaseBean<T> t) {
        if (t == null) {
            onJsonParesError();
        }

        T data = t.getData();
        //状态错误
        if (!t.isStatus()) {
            onStatusError(t.getErrorMsg());
            return;
        }
        //获得data数据
        onSuccess(data);

    }


    /**
     * data是单个对象回调
     * @param result
     */
    public abstract void onSuccess(T result);

    public void onJsonParesError() {

    }


    /**
     * data返回的错误信息
     * @param errorMsg
     */
    public abstract void onStatusError(String errorMsg);


}
