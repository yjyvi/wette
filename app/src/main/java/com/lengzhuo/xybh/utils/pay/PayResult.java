package com.lengzhuo.xybh.utils.pay;import android.text.TextUtils;public class PayResult {    private String resultStatus;    private String result;    private String memo;    private String partner;    private String out_trade_no;    private String seller_id;    private String subject;    private String body;    private String total_free;    public PayResult(String rawResult) {        if (TextUtils.isEmpty(rawResult))            return;        String[] resultParams = rawResult.split(";");        for (String resultParam : resultParams) {            if (resultParam.startsWith("resultStatus")) {                resultStatus = gatValue(resultParam, "resultStatus");            }            if (resultParam.startsWith("result")) {                result = gatValue(resultParam, "result");            }            if (resultParam.startsWith("memo")) {                memo = gatValue(resultParam, "memo");            }        }        getResultInfo();    }    private void getResultInfo() {        for (String res : getResult().split("&")) {            if (res != null) {                if (res.startsWith("partner=")) {                    partner = res.substring("partner=".length(), res.length());                }                if (res.startsWith("seller_id=")) {                    seller_id = res.substring("seller_id=".length(), res.length());                }                if (res.startsWith("out_trade_no=")) {                    out_trade_no = res.substring("out_trade_no=".length(), res.length());                }                if (res.startsWith("subject=")) {                    subject = res.substring("subject=".length(), res.length());                }                if (res.startsWith("body=")) {                    body = res.substring("body=".length(), res.length());                }                if (res.startsWith("total_free=")) {                    total_free = res.substring("total_free=".length(), res.length());                }            }        }    }    @Override    public String toString() {        return "PayResult{" +                "resultStatus='" + resultStatus + '\'' +                ", result='" + result + '\'' +                ", memo='" + memo + '\'' +                ", partner='" + partner + '\'' +                ", out_trade_no='" + out_trade_no + '\'' +                ", seller_id='" + seller_id + '\'' +                ", subject='" + subject + '\'' +                ", body='" + body + '\'' +                ", total_free='" + total_free + '\'' +                '}';    }    private String gatValue(String content, String key) {        String prefix = key + "={";        return content.substring(content.indexOf(prefix) + prefix.length(),                content.lastIndexOf("}"));    }    public String getSeller_id() {        return seller_id;    }    public String getOut_trade_no() {        return out_trade_no;    }    public String getPartner() {        return partner;    }    public String getSubject() {        return subject;    }    public String getBody() {        return body;    }    public String getTotal_free() {        return total_free;    }    /**     * @return the resultStatus     */    public String getResultStatus() {        return resultStatus;    }    /**     * @return the memo     */    public String getMemo() {        return memo;    }    /**     * @return the result     */    public String getResult() {        return result;    }}