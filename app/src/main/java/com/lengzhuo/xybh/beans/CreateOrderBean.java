package com.lengzhuo.xybh.beans;

/**
 * @author yjyvi
 * @data 2018/2/7.
 */

public class CreateOrderBean {

    /**
     * data : {"addressId":24,"cancelTime":"","createTime":1517979458663,"freight":"","goodList":"","goodsAmount":1,"goodsIds":"1","invoiceContent":"","invoiceDuty":"","invoiceHead":"","invoiceType":"","logo":"","orderCid":"","orderId":31,"orderNo":"20180207125738623811235","orderStatus":"","payChannel":"","payTime":"","remark":"","shopId":"","shopIds":"1","shopName":"","totalFee":100,"userId":10}
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
         * addressId : 24
         * cancelTime :
         * createTime : 1517979458663
         * freight :
         * goodList :
         * goodsAmount : 1
         * goodsIds : 1
         * invoiceContent :
         * invoiceDuty :
         * invoiceHead :
         * invoiceType :
         * logo :
         * orderCid :
         * orderId : 31
         * orderNo : 20180207125738623811235
         * orderStatus :
         * payChannel :
         * payTime :
         * remark :
         * shopId :
         * shopIds : 1
         * shopName :
         * totalFee : 100.0
         * userId : 10
         */

        private int addressId;
        private String cancelTime;
        private long createTime;
        private String freight;
        private String goodList;
        private int goodsAmount;
        private String goodsIds;
        private String invoiceContent;
        private String invoiceDuty;
        private String invoiceHead;
        private String invoiceType;
        private String logo;
        private String orderCid;
        private int orderId;
        private String orderNo;
        private String orderStatus;
        private String payChannel;
        private String payTime;
        private String remark;
        private String shopId;
        private String shopIds;
        private String shopName;
        private double totalFee;
        private int userId;

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public String getCancelTime() {
            return cancelTime;
        }

        public void setCancelTime(String cancelTime) {
            this.cancelTime = cancelTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getGoodList() {
            return goodList;
        }

        public void setGoodList(String goodList) {
            this.goodList = goodList;
        }

        public int getGoodsAmount() {
            return goodsAmount;
        }

        public void setGoodsAmount(int goodsAmount) {
            this.goodsAmount = goodsAmount;
        }

        public String getGoodsIds() {
            return goodsIds;
        }

        public void setGoodsIds(String goodsIds) {
            this.goodsIds = goodsIds;
        }

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public String getInvoiceDuty() {
            return invoiceDuty;
        }

        public void setInvoiceDuty(String invoiceDuty) {
            this.invoiceDuty = invoiceDuty;
        }

        public String getInvoiceHead() {
            return invoiceHead;
        }

        public void setInvoiceHead(String invoiceHead) {
            this.invoiceHead = invoiceHead;
        }

        public String getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getOrderCid() {
            return orderCid;
        }

        public void setOrderCid(String orderCid) {
            this.orderCid = orderCid;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopIds() {
            return shopIds;
        }

        public void setShopIds(String shopIds) {
            this.shopIds = shopIds;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public double getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(double totalFee) {
            this.totalFee = totalFee;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
