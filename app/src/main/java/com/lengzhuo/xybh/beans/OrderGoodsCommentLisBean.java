package com.lengzhuo.xybh.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/8.
 */

public class OrderGoodsCommentLisBean {

    private String errorCode;
    private String errorMsg;
    private String msg;
    private String status;
    private int total;
    private List<OrderGoodsCommentLisBean.DataBean> data;


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
        private String orderGid;
        private String cover;
        private String goodsName;
        private int isEvaluate;
        private String propertiesName;
        private String shopId;
        private String goodsId;


        public String getOrderGid() {
            return orderGid;
        }

        public void setOrderGid(String orderGid) {
            this.orderGid = orderGid;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getIsEvaluate() {
            return isEvaluate;
        }

        public void setIsEvaluate(int isEvaluate) {
            this.isEvaluate = isEvaluate;
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

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }
    }
}
