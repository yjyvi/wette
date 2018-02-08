package com.lengzhuo.xybh.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yjyvi on 2018/1/30.
 */

public class OKHttpManager {
    private OkHttpClient client;
    private static OKHttpManager oKhttpManager;
    private Handler mHandler;

    /**
     * 单例获取 OKhttpManager实例
     */
    public static OKHttpManager getInstance() {
        if (oKhttpManager == null) {
            oKhttpManager = new OKHttpManager();
        }
        return oKhttpManager;
    }

    private OKHttpManager() {
        client = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }


    /**************
     * 内部逻辑处理
     ****************/
    private Response p_getSync(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response;
    }

    private String p_getSyncAsString(String url) throws IOException {
        return p_getSync(url).body().string();
    }


    /**
     * get异步请求
     *
     * @param url
     * @param callBack
     */
    private void p_getAsync(String url, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(call, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Log.d("OKHttpManager", result);

//                    deliverDataSuccess(result, callBack);
                } catch (IOException e) {
                    deliverDataFailure(call, e, callBack);
                }
            }
        });
    }


    /**
     * post异步请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    private void p_postAsync(final String url, Map<String, String> params, final DataCallBack callBack) {
        RequestBody requestBody = null;

        if (params == null) {
            params = new HashMap<String, String>();
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey().toString();
            String value = null;
            if (entry.getValue() == null) {
                value = "";
            } else {
                value = entry.getValue().toString();
            }
            builder.add(key, value);
        }
        requestBody = builder.build();

        Log.e("请求返回参数=", url + params.toString());
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(call, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String result = response.body().string();
                    Log.e(url + "请求成功返回参数=", result);
                    deliverDataSuccess(result, callBack);
                } catch (IOException e) {
                    deliverDataFailure(call, e, callBack);
                }

            }
        });
    }


    /**
     * 数据分发的方法
     */
    private void deliverDataFailure(final Call call, final IOException e, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onStatusError(e.getMessage());
                }
            }
        });
    }

    private void deliverDataSuccess(final String result, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestSuccess(result);
                }
            }
        });
    }


    //===================不经反射基类的网络请求========================================


    private void p_postAsync(final String url, Map<String, String> params, final StringCallBack callBack) {
        RequestBody requestBody = null;

        if (params == null) {
            params = new HashMap<String, String>();
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey().toString();
            String value = null;
            if (entry.getValue() == null) {
                value = "";
            } else {
                value = entry.getValue().toString();
            }
            builder.add(key, value);
        }
        requestBody = builder.build();

        Log.e("请求返回参数=", url + params.toString());
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(call, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String result = response.body().string();
                    Log.e(url+"请求成功返回参数=", result);
                    deliverDataSuccess(result, callBack);
                } catch (IOException e) {
                    deliverDataFailure(call, e, callBack);
                }

            }
        });
    }


    /**
     * 数据分发的方法
     */
    private void deliverDataFailure(final Call call, final IOException e, final StringCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestFailure(call, e);
                }
            }
        });
    }

    private void deliverDataSuccess(final String result, final StringCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestSuccess(result);
                }
            }
        });
    }


    public static void postAsync(String url, Map<String, String> params, StringCallBack callBack) {
        getInstance().p_postAsync(url, params, callBack);
    }

    //===========================================================

    /******************
     * 对外公布的方法
     *****************/
    /**
     * //同步GET，返回Response类型数据
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static Response getSync(String url) throws IOException {
        return getInstance().p_getSync(url);
    }


    /**
     * //同步GET，返回String类型数据
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getSyncAsString(String url) throws IOException {
        return getInstance().p_getSyncAsString(url);
    }


    /**
     * //异步GET请求
     *
     * @param url
     * @param callBack
     */
    public static void getAsync(String url, DataCallBack callBack) {
        getInstance().p_getAsync(url, callBack);
    }

    /**
     * //异步POST请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public static void postAsync(String url, Map<String, String> params, DataCallBack callBack) {
        getInstance().p_postAsync(url, params, callBack);
    }


    /**
     * 数据回调接口
     */
    public interface StringCallBack {
        void requestFailure(Call call, IOException e);

        void requestSuccess(String result);
    }
}
