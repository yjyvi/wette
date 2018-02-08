package com.lengzhuo.xybh.beans;

/**
 * @author yjyvi
 * @data 2018/2/6.
 * 创建订单需要的Json
 */

public class CreateOrderGoodsBean {

    private String goodsId;
    private String goodsAmount;
    private String shopId;
    private String skuId;

    public CreateOrderGoodsBean() {
    }

    public CreateOrderGoodsBean(String goodsId, String goodsAmount, String shopId, String skuId) {
        this.goodsId = goodsId;
        this.goodsAmount = goodsAmount;
        this.shopId = shopId;
        this.skuId = skuId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        return "CreateOrderGoodsBean{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsAmount='" + goodsAmount + '\'' +
                ", shopId='" + shopId + '\'' +
                ", skuId='" + skuId + '\'' +
                '}';
    }
}
