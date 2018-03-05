package com.lengzhuo.xybh.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author yjyvi
 * @data 2018/2/6.
 * 创建订单需要的Json
 */

public class CreateOrderGoodsBean implements Parcelable {

    private String goodsId;
    private String goodsAmount;
    private String shopId;
    private String skuId;
    private String goodsImageUrl;
    private String goodsTitle;
    private String goodsSkuContent;
    private double goodsPrice;


    public CreateOrderGoodsBean() {
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsSkuContent() {
        return goodsSkuContent;
    }

    public void setGoodsSkuContent(String goodsSkuContent) {
        this.goodsSkuContent = goodsSkuContent;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.goodsId);
        dest.writeString(this.goodsAmount);
        dest.writeString(this.shopId);
        dest.writeString(this.skuId);
        dest.writeString(this.goodsImageUrl);
        dest.writeString(this.goodsTitle);
        dest.writeString(this.goodsSkuContent);
        dest.writeDouble(this.goodsPrice);
    }

    protected CreateOrderGoodsBean(Parcel in) {
        this.goodsId = in.readString();
        this.goodsAmount = in.readString();
        this.shopId = in.readString();
        this.skuId = in.readString();
        this.goodsImageUrl = in.readString();
        this.goodsTitle = in.readString();
        this.goodsSkuContent = in.readString();
        this.goodsPrice = in.readDouble();
    }

    public static final Creator<CreateOrderGoodsBean> CREATOR = new Creator<CreateOrderGoodsBean>() {
        @Override
        public CreateOrderGoodsBean createFromParcel(Parcel source) {
            return new CreateOrderGoodsBean(source);
        }

        @Override
        public CreateOrderGoodsBean[] newArray(int size) {
            return new CreateOrderGoodsBean[size];
        }
    };
}
