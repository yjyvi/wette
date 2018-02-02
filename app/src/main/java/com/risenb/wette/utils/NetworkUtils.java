package com.risenb.wette.utils;

import com.risenb.wette.MyApplication;
import com.risenb.wette.R;
import com.risenb.wette.network.DataCallBack;
import com.risenb.wette.network.OKHttpManager;

import java.util.Map;
import java.util.TreeMap;


/**
 * 调用接口
 */
public class NetworkUtils {
    private static NetworkUtils networkUtils;
    private String PAGE = "page";
    private String LIMIT = "limit";
    private String TIME = "timestamp";

    public static NetworkUtils getNetworkUtils() {
        if (networkUtils == null) {
            networkUtils = new NetworkUtils();
        }
        return networkUtils;
    }


    private String getUrl(int id) {
        return MyApplication.applicationContext.getResources().getString(R.string.service_host_address).concat(MyApplication.applicationContext.getString(id).concat(".do"));
    }

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

    /**
     * 获取首页数据
     *
     * @param httpBack
     */
    public void getHomeData(DataCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        OKHttpManager.postAsync(getUrl(R.string.homeData), params, httpBack);
    }


    /**
     * 获取首页数据
     *
     * @param categoryTid
     * @param pageSize
     * @param pageNo
     * @param httpBack
     */
    public void getGoodsList(int categoryTid, int pageSize, int pageNo, OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("categoryTid", String.valueOf(""));
        params.put("pageSize", String.valueOf(pageSize));
        params.put("pageNo", String.valueOf(pageNo));
        OKHttpManager.postAsync(getUrl(R.string.goodsList), params, httpBack);
    }


    /**
     * 获取分类
     * @param httpBack
     */
    public void getGoodsCategory(DataCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        OKHttpManager.postAsync(getUrl(R.string.goodsCategory), params, httpBack);
    }


}
