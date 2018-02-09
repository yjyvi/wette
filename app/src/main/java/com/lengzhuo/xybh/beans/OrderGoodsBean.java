package com.lengzhuo.xybh.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/9.
 */

public class OrderGoodsBean  {


    /**
     * data : [{"amount":"","attrIds":"","attrIdsSon":"","attrList":"","carousel":"","cartId":"","categoryFid":"","categorySid":"","categoryTid":"","collectionId":"","cover":"http://img14.360buyimg.com/n7/jfs/t2071/42/723861603/68617/2be301c2/562464b2N7246e169.jpg","createTime":"","goodsDetail":"","goodsId":1,"goodsIntroduce":"","goodsName":"雅诗兰黛1","isCollection":0,"isDel":"","isEvaluate":0,"isRecommend":"","lastUpdateTime":"","logo":"","orderGid":91,"price":"","propertiesName":"颜色：绿色；尺码：32","shopId":1,"shopName":"","skuId":"","skuInfo":"","skuStock":"","skuSurplus":"","sort":"","status":"","stock":"","surplus":"","type":"","video":""}]
     * errorCode :
     * errorMsg :
     * msg :
     * status : 1
     * total : 1
     */

    private String errorCode;
    private String errorMsg;
    private String msg;
    private String status;
    private int total;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * amount :
         * attrIds :
         * attrIdsSon :
         * attrList :
         * carousel :
         * cartId :
         * categoryFid :
         * categorySid :
         * categoryTid :
         * collectionId :
         * cover : http://img14.360buyimg.com/n7/jfs/t2071/42/723861603/68617/2be301c2/562464b2N7246e169.jpg
         * createTime :
         * goodsDetail :
         * goodsId : 1
         * goodsIntroduce :
         * goodsName : 雅诗兰黛1
         * isCollection : 0
         * isDel :
         * isEvaluate : 0
         * isRecommend :
         * lastUpdateTime :
         * logo :
         * orderGid : 91
         * price :
         * propertiesName : 颜色：绿色；尺码：32
         * shopId : 1
         * shopName :
         * skuId :
         * skuInfo :
         * skuStock :
         * skuSurplus :
         * sort :
         * status :
         * stock :
         * surplus :
         * type :
         * video :
         */

        private String amount;
        private String attrIds;
        private String attrIdsSon;
        private String attrList;
        private String carousel;
        private String cartId;
        private String categoryFid;
        private String categorySid;
        private String categoryTid;
        private String collectionId;
        private String cover;
        private String createTime;
        private String goodsDetail;
        private String goodsId;
        private String goodsIntroduce;
        private String goodsName;
        private int isCollection;
        private String isDel;
        private int isEvaluate;
        private String isRecommend;
        private String lastUpdateTime;
        private String logo;
        private int orderGid;
        private String price;
        private String propertiesName;
        private String shopId;
        private String shopName;
        private String skuId;
        private String skuInfo;
        private String skuStock;
        private String skuSurplus;
        private String sort;
        private String status;
        private String stock;
        private String surplus;
        private String type;
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

        public String getAttrIdsSon() {
            return attrIdsSon;
        }

        public void setAttrIdsSon(String attrIdsSon) {
            this.attrIdsSon = attrIdsSon;
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

        public String getCategoryFid() {
            return categoryFid;
        }

        public void setCategoryFid(String categoryFid) {
            this.categoryFid = categoryFid;
        }

        public String getCategorySid() {
            return categorySid;
        }

        public void setCategorySid(String categorySid) {
            this.categorySid = categorySid;
        }

        public String getCategoryTid() {
            return categoryTid;
        }

        public void setCategoryTid(String categoryTid) {
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getGoodsDetail() {
            return goodsDetail;
        }

        public void setGoodsDetail(String goodsDetail) {
            this.goodsDetail = goodsDetail;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
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

        public String getIsDel() {
            return isDel;
        }

        public void setIsDel(String isDel) {
            this.isDel = isDel;
        }

        public int getIsEvaluate() {
            return isEvaluate;
        }

        public void setIsEvaluate(int isEvaluate) {
            this.isEvaluate = isEvaluate;
        }

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getOrderGid() {
            return orderGid;
        }

        public void setOrderGid(int orderGid) {
            this.orderGid = orderGid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPropertiesName() {
            return propertiesName;
        }

        public void setPropertiesName(String propertiesName) {
            this.propertiesName = propertiesName;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
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

        public String getSkuInfo() {
            return skuInfo;
        }

        public void setSkuInfo(String skuInfo) {
            this.skuInfo = skuInfo;
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

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getSurplus() {
            return surplus;
        }

        public void setSurplus(String surplus) {
            this.surplus = surplus;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
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
