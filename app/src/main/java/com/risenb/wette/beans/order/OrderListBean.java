package com.risenb.wette.beans.order;

import java.util.List;

/**
 * Created by yy on 2018/2/5.
 */

public class OrderListBean {

    /**
     * data : [{"addressId":10,"cancelTime":"","createTime":1517670433000,"freight":0,"goodList":[{"amount":1,"attrIds":"","attrList":"","carousel":"","cartId":"","categoryFid":"","categorySid":"","categoryTid":"","cover":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780","createTime":"","goodsDetail":"","goodsId":"","goodsIntroduce":"","goodsName":"雅诗兰黛1","isCollection":0,"isDel":"","isRecommend":"","lastUpdateTime":"","logo":"","price":100,"propertiesName":"颜色：绿色；尺码：32","shopId":"","skuId":"","skuStock":"","skuSurplus":"","sort":"","status":"","stock":"","surplus":"","type":"","video":""},{"amount":1,"attrIds":"","attrList":"","carousel":"","cartId":"","categoryFid":"","categorySid":"","categoryTid":"","cover":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780","createTime":"","goodsDetail":"","goodsId":"","goodsIntroduce":"","goodsName":"雅诗兰黛1","isCollection":0,"isDel":"","isRecommend":"","lastUpdateTime":"","logo":"","price":100,"propertiesName":"颜色：绿色；尺码：33","shopId":"","skuId":"","skuStock":"","skuSurplus":"","sort":"","status":"","stock":"","surplus":"","type":"","video":""},{"amount":2,"attrIds":"","attrList":"","carousel":"","cartId":"","categoryFid":"","categorySid":"","categoryTid":"","cover":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780","createTime":"","goodsDetail":"","goodsId":"","goodsIntroduce":"","goodsName":"雅诗兰黛3","isCollection":0,"isDel":"","isRecommend":"","lastUpdateTime":"","logo":"","price":300.04,"propertiesName":"颜色：青色；尺码：46","shopId":"","skuId":"","skuStock":"","skuSurplus":"","sort":"","status":"","stock":"","surplus":"","type":"","video":""}],"goodsAmount":4,"goodsIds":"1,1,3","invoiceContent":"","invoiceDuty":"","invoiceHead":"","invoiceType":"","logo":"","orderCid":"","orderId":30,"orderNo":"20180203230713304416174","orderStatus":1,"payChannel":"","payTime":"","remark":"","shopId":"","shopIds":"","shopName":"","totalFee":800.08,"userId":6},{"addressId":10,"cancelTime":"","createTime":1517670413000,"freight":0,"goodList":[{"amount":1,"attrIds":"","attrList":"","carousel":"","cartId":"","categoryFid":"","categorySid":"","categoryTid":"","cover":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780","createTime":"","goodsDetail":"","goodsId":"","goodsIntroduce":"","goodsName":"雅诗兰黛1","isCollection":0,"isDel":"","isRecommend":"","lastUpdateTime":"","logo":"","price":100,"propertiesName":"颜色：绿色；尺码：32","shopId":"","skuId":"","skuStock":"","skuSurplus":"","sort":"","status":"","stock":"","surplus":"","type":"","video":""}],"goodsAmount":1,"goodsIds":"1","invoiceContent":"","invoiceDuty":"","invoiceHead":"","invoiceType":"","logo":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3618554304,2887917621&fm=77&w_h=121_75&cs=2820658166,1330608299","orderCid":"","orderId":29,"orderNo":"20180203230653281325302","orderStatus":1,"payChannel":"","payTime":"","remark":"","shopId":1,"shopIds":"1","shopName":"宠物店1","totalFee":100,"userId":6}]
     * errorCode :
     * errorMsg :
     * msg :
     * status : 1
     * total : 2
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
         * addressId : 10
         * cancelTime :
         * createTime : 1517670433000
         * freight : 0.0
         * goodList : [{"amount":1,"attrIds":"","attrList":"","carousel":"","cartId":"","categoryFid":"","categorySid":"","categoryTid":"","cover":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780","createTime":"","goodsDetail":"","goodsId":"","goodsIntroduce":"","goodsName":"雅诗兰黛1","isCollection":0,"isDel":"","isRecommend":"","lastUpdateTime":"","logo":"","price":100,"propertiesName":"颜色：绿色；尺码：32","shopId":"","skuId":"","skuStock":"","skuSurplus":"","sort":"","status":"","stock":"","surplus":"","type":"","video":""},{"amount":1,"attrIds":"","attrList":"","carousel":"","cartId":"","categoryFid":"","categorySid":"","categoryTid":"","cover":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780","createTime":"","goodsDetail":"","goodsId":"","goodsIntroduce":"","goodsName":"雅诗兰黛1","isCollection":0,"isDel":"","isRecommend":"","lastUpdateTime":"","logo":"","price":100,"propertiesName":"颜色：绿色；尺码：33","shopId":"","skuId":"","skuStock":"","skuSurplus":"","sort":"","status":"","stock":"","surplus":"","type":"","video":""},{"amount":2,"attrIds":"","attrList":"","carousel":"","cartId":"","categoryFid":"","categorySid":"","categoryTid":"","cover":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780","createTime":"","goodsDetail":"","goodsId":"","goodsIntroduce":"","goodsName":"雅诗兰黛3","isCollection":0,"isDel":"","isRecommend":"","lastUpdateTime":"","logo":"","price":300.04,"propertiesName":"颜色：青色；尺码：46","shopId":"","skuId":"","skuStock":"","skuSurplus":"","sort":"","status":"","stock":"","surplus":"","type":"","video":""}]
         * goodsAmount : 4
         * goodsIds : 1,1,3
         * invoiceContent :
         * invoiceDuty :
         * invoiceHead :
         * invoiceType :
         * logo :
         * orderCid :
         * orderId : 30
         * orderNo : 20180203230713304416174
         * orderStatus : 1
         * payChannel :
         * payTime :
         * remark :
         * shopId :
         * shopIds :
         * shopName :
         * totalFee : 800.08
         * userId : 6
         */

        private int addressId;
        private String cancelTime;
        private long createTime;
        private double freight;
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
        private int orderStatus;
        private String payChannel;
        private String payTime;
        private String remark;
        private String shopId;
        private String shopIds;
        private String shopName;
        private double totalFee;
        private int userId;
        private List<GoodListBean> goodList;

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

        public double getFreight() {
            return freight;
        }

        public void setFreight(double freight) {
            this.freight = freight;
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

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
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

        public List<GoodListBean> getGoodList() {
            return goodList;
        }

        public void setGoodList(List<GoodListBean> goodList) {
            this.goodList = goodList;
        }

        public static class GoodListBean {
            /**
             * amount : 1
             * attrIds :
             * attrList :
             * carousel :
             * cartId :
             * categoryFid :
             * categorySid :
             * categoryTid :
             * cover : https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780
             * createTime :
             * goodsDetail :
             * goodsId :
             * goodsIntroduce :
             * goodsName : 雅诗兰黛1
             * isCollection : 0
             * isDel :
             * isRecommend :
             * lastUpdateTime :
             * logo :
             * price : 100.0
             * propertiesName : 颜色：绿色；尺码：32
             * shopId :
             * skuId :
             * skuStock :
             * skuSurplus :
             * sort :
             * status :
             * stock :
             * surplus :
             * type :
             * video :
             */

            private int amount;
            private String attrIds;
            private String attrList;
            private String carousel;
            private String cartId;
            private String categoryFid;
            private String categorySid;
            private String categoryTid;
            private String cover;
            private String createTime;
            private String goodsDetail;
            private String goodsId;
            private String goodsIntroduce;
            private String goodsName;
            private int isCollection;
            private String isDel;
            private String isRecommend;
            private String lastUpdateTime;
            private String logo;
            private double price;
            private String propertiesName;
            private String shopId;
            private String skuId;
            private String skuStock;
            private String skuSurplus;
            private String sort;
            private String status;
            private String stock;
            private String surplus;
            private String type;
            private String video;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
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
}
