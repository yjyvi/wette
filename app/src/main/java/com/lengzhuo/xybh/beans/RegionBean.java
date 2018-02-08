package com.lengzhuo.xybh.beans;

import java.util.List;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/06
 *     desc   : 省 市 区
 *     version: 1.0
 * </pre>
 */
public class RegionBean {


    /**
     * regionCode : 110000
     * regionName : 北京市
     * id : 1
     * cityList : [{"regionCode":"110100","regionName":"北京市","areaList":[{"regionCode":"110101","regionName":"东城区","id":3},{"regionCode":"110102","regionName":"西城区","id":4},{"regionCode":"110105","regionName":"朝阳区","id":5},{"regionCode":"110106","regionName":"丰台区","id":6},{"regionCode":"110107","regionName":"石景山区","id":7},{"regionCode":"110108","regionName":"海淀区","id":8},{"regionCode":"110109","regionName":"门头沟区","id":9},{"regionCode":"110111","regionName":"房山区","id":10},{"regionCode":"110112","regionName":"通州区","id":11},{"regionCode":"110113","regionName":"顺义区","id":12},{"regionCode":"110114","regionName":"昌平区","id":13},{"regionCode":"110115","regionName":"大兴区","id":14},{"regionCode":"110116","regionName":"怀柔区","id":15},{"regionCode":"110117","regionName":"平谷区","id":16},{"regionCode":"110118","regionName":"密云区","id":17},{"regionCode":"110119","regionName":"延庆区","id":18}],"id":2}]
     */

    private String regionCode;
    private String regionName;
    private int id;
    private List<CityListBean> cityList;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CityListBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityListBean> cityList) {
        this.cityList = cityList;
    }

    public static class CityListBean {
        /**
         * regionCode : 110100
         * regionName : 北京市
         * areaList : [{"regionCode":"110101","regionName":"东城区","id":3},{"regionCode":"110102","regionName":"西城区","id":4},{"regionCode":"110105","regionName":"朝阳区","id":5},{"regionCode":"110106","regionName":"丰台区","id":6},{"regionCode":"110107","regionName":"石景山区","id":7},{"regionCode":"110108","regionName":"海淀区","id":8},{"regionCode":"110109","regionName":"门头沟区","id":9},{"regionCode":"110111","regionName":"房山区","id":10},{"regionCode":"110112","regionName":"通州区","id":11},{"regionCode":"110113","regionName":"顺义区","id":12},{"regionCode":"110114","regionName":"昌平区","id":13},{"regionCode":"110115","regionName":"大兴区","id":14},{"regionCode":"110116","regionName":"怀柔区","id":15},{"regionCode":"110117","regionName":"平谷区","id":16},{"regionCode":"110118","regionName":"密云区","id":17},{"regionCode":"110119","regionName":"延庆区","id":18}]
         * id : 2
         */

        private String regionCode;
        private String regionName;
        private int id;
        private List<AreaListBean> areaList;

        public String getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(String regionCode) {
            this.regionCode = regionCode;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<AreaListBean> getAreaList() {
            return areaList;
        }

        public void setAreaList(List<AreaListBean> areaList) {
            this.areaList = areaList;
        }

        public static class AreaListBean {
            /**
             * regionCode : 110101
             * regionName : 东城区
             * id : 3
             */

            private String regionCode;
            private String regionName;
            private int id;

            public String getRegionCode() {
                return regionCode;
            }

            public void setRegionCode(String regionCode) {
                this.regionCode = regionCode;
            }

            public String getRegionName() {
                return regionName;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
