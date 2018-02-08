package com.lengzhuo.xybh.beans;

import java.util.List;

/**
 * Created by yjyvi on 2018/2/3.
 */

public class SearchBean {


    /**
     * data : {"shopList":[{"businessId":1,"collectionId":"","content":"宠物店好，宠物店棒","goodList":"","introduce":"宠物店好","isCollection":0,"logo":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3618554304,2887917621&fm=77&w_h=121_75&cs=2820658166,1330608299","shopId":1,"shopName":"宠物店1","shopStatus":1,"storeTime":1516779918000}],"goodsList":[{"amount":"","attrIds":"1,4","attrList":"","carousel":"http://c1.ifengimg.com/mappa/2016/01/22/60021daa7c11365be8d602c9c0bb3116.jpg,http://c1.ifengimg.com/mappa/2016/01/22/60021daa7c11365be8d602c9c0bb3116.jpg,http://c1.ifengimg.com/mappa/2016/01/22/60021daa7c11365be8d602c9c0bb3116.jpg","cartId":"","categoryFid":1,"categorySid":11,"categoryTid":16,"collectionId":"","cover":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780","createTime":1516779843000,"goodsDetail":"雅诗兰黛就是好","goodsId":1,"goodsIntroduce":"雅诗兰黛就是好","goodsName":"雅诗兰黛1","isCollection":0,"isDel":0,"isRecommend":1,"lastUpdateTime":1517907536000,"logo":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3618554304,2887917621&fm=77&w_h=121_75&cs=2820658166,1330608299","price":100.01,"propertiesName":"","shopId":1,"shopName":"宠物店1","skuId":"","skuStock":"","skuSurplus":"","sort":1,"status":1,"stock":1,"surplus":1,"type":1,"video":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780"}]}
     * errorCode :
     * errorMsg :
     * msg :
     * status : 1
     * total : 0
     */

    private DataBean data;
    private String errorCode;
    private String errorMsg;
    private String msg;
    private String status;
    private int total;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class DataBean {
        private List<ShopListBean> shopList;
        private List<GoodsListBean> goodsList;

        public List<ShopListBean> getShopList() {
            return shopList;
        }

        public void setShopList(List<ShopListBean> shopList) {
            this.shopList = shopList;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class ShopListBean {
            /**
             * businessId : 1
             * collectionId :
             * content : 宠物店好，宠物店棒
             * goodList :
             * introduce : 宠物店好
             * isCollection : 0
             * logo : https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3618554304,2887917621&fm=77&w_h=121_75&cs=2820658166,1330608299
             * shopId : 1
             * shopName : 宠物店1
             * shopStatus : 1
             * storeTime : 1516779918000
             */

            private int businessId;
            private String collectionId;
            private String content;
            private String goodList;
            private String introduce;
            private int isCollection;
            private String logo;
            private int shopId;
            private String shopName;
            private int shopStatus;
            private long storeTime;

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

            public String getGoodList() {
                return goodList;
            }

            public void setGoodList(String goodList) {
                this.goodList = goodList;
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

        public static class GoodsListBean {
            /**
             * amount :
             * attrIds : 1,4
             * attrList :
             * carousel : http://c1.ifengimg.com/mappa/2016/01/22/60021daa7c11365be8d602c9c0bb3116.jpg,http://c1.ifengimg.com/mappa/2016/01/22/60021daa7c11365be8d602c9c0bb3116.jpg,http://c1.ifengimg.com/mappa/2016/01/22/60021daa7c11365be8d602c9c0bb3116.jpg
             * cartId :
             * categoryFid : 1
             * categorySid : 11
             * categoryTid : 16
             * collectionId :
             * cover : https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780
             * createTime : 1516779843000
             * goodsDetail : 雅诗兰黛就是好
             * goodsId : 1
             * goodsIntroduce : 雅诗兰黛就是好
             * goodsName : 雅诗兰黛1
             * isCollection : 0
             * isDel : 0
             * isRecommend : 1
             * lastUpdateTime : 1517907536000
             * logo : https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3618554304,2887917621&fm=77&w_h=121_75&cs=2820658166,1330608299
             * price : 100.01
             * propertiesName :
             * shopId : 1
             * shopName : 宠物店1
             * skuId :
             * skuStock :
             * skuSurplus :
             * sort : 1
             * status : 1
             * stock : 1
             * surplus : 1
             * type : 1
             * video : https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780
             */

            private String amount;
            private String attrIds;
            private String attrList;
            private String carousel;
            private String cartId;
            private int categoryFid;
            private int categorySid;
            private int categoryTid;
            private String collectionId;
            private String cover;
            private long createTime;
            private String goodsDetail;
            private int goodsId;
            private String goodsIntroduce;
            private String goodsName;
            private int isCollection;
            private int isDel;
            private int isRecommend;
            private long lastUpdateTime;
            private String logo;
            private double price;
            private String propertiesName;
            private int shopId;
            private String shopName;
            private String skuId;
            private String skuStock;
            private String skuSurplus;
            private int sort;
            private int status;
            private int stock;
            private int surplus;
            private int type;
            private String video;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAttrIds() {
                return attrIds;
            }

            public void setAttrIds(String attrIds) {
                this.attrIds = attrIds;
            }

            public String getAttrList() {
                return attrList;
            }

            public void setAttrList(String attrList) {
                this.attrList = attrList;
            }

            public String getCarousel() {
                return carousel;
            }

            public void setCarousel(String carousel) {
                this.carousel = carousel;
            }

            public String getCartId() {
                return cartId;
            }

            public void setCartId(String cartId) {
                this.cartId = cartId;
            }

            public int getCategoryFid() {
                return categoryFid;
            }

            public void setCategoryFid(int categoryFid) {
                this.categoryFid = categoryFid;
            }

            public int getCategorySid() {
                return categorySid;
            }

            public void setCategorySid(int categorySid) {
                this.categorySid = categorySid;
            }

            public int getCategoryTid() {
                return categoryTid;
            }

            public void setCategoryTid(int categoryTid) {
                this.categoryTid = categoryTid;
            }

            public String getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(String collectionId) {
                this.collectionId = collectionId;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getGoodsDetail() {
                return goodsDetail;
            }

            public void setGoodsDetail(String goodsDetail) {
                this.goodsDetail = goodsDetail;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsIntroduce() {
                return goodsIntroduce;
            }

            public void setGoodsIntroduce(String goodsIntroduce) {
                this.goodsIntroduce = goodsIntroduce;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public int getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(int isCollection) {
                this.isCollection = isCollection;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public int getIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(int isRecommend) {
                this.isRecommend = isRecommend;
            }

            public long getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(long lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getPropertiesName() {
                return propertiesName;
            }

            public void setPropertiesName(String propertiesName) {
                this.propertiesName = propertiesName;
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

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getSkuStock() {
                return skuStock;
            }

            public void setSkuStock(String skuStock) {
                this.skuStock = skuStock;
            }

            public String getSkuSurplus() {
                return skuSurplus;
            }

            public void setSkuSurplus(String skuSurplus) {
                this.skuSurplus = skuSurplus;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getSurplus() {
                return surplus;
            }

            public void setSurplus(int surplus) {
                this.surplus = surplus;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }
        }
    }
}
