package com.lengzhuo.xybh.utils;

import com.lengzhuo.xybh.MyApplication;
import com.lengzhuo.xybh.R;

/**
 * 显示字符串占位工具类
 *
 * @author yjyvi
 * @data 2018/3/3.
 */

public class PlaceholderUtils {

    /**
     * 商品价格占位
     *
     * @param money
     * @return
     */
    public static String pricePlaceholder( double money) {
        return String.format(MyApplication.applicationContext.getResources().getString(R.string.money_icon), money);
    }


    /**
     * sku显示占位
     *
     * @param str
     * @return
     */
    public static String skuPlaceholder(String  str) {
        return String.format(MyApplication.applicationContext.getResources().getString(R.string.sku_selected), str);
    }
}
