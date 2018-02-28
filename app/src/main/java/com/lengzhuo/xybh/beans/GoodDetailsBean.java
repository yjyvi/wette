package com.lengzhuo.xybh.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yjyvi on 2018/2/4.
 */

public class GoodDetailsBean {

    /**
     * data : {"amount":"","attrIds":"1,4","attrList":[{"attrId":1,"attrList":[{"attrId":2,"attrList":"","attrName":"绿色","attrPid":1,"createTime":1516847897000,"goodsId":1},{"attrId":3,"attrList":"","attrName":"蓝色","attrPid":1,"createTime":1516847910000,"goodsId":1}],"attrName":"颜色","attrPid":"","createTime":1516847881000,"goodsId":""},{"attrId":4,"attrList":[{"attrId":5,"attrList":"","attrName":"32","attrPid":4,"createTime":1516847936000,"goodsId":1},{"attrId":6,"attrList":"","attrName":"33","attrPid":4,"createTime":1516847948000,"goodsId":1}],"attrName":"尺码","attrPid":"","createTime":1516847927000,"goodsId":""}],"carousel":"","cartId":"","categoryFid":"","categorySid":"","categoryTid":"","cover":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780","createTime":"","goodsDetail":"雅诗兰黛就是好","goodsId":1,"goodsIntroduce":"雅诗兰黛就是好","goodsName":"雅诗兰黛1","isCollection":0,"isDel":"","isRecommend":"","lastUpdateTime":"","logo":"","price":100.01,"propertiesName":"","shopId":1,"skuId":"","skuStock":"","skuSurplus":"","sort":"","status":"","stock":1,"surplus":1,"type":1,"video":"https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780"}
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

    public static class DataBean  implements Serializable {
        /**
         * amount :
         * attrIds : 1,4
         * attrList : [{"attrId":1,"attrList":[{"attrId":2,"attrList":"","attrName":"绿色","attrPid":1,"createTime":1516847897000,"goodsId":1},{"attrId":3,"attrList":"","attrName":"蓝色","attrPid":1,"createTime":1516847910000,"goodsId":1}],"attrName":"颜色","attrPid":"","createTime":1516847881000,"goodsId":""},{"attrId":4,"attrList":[{"attrId":5,"attrList":"","attrName":"32","attrPid":4,"createTime":1516847936000,"goodsId":1},{"attrId":6,"attrList":"","attrName":"33","attrPid":4,"createTime":1516847948000,"goodsId":1}],"attrName":"尺码","attrPid":"","createTime":1516847927000,"goodsId":""}]
         * carousel :
         * cartId :
         * categoryFid :
         * categorySid :
         * categoryTid :
         * cover : https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780
         * createTime :
         * goodsDetail : 雅诗兰黛就是好
         * goodsId : 1
         * goodsIntroduce : 雅诗兰黛就是好
         * goodsName : 雅诗兰黛1
         * isCollection : 0
         * isDel :
         * isRecommend :
         * lastUpdateTime :
         * logo :
         * price : 100.01
         * propertiesName :
         * shopId : 1
         * skuId :
         * skuStock :
         * skuSurplus :
         * sort :
         * status :
         * stock : 1
         * surplus : 1
         * type : 1
         * video : https://ss0.bdstatic.com/6ONWsjip0QIZ8tyhnq/it/u=3545401083,2251952841&fm=77&w_h=121_75&cs=2008984878,1171842780
         */

        private String amount;
        private String attrIds;
        private String carousel;
        private String cartId;
        private String categoryFid;
        private String categorySid;
        private String categoryTid;
        private String collectionId;

        private String cover;
        private String createTime;
        private String goodsDetail;
        private int goodsId;
        private String goodsIntroduce;
        private String goodsName;
        private int isCollection;
        private String isDel;
        private String isRecommend;
        private String lastUpdateTime;
        private String logo;
        private double price;
        private String propertiesName;
        private int shopId;
        private String skuId;
        private String skuStock;
        private String skuSurplus;
        private String sort;
        private String status;
        private int stock;
        private int surplus;
        private int type;
        private String video;
        private List<AttrListBeanX> attrList;

        public String getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(String collectionId) {
            this.collectionId = collectionId;
        }

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

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
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

        public List<AttrListBeanX> getAttrList() {
            return attrList;
        }

        public void setAttrList(List<AttrListBeanX> attrList) {
            this.attrList = attrList;
        }



        public static class AttrListBeanX {
            /**
             * attrId : 1
             * attrList : [{"attrId":2,"attrList":"","attrName":"绿色","attrPid":1,"createTime":1516847897000,"goodsId":1},{"attrId":3,"attrList":"","attrName":"蓝色","attrPid":1,"createTime":1516847910000,"goodsId":1}]
             * attrName : 颜色
             * attrPid :
             * createTime : 1516847881000
             * goodsId :
             */

            private int attrId;
            private String attrName;
            private String attrPid;
            private long createTime;
            private String goodsId;
            private List<AttrListBean> attrList;

            public int getAttrId() {
                return attrId;
            }

            public void setAttrId(int attrId) {
                this.attrId = attrId;
            }

            public String getAttrName() {
                return attrName;
            }

            public void setAttrName(String attrName) {
                this.attrName = attrName;
            }

            public String getAttrPid() {
                return attrPid;
            }

            public void setAttrPid(String attrPid) {
                this.attrPid = attrPid;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public List<AttrListBean> getAttrList() {
                return attrList;
            }

            public void setAttrList(List<AttrListBean> attrList) {
                this.attrList = attrList;
            }

            public static class AttrListBean {
                /**
                 * attrId : 2
                 * attrList :
                 * attrName : 绿色
                 * attrPid : 1
                 * createTime : 1516847897000
                 * goodsId : 1
                 */

                private int attrId;
                private String attrList;
                private String attrName;
                private int attrPid;
                private long createTime;
                private int goodsId;

                public int getAttrId() {
                    return attrId;
                }

                public void setAttrId(int attrId) {
                    this.attrId = attrId;
                }

                public String getAttrList() {
                    return attrList;
                }

                public void setAttrList(String attrList) {
                    this.attrList = attrList;
                }

                public String getAttrName() {
                    return attrName;
                }

                public void setAttrName(String attrName) {
                    this.attrName = attrName;
                }

                public int getAttrPid() {
                    return attrPid;
                }

                public void setAttrPid(int attrPid) {
                    this.attrPid = attrPid;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }
            }
        }
    }
}
