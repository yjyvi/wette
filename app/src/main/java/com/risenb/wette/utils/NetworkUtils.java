package com.risenb.wette.utils;

import android.text.TextUtils;

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
    private String PAGE = "pageNo";
    private String LIMIT = "pageSize";
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
    public void getGoodList(int categoryTid, int pageSize, int pageNo, OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
//        params.put("categoryTid", String.valueOf(""));
        params.put(PAGE, String.valueOf(pageNo));
        params.put(LIMIT, String.valueOf(pageSize));
        OKHttpManager.postAsync(getUrl(R.string.goodsList), params, httpBack);
    }


    /**
     * 搜索商品
     *
     * @param keyword
     * @param httpBack
     */
    public void getSearch(String keyword, DataCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("keyword", keyword);
        OKHttpManager.postAsync(getUrl(R.string.search), params, httpBack);
    }


    /**
     * 获取分类
     *
     * @param httpBack
     */
    public void getGoodCategory(OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        OKHttpManager.postAsync(getUrl(R.string.goodsCategory), params, httpBack);
    }


    /**
     * 商品详情
     *
     * @param goodsId
     * @param userId
     * @param httpBack
     */
    public void getGoodDetails(String goodsId, String userId, OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("goodsId", goodsId);
        params.put("c", userId);
        OKHttpManager.postAsync(getUrl(R.string.goodsDetail), params, httpBack);
    }


    /**
     * 商品评价列表
     *
     * @param goodsId
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getGoodEvaluate(String goodsId, String page, String limit, OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("goodsId", goodsId);
        params.put(PAGE, page);
        params.put(LIMIT, limit);
        OKHttpManager.postAsync(getUrl(R.string.evaluate), params, httpBack);
    }


    /**
     * 店铺详情
     *
     * @param shopId
     * @param page
     * @param limit
     * @param httpBack
     */
    public void getShopDetail(String shopId, String page, String limit, OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("goodsId", shopId);
        params.put(PAGE, page);
        params.put(LIMIT, limit);
        OKHttpManager.postAsync(getUrl(R.string.shopDetail), params, httpBack);
    }


    /**
     * 商品、店铺收藏与取消收藏
     *
     * @param c
     * @param operation
     * @param collectionId
     * @param dataId
     * @param type
     * @param httpBack
     */
    public void getCollection(String c, String operation, String collectionId, String dataId, String type, OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("c", c);
        params.put("operation", operation);
        if (TextUtils.equals(operation, "2")) {
            params.put("collectionId", collectionId);
        }else {
            params.put("dataId", dataId);
            params.put("type", type);
        }

        OKHttpManager.postAsync(getUrl(R.string.shopDetail), params, httpBack);
    }


    public void getOrderList(String c, int state, int page, int limit, OKHttpManager.StringCallBack stringCallBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("c", c);
        if (state != -1) {
            params.put("orderStatus", state + "");
        }
        params.put("pageSize", limit + "");
        params.put("pageNo", page + "");
        OKHttpManager.postAsync(getUrl(R.string.order_list), params, stringCallBack);
    }
}
