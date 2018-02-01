package com.risenb.wette.beans;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/05/27
 *     desc   : 对服务器返回的 Banner 进行二次封装 已满足代码要求
 *     version: 1.0
 * </pre>
 */
public class BannerBean {
    /**
     * type : 1
     * code : 0
     * message : 获取数据成功。
     * resultdata : [{"Banner_ID":"2a1eddef-9f2b-4c84-9b85-72f3cdccd4b7","Banner_Url":"http://taskweb.whmnx.com/Resource/PhotoFile/4ac64ab2-29f7-470a-8eff-337299156838.jpg","Banner_LinkUrl":"http://taskweb.whmnx.comhttp://BxypDBshop.com/UserDy/Register","AppBanner_LikUrl":"http://taskweb.whmnx.com1","Category":"首页轮播图","Banner_Sort":2},{"Banner_ID":"8b175318-af4c-4fac-b9b9-0c9e27f79f9c","Banner_Url":"http://taskweb.whmnx.com/Resource/PhotoFile/c559ff1c-31ac-4f83-bd5c-ab7d9f7627de.jpg","Banner_LinkUrl":"http://taskweb.whmnx.com#","AppBanner_LikUrl":"http://taskweb.whmnx.com1","Category":"首页轮播图","Banner_Sort":3},{"Banner_ID":"a5cb572b-3865-4470-9e5b-68196b47aceb","Banner_Url":"http://taskweb.whmnx.com/Resource/PhotoFile/8ba8c8ea-e19e-4db9-b5b0-cf1fa5373af6.jpg","Banner_LinkUrl":"http://taskweb.whmnx.com#","AppBanner_LikUrl":"http://taskweb.whmnx.com1","Category":"首页轮播图","Banner_Sort":4},{"Banner_ID":"a65314ab-dff4-4c7f-8105-a3305261ff2a","Banner_Url":"http://taskweb.whmnx.com/Resource/PhotoFile/82337d9a-c887-4a1e-a621-a7b94471ce62.jpg","Banner_LinkUrl":"http://taskweb.whmnx.com#","AppBanner_LikUrl":"http://taskweb.whmnx.com1","Category":"首页轮播图","Banner_Sort":1}]
     */

    private int type;
    private int code;
    private String message;
    private List<ResultdataBean> resultdata;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultdataBean> getResultdata() {
        return resultdata;
    }

    public void setResultdata(List<ResultdataBean> resultdata) {
        this.resultdata = resultdata;
    }

    public static class ResultdataBean  implements MultiItemEntity{
        /**
         * Banner_ID : 2a1eddef-9f2b-4c84-9b85-72f3cdccd4b7
         * Banner_Url : http://taskweb.whmnx.com/Resource/PhotoFile/4ac64ab2-29f7-470a-8eff-337299156838.jpg
         * Banner_LinkUrl : http://taskweb.whmnx.comhttp://BxypDBshop.com/UserDy/Register
         * AppBanner_LikUrl : http://taskweb.whmnx.com1
         * Category : 首页轮播图
         * Banner_Sort : 2
         */

        private String Banner_ID;
        private String Banner_Url;
        private String Banner_LinkUrl;
        private String AppBanner_LikUrl;
        private String Category;
        private int Banner_Sort;

        public String getBanner_ID() {
            return Banner_ID;
        }

        public void setBanner_ID(String Banner_ID) {
            this.Banner_ID = Banner_ID;
        }

        public String getBanner_Url() {
            return Banner_Url;
        }

        public void setBanner_Url(String Banner_Url) {
            this.Banner_Url = Banner_Url;
        }

        public String getBanner_LinkUrl() {
            return Banner_LinkUrl;
        }

        public void setBanner_LinkUrl(String Banner_LinkUrl) {
            this.Banner_LinkUrl = Banner_LinkUrl;
        }

        public String getAppBanner_LikUrl() {
            return AppBanner_LikUrl;
        }

        public void setAppBanner_LikUrl(String AppBanner_LikUrl) {
            this.AppBanner_LikUrl = AppBanner_LikUrl;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public int getBanner_Sort() {
            return Banner_Sort;
        }

        public void setBanner_Sort(int Banner_Sort) {
            this.Banner_Sort = Banner_Sort;
        }

        @Override
        public int getItemType() {
            return 1001;
        }
    }

//    private List<HomePageRecommendBean.ResultdataBean> mBannersBeen;
//
//    public List<HomePageRecommendBean.ResultdataBean> getBannersBeen() {
//        return mBannersBeen;
//    }
//
//    public BannerBean setBannersBeen(List<HomePageRecommendBean.ResultdataBean> bannersBeen) {
//        mBannersBeen = bannersBeen;
//        return this;
//    }


}
