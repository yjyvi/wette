package com.risenb.wette.beans.shoppingcart;

import java.util.List;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/03
 *     desc   : 购物车 店铺
 *     version: 1.0
 * </pre>
 */
public class ShopBean {

    private boolean isSelected;
    /**
     * businessId : 2
     * collectionId :
     * content :
     * introduce : 宠物店好宠物店好22222
     * isCollection : 0
     * logo : https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3618554304,2887917621&fm=77&w_h=121_75&cs=2820658166,1330608299
     * shopId : 2
     * shopName : 宠物店2
     * shopStatus : 1
     * storeTime : 1517456636000
     */

    private int businessId;
    private String collectionId;
    private String content;
    private String introduce;
    private int isCollection;
    private String logo;
    private int shopId;
    private String shopName;
    private int shopStatus;
    private long storeTime;
    private List<CommodityBean> goodList;

    public List<CommodityBean> getGoodList() {
        return goodList;
    }

    public ShopBean setGoodList(List<CommodityBean> goodList) {
        this.goodList = goodList;
        return this;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public ShopBean setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(int shopStatus) {
        this.shopStatus = shopStatus;
    }

    public long getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(long storeTime) {
        this.storeTime = storeTime;
    }
}
