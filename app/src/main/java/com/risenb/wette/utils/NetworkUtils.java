package com.risenb.wette.utils;

import com.risenb.wette.MyApplication;
import com.risenb.wette.network.DataCallBack;
import com.risenb.wette.network.OKHttpManager;

import java.util.Map;
import java.util.TreeMap;


/**
 * 调用接口
 */
public class NetworkUtils {
    private static NetworkUtils networkUtils;
    protected MyApplication applicationContext;
    private String PAGE = "page";
    private String LIMIT = "limit";
    private String TIME = "timestamp";

    public static NetworkUtils getNetworkUtils() {
        if (networkUtils == null) {
            networkUtils = new NetworkUtils();
        }
        return networkUtils;
    }

    public void setApplication(MyApplication application) {
        this.applicationContext = application;
    }

//    private String getUrl(int id) {
//        return applicationContext.getResources().getString(R.string.service_host_address).concat(applicationContext.getString(id).concat(".do"));
//    }
//
//    public String getUrl1(int id) {
//        return applicationContext.getResources().getString(R.string.service_test_address).concat(applicationContext.getString(id).concat(".do"));
//    }


    /**
     * 获取验证码
     *
     * @param phone
     * @param httpBack type  是否需要验证手机号是否存在注册，0-不需要，1-需要 2-忘记密码
     */
    public void getCode(String phone, String type, DataCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("mobile", phone);
        params.put("type", type);

        OKHttpManager.postAsync("http://web.rongyaojiazu.com/api/user/smsCode", params, httpBack);

    }

    public void getUserInfo(String userId,  DataCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("c", userId);

        OKHttpManager instance = OKHttpManager.getInstance();
        OKHttpManager.postAsync("http://web.rongyaojiazu.com/api/user/getUserInfo", params, httpBack);

    }


}
