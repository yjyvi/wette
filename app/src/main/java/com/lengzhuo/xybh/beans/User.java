package com.lengzhuo.xybh.beans;

import com.alibaba.fastjson.JSON;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class User {


    /**
     * birthday :
     * c : 9b04f723afe6d9baca71738c2c57fa08
     * headImg :
     * imPwd : 123456
     * lastLoginTime : 1517812183320
     * loginTimes : 2
     * nickname : HelloWorld
     * password : 25F9E794323B453885F5181F1B624D0B
     * phone : 13477484198
     * qqAuth :
     * registerTime : 1517812083000
     * sex : 0
     * status : 1
     * userId : 8
     * wechatAuth :
     */

    private String birthday;
    private String c;
    private String headImg;
    private String imPwd;
    private long lastLoginTime;
    private int loginTimes;
    private String nickname;
    private String password;
    private String phone;
    private String qqAuth;
    private long registerTime;
    private int sex;
    private int status;
    private int userId;
    private String wechatAuth;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getImPwd() {
        return imPwd;
    }

    public void setImPwd(String imPwd) {
        this.imPwd = imPwd;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQqAuth() {
        return qqAuth;
    }

    public void setQqAuth(String qqAuth) {
        this.qqAuth = qqAuth;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWechatAuth() {
        return wechatAuth;
    }

    public void setWechatAuth(String wechatAuth) {
        this.wechatAuth = wechatAuth;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
