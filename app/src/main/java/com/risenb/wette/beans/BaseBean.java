package com.risenb.wette.beans;

/**
 * 描述：公用
 *
 * @author wangjian
 */
public class BaseBean  {
    private static final long serialVersionUID = 1876345352L;

    private String sussces;
    private String errorCode;
    private String errorMsg;
    private String data;
    private String count;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setSussces(String sussces) {
        this.sussces = sussces;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCount(String count) {
        this.count = count;
    }



}
