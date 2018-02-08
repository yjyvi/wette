package com.lengzhuo.xybh.beans;

public class UserBean extends NetBaseBean{


    /**
     * userInfo : {"fans":7,"follows":12,"isReal":5,"mytags":"","nickname":"康","realName":"康","sign":"荣耀家族","tags":"","type":1,"userIcon":"http://web.rongyaojiazu.com/upload/image/20170925/b6f684c66c1f20ed495a560c5ae1687e.jpg","username":"U00000001","qrcode":"http://web.rongyaojiazu.com/upload/qrcode/10582f02167ce427b0b259385aa1fbfd.png","sex":"2","honourCurrency":"9999897.60","imId":"honour_1504090157","vip":2,"vipTime":"2017-10-08 09:45:51","wxAccount":"","zfbAccount":"13554542559","isFollow":0,"serverTel":"13521499310"}
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * fans : 7
         * follows : 12
         * isReal : 5
         * mytags :
         * nickname : 康
         * realName : 康
         * sign : 荣耀家族
         * tags :
         * type : 1
         * userIcon : http://web.rongyaojiazu.com/upload/image/20170925/b6f684c66c1f20ed495a560c5ae1687e.jpg
         * username : U00000001
         * qrcode : http://web.rongyaojiazu.com/upload/qrcode/10582f02167ce427b0b259385aa1fbfd.png
         * sex : 2
         * honourCurrency : 9999897.60
         * imId : honour_1504090157
         * vip : 2
         * vipTime : 2017-10-08 09:45:51
         * wxAccount :
         * zfbAccount : 13554542559
         * isFollow : 0
         * serverTel : 13521499310
         */

        private int fans;
        private int follows;
        private int isReal;
        private String mytags;
        private String nickname;
        private String realName;
        private String sign;
        private String tags;
        private int type;
        private String userIcon;
        private String username;
        private String qrcode;
        private String sex;
        private String honourCurrency;
        private String imId;
        private int vip;
        private String vipTime;
        private String wxAccount;
        private String zfbAccount;
        private int isFollow;
        private String serverTel;

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getIsReal() {
            return isReal;
        }

        public void setIsReal(int isReal) {
            this.isReal = isReal;
        }

        public String getMytags() {
            return mytags;
        }

        public void setMytags(String mytags) {
            this.mytags = mytags;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getHonourCurrency() {
            return honourCurrency;
        }

        public void setHonourCurrency(String honourCurrency) {
            this.honourCurrency = honourCurrency;
        }

        public String getImId() {
            return imId;
        }

        public void setImId(String imId) {
            this.imId = imId;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public String getVipTime() {
            return vipTime;
        }

        public void setVipTime(String vipTime) {
            this.vipTime = vipTime;
        }

        public String getWxAccount() {
            return wxAccount;
        }

        public void setWxAccount(String wxAccount) {
            this.wxAccount = wxAccount;
        }

        public String getZfbAccount() {
            return zfbAccount;
        }

        public void setZfbAccount(String zfbAccount) {
            this.zfbAccount = zfbAccount;
        }

        public int getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(int isFollow) {
            this.isFollow = isFollow;
        }

        public String getServerTel() {
            return serverTel;
        }

        public void setServerTel(String serverTel) {
            this.serverTel = serverTel;
        }
    }
}
