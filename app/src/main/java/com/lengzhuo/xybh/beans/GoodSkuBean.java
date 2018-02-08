package com.lengzhuo.xybh.beans;

/**
 * @author yjyvi
 * @data 2018/2/7.
 */

public class GoodSkuBean {


    /**
     * data : {"costPrice":100,"goodsId":1,"goodsNo":"00001","properties":"[1:2;4:5]","propertiesName":"颜色：绿色；尺码：32","salesPrice":80,"skuId":1,"skuStock":101,"skuSurplus":33}
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
         * costPrice : 100
         * goodsId : 1
         * goodsNo : 00001
         * properties : [1:2;4:5]
         * propertiesName : 颜色：绿色；尺码：32
         * salesPrice : 80
         * skuId : 1
         * skuStock : 101
         * skuSurplus : 33
         */

        private int costPrice;
        private int goodsId;
        private String goodsNo;
        private String properties;
        private String propertiesName;
        private int salesPrice;
        private int skuId;
        private int skuStock;
        private int skuSurplus;

        public int getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(int costPrice) {
            this.costPrice = costPrice;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsNo() {
            return goodsNo;
        }

        public void setGoodsNo(String goodsNo) {
            this.goodsNo = goodsNo;
        }

        public String getProperties() {
            return properties;
        }

        public void setProperties(String properties) {
            this.properties = properties;
        }

        public String getPropertiesName() {
            return propertiesName;
        }

        public void setPropertiesName(String propertiesName) {
            this.propertiesName = propertiesName;
        }

        public int getSalesPrice() {
            return salesPrice;
        }

        public void setSalesPrice(int salesPrice) {
            this.salesPrice = salesPrice;
        }

        public int getSkuId() {
            return skuId;
        }

        public void setSkuId(int skuId) {
            this.skuId = skuId;
        }

        public int getSkuStock() {
            return skuStock;
        }

        public void setSkuStock(int skuStock) {
            this.skuStock = skuStock;
        }

        public int getSkuSurplus() {
            return skuSurplus;
        }

        public void setSkuSurplus(int skuSurplus) {
            this.skuSurplus = skuSurplus;
        }
    }
}
