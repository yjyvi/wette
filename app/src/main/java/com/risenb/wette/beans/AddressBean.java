package com.risenb.wette.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 地址
 *     version: 1.0
 * </pre>
 */
public class AddressBean implements Parcelable {


    /**
     * address : 没有名字的地方
     * addressId : 16
     * addressee : 大佬
     * areaId : 110101
     * areaName : 东城区
     * cityId : 110100
     * cityName : 北京市
     * createTime : 1517822291000
     * isDefault : 0
     * isDel : 0
     * postalcode : 100010
     * provinceId : 110000
     * provinceName : 北京市
     * telephone : 13477484198
     * userId : 8
     */

    private String address;
    private int addressId;
    private String addressee;
    private int areaId;
    private String areaName;
    private int cityId;
    private String cityName;
    private long createTime;
    private int isDefault;
    private int isDel;
    private String postalcode;
    private int provinceId;
    private String provinceName;
    private String telephone;
    private int userId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeInt(this.addressId);
        dest.writeString(this.addressee);
        dest.writeInt(this.areaId);
        dest.writeString(this.areaName);
        dest.writeInt(this.cityId);
        dest.writeString(this.cityName);
        dest.writeLong(this.createTime);
        dest.writeInt(this.isDefault);
        dest.writeInt(this.isDel);
        dest.writeString(this.postalcode);
        dest.writeInt(this.provinceId);
        dest.writeString(this.provinceName);
        dest.writeString(this.telephone);
        dest.writeInt(this.userId);
    }

    public AddressBean() {
    }

    protected AddressBean(Parcel in) {
        this.address = in.readString();
        this.addressId = in.readInt();
        this.addressee = in.readString();
        this.areaId = in.readInt();
        this.areaName = in.readString();
        this.cityId = in.readInt();
        this.cityName = in.readString();
        this.createTime = in.readLong();
        this.isDefault = in.readInt();
        this.isDel = in.readInt();
        this.postalcode = in.readString();
        this.provinceId = in.readInt();
        this.provinceName = in.readString();
        this.telephone = in.readString();
        this.userId = in.readInt();
    }

    public static final Parcelable.Creator<AddressBean> CREATOR = new Parcelable.Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel source) {
            return new AddressBean(source);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };
}
