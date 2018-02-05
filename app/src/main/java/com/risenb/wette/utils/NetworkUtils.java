package com.risenb.wette.utils;

import android.text.TextUtils;

import com.risenb.wette.MyApplication;
import com.risenb.wette.R;
import com.risenb.wette.network.DataCallBack;
import com.risenb.wette.network.OKHttpManager;

import java.util.HashMap;
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
     */
    public void getHomeData(DataCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        OKHttpManager.postAsync(getUrl(R.string.homeData), params, httpBack);
    }


    /**
     * 获取首页数据
     */
    public void getGoodList(int categoryTid, int pageSize, int pageNo, OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
//        params.put("categoryTid", String.valueOf(""));
        params.put("pageNo", String.valueOf(pageNo));
        params.put("pageSize", String.valueOf(pageSize));
        OKHttpManager.postAsync(getUrl(R.string.goodsList), params, httpBack);
    }


    /**
     * 搜索商品
     */
    public void getSearch(String keyword, DataCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("keyword", keyword);
        OKHttpManager.postAsync(getUrl(R.string.search), params, httpBack);
    }


    /**
     * 获取分类
     */
    public void getGoodCategory(OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        OKHttpManager.postAsync(getUrl(R.string.goodsCategory), params, httpBack);
    }

    public void getGoodDetails(String goodsId, String userId, OKHttpManager.StringCallBack httpBack) {
        Map<String, String> params = new TreeMap<>();
        params.put("goodsId", goodsId);
        params.put("c", userId);
        OKHttpManager.postAsync(getUrl(R.string.goodsDetail), params, httpBack);
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

    public void register(String type, String phone, String password, String code, String nickName, OKHttpManager.StringCallBack callBack) {
        register(type, phone, password, code, nickName, "", callBack);
    }

    public void register(String type, String phone, String password, String code, String nickName, String authorize, OKHttpManager.StringCallBack callBack) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("phone", phone);
        params.put("password", password);
        params.put("passwordTwo", password);
        params.put("code", code);
        params.put("nickname", nickName);
        if (!TextUtils.isEmpty(authorize)) params.put("authorize", authorize);
        OKHttpManager.postAsync(getUrl(R.string.user_register), params, callBack);
    }

    public void login(String type, String phone, String password, OKHttpManager.StringCallBack callBack) {
        login(type, phone, password, "", callBack);
    }

    public void login(String type, String phone, String password, String authorize, OKHttpManager.StringCallBack callBack) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("phone", phone);
        params.put("password", password);
        if (!TextUtils.isEmpty(authorize)) params.put("authorize", authorize);
        OKHttpManager.postAsync(getUrl(R.string.user_login), params, callBack);
    }

    public void getAddressList(OKHttpManager.StringCallBack callback) {
        getAddressList("", "", callback);
    }

    public void getAddressList(String addressId, String isDefault, OKHttpManager.StringCallBack callBack) {
        Map<String, String> params = getParams();
        if (!TextUtils.isEmpty(addressId)) params.put("addressId", addressId);
        if (!TextUtils.isEmpty(isDefault)) params.put("isDefault", isDefault);
        OKHttpManager.postAsync(getUrl(R.string.address_list), params, callBack);
    }

    public void addAddress( String addressee,
                            String provinceId,
                            String provinceName,
                            String cityId,
                            String cityName,
                            String areaId,
                            String areaName,
                            String address,
                            String postalCode,
                            String telephone,
                            OKHttpManager.StringCallBack callBack) {
        updateAddress("",addressee,provinceId,provinceName,cityId,cityName,areaId,areaName,address,postalCode,telephone,callBack);
    }

    public void updateAddress(
            String addressId,
            String addressee,
            String provinceId,
            String provinceName,
            String cityId,
            String cityName,
            String areaId,
            String areaName,
            String address,
            String postalCode,
            String telephone,
            OKHttpManager.StringCallBack callBack) {

        Map<String,String> params = getParams();
        if(!TextUtils.isEmpty(addressId))params.put("addressId",addressId);
        params.put("addressee", addressee);
        params.put("provinceId", provinceId);
        params.put("provinceName", provinceName);
        params.put("cityId", cityId);
        params.put("cityName",cityName);
        params.put("areaId",areaId);
        params.put("areaName",areaName);
        params.put("address",address);
        params.put("postalcode",postalCode);
        params.put("telephone", telephone);

        OKHttpManager.postAsync(getUrl(R.string.address_update), params, callBack);

    }


    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap();
        params.put("c", UserManager.getUser().getC());
        return params;
    }

}
