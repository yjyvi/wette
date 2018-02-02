package com.risenb.wette.beans;

/**统一bean的头
 * Created by yonghao zeng on 2017/5/11.
 */

public  class NetBaseBean<T> {

    public NetBaseBean() {
    }


    public  T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String errorCode;
    public String errorMsg;
    public String status;
    public String msg;
    public int total;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

}
