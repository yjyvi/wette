package com.risenb.wette.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/7.
 */

public class GoodCommentListBean {

    /**
     * data : [{"commentId":2,"commentImg":"","content":"用起来不错","createTime":1516873808000,"goodsId":"","headImg":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1121475478,2545730346&fm=27&gp=0.jpg","isRecommend":"","isShow":"","nickname":"Mr.yin","orderGid":"","score":"","shopId":"","userId":""},{"commentId":3,"commentImg":"","content":"效果很明显","createTime":1516873802000,"goodsId":"","headImg":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1121475478,2545730346&fm=27&gp=0.jpg","isRecommend":"","isShow":"","nickname":"Mr.yin","orderGid":"","score":"","shopId":"","userId":""},{"commentId":4,"commentImg":"","content":"111","createTime":1516873802000,"goodsId":"","headImg":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1121475478,2545730346&fm=27&gp=0.jpg","isRecommend":"","isShow":"","nickname":"Mr.yin","orderGid":"","score":"","shopId":"","userId":""},{"commentId":5,"commentImg":"","content":"111","createTime":1516873802000,"goodsId":"","headImg":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1121475478,2545730346&fm=27&gp=0.jpg","isRecommend":"","isShow":"","nickname":"Mr.yin","orderGid":"","score":"","shopId":"","userId":""},{"commentId":1,"commentImg":"","content":"很棒的产品哦","createTime":1516873764000,"goodsId":"","headImg":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1121475478,2545730346&fm=27&gp=0.jpg","isRecommend":"","isShow":"","nickname":"Mr.yin","orderGid":"","score":"","shopId":"","userId":""}]
     * errorCode :
     * errorMsg :
     * msg :
     * status : 1
     * total : 5
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
         * commentId : 2
         * commentImg :
         * content : 用起来不错
         * createTime : 1516873808000
         * goodsId :
         * headImg : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1121475478,2545730346&fm=27&gp=0.jpg
         * isRecommend :
         * isShow :
         * nickname : Mr.yin
         * orderGid :
         * score :
         * shopId :
         * userId :
         */

        private int commentId;
        private String commentImg;
        private String content;
        private long createTime;
        private String goodsId;
        private String headImg;
        private String isRecommend;
        private String isShow;
        private String nickname;
        private String orderGid;
        private String score;
        private String shopId;
        private String userId;

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public String getCommentImg() {
            return commentImg;
        }

        public void setCommentImg(String commentImg) {
            this.commentImg = commentImg;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOrderGid() {
            return orderGid;
        }

        public void setOrderGid(String orderGid) {
            this.orderGid = orderGid;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
