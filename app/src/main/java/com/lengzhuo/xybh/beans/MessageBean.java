package com.lengzhuo.xybh.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 消息
 *     version: 1.0
 * </pre>
 */
public class MessageBean implements Parcelable {


    /**
     * content : 您有一笔订单未支付，请及时处理
     * createTime : 1517919316000
     * creator :
     * isDel :
     * isRead : 0
     * messageId :
     * title : 订单支付消息
     * type : 2
     * userMsgId : 3
     */

    private String content;
    private long createTime;
    private String creator;
    private String isDel;
    private int isRead;
    private String messageId;
    private String title;
    private int type;
    private int userMsgId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(createTime));
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserMsgId() {
        return userMsgId;
    }

    public void setUserMsgId(int userMsgId) {
        this.userMsgId = userMsgId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeLong(this.createTime);
        dest.writeString(this.creator);
        dest.writeString(this.isDel);
        dest.writeInt(this.isRead);
        dest.writeString(this.messageId);
        dest.writeString(this.title);
        dest.writeInt(this.type);
        dest.writeInt(this.userMsgId);
    }

    public MessageBean() {
    }

    protected MessageBean(Parcel in) {
        this.content = in.readString();
        this.createTime = in.readLong();
        this.creator = in.readString();
        this.isDel = in.readString();
        this.isRead = in.readInt();
        this.messageId = in.readString();
        this.title = in.readString();
        this.type = in.readInt();
        this.userMsgId = in.readInt();
    }

    public static final Parcelable.Creator<MessageBean> CREATOR = new Parcelable.Creator<MessageBean>() {
        @Override
        public MessageBean createFromParcel(Parcel source) {
            return new MessageBean(source);
        }

        @Override
        public MessageBean[] newArray(int size) {
            return new MessageBean[size];
        }

    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageBean bean = (MessageBean) o;

        if (createTime != bean.createTime) return false;
        if (isRead != bean.isRead) return false;
        if (type != bean.type) return false;
        if (userMsgId != bean.userMsgId) return false;
        if (content != null ? !content.equals(bean.content) : bean.content != null) return false;
        if (creator != null ? !creator.equals(bean.creator) : bean.creator != null) return false;
        if (isDel != null ? !isDel.equals(bean.isDel) : bean.isDel != null) return false;
        if (messageId != null ? !messageId.equals(bean.messageId) : bean.messageId != null)
            return false;
        return title != null ? title.equals(bean.title) : bean.title == null;
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (isDel != null ? isDel.hashCode() : 0);
        result = 31 * result + isRead;
        result = 31 * result + (messageId != null ? messageId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + userMsgId;
        return result;
    }
}
