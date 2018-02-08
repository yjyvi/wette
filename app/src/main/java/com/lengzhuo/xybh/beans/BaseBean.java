package com.lengzhuo.xybh.beans;

/**
 * 描述：公用
 *
 * @author linzheng
 */
public class BaseBean<T>  {


    /**
     * data :
     * errorCode : 300108
     * errorMsg :  手机号已注册！
     * msg :
     * status : 0
     * total : 0
     */

    private String data;
    private String errorCode;
    private String errorMsg;
    private String msg;
    private String status;
    private int total;

    public String getData() {
        return data;
    }

    public BaseBean setData(String data) {
        this.data = data;
        return this;
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
}
