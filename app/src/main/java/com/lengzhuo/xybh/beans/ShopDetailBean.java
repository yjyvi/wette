package com.lengzhuo.xybh.beans;

/**
 * @author yjyvi
 * @data 2018/2/6.
 */

public class ShopDetailBean {

    /**
     * data : {"businessId":3,"collectionId":"","content":"33333333333333333333333333333","goodList":"","introduce":"宠物店好33333","isCollection":0,"logo":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3618554304,2887917621&fm=77&w_h=121_75&cs=2820658166,1330608299","shopId":3,"shopName":"宠物店3","shopStatus":1,"storeTime":1517479670000}
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
        /**
         * businessId : 3
         * collectionId :
         * content : 33333333333333333333333333333
         * goodList :
         * introduce : 宠物店好33333
         * isCollection : 0
         * logo : https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3618554304,2887917621&fm=77&w_h=121_75&cs=2820658166,1330608299
         * shopId : 3
         * shopName : 宠物店3
         * shopStatus : 1
         * storeTime : 1517479670000
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
}
