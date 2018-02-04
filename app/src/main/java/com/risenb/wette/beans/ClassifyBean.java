package com.risenb.wette.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yjyvi on 2018/2/3.
 */

public class ClassifyBean  implements  Serializable{

    /**
     * data : [{"categoryId":1,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":11,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":16,"categoryPid":11,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"儿童文学","sort":1},{"categoryId":17,"categoryPid":11,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"少儿英语","sort":2},{"categoryId":18,"categoryPid":11,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"幼儿启蒙","sort":3}],"name":"少儿","sort":1},{"categoryId":12,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":19,"categoryPid":12,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"名家作品及欣赏","sort":1},{"categoryId":20,"categoryPid":12,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"散文随笔","sort":2},{"categoryId":21,"categoryPid":12,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"文学史","sort":3}],"name":"文学","sort":2},{"categoryId":13,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":22,"categoryPid":13,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"推理小说","sort":1},{"categoryId":23,"categoryPid":13,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"历史小说","sort":2},{"categoryId":24,"categoryPid":13,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"世界名著","sort":3}],"name":"小说","sort":3},{"categoryId":14,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":25,"categoryPid":14,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"中国史","sort":1},{"categoryId":26,"categoryPid":14,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"欧洲史","sort":2},{"categoryId":27,"categoryPid":14,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"世界总史","sort":3}],"name":"历史","sort":4},{"categoryId":15,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":28,"categoryPid":15,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"投资理财","sort":1},{"categoryId":29,"categoryPid":15,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"经济学理论与读物","sort":2},{"categoryId":30,"categoryPid":15,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"企业经营与管理","sort":3},{"categoryId":31,"categoryPid":15,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"市场营销","sort":4}],"name":"经济管理","sort":5}],"name":"图书","sort":1},{"categoryId":2,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":32,"categoryPid":2,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":37,"categoryPid":32,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"耳机","sort":1},{"categoryId":38,"categoryPid":32,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"耳放","sort":2},{"categoryId":39,"categoryPid":32,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"耳机配件","sort":3}],"name":"耳机及配件","sort":1},{"categoryId":33,"categoryPid":2,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":40,"categoryPid":33,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"低音炮","sort":1},{"categoryId":41,"categoryPid":33,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"中置音箱","sort":2},{"categoryId":42,"categoryPid":33,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"落地音箱","sort":3},{"categoryId":43,"categoryPid":33,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"环绕音箱","sort":4}],"name":"音箱","sort":2},{"categoryId":34,"categoryPid":2,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[],"name":"智能设备","sort":3},{"categoryId":35,"categoryPid":2,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[],"name":"影音播放器","sort":4},{"categoryId":36,"categoryPid":2,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[],"name":"数码配件","sort":5}],"name":"数码影音","sort":2},{"categoryId":3,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":44,"categoryPid":3,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":47,"categoryPid":44,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"铅笔","sort":1},{"categoryId":48,"categoryPid":44,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"水彩笔","sort":2},{"categoryId":49,"categoryPid":44,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"圆珠笔","sort":3},{"categoryId":50,"categoryPid":44,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"中性笔","sort":4},{"categoryId":51,"categoryPid":44,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"钢笔","sort":5}],"name":"书写工具","sort":1},{"categoryId":45,"categoryPid":3,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":52,"categoryPid":45,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"电子词典","sort":1},{"categoryId":53,"categoryPid":45,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"复读机","sort":2},{"categoryId":54,"categoryPid":45,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"学习机","sort":3}],"name":"电子教育","sort":2},{"categoryId":46,"categoryPid":3,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":55,"categoryPid":46,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"多功能一体打印机","sort":1},{"categoryId":56,"categoryPid":46,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"投影仪","sort":2}],"name":"办公设备","sort":3}],"name":"办公用品","sort":3},{"categoryId":4,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":57,"categoryPid":4,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":58,"categoryPid":57,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"双肩背包","sort":1},{"categoryId":59,"categoryPid":57,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"旅行箱包","sort":2}],"name":"皮具箱包","sort":1}],"name":"服饰箱包","sort":4},{"categoryId":5,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":60,"categoryPid":5,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":62,"categoryPid":60,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"奶瓶用品","sort":1},{"categoryId":63,"categoryPid":60,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"餐具","sort":2}],"name":"喂养用品","sort":1},{"categoryId":61,"categoryPid":5,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":64,"categoryPid":61,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"三段奶粉","sort":1},{"categoryId":65,"categoryPid":61,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"四段、五段奶粉","sort":2}],"name":"奶粉","sort":2}],"name":"母婴用品","sort":5},{"categoryId":6,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[],"name":"运动户外","sort":6},{"categoryId":7,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[],"name":"鞋靴","sort":7},{"categoryId":8,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[],"name":"个护健康","sort":8},{"categoryId":9,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[],"name":"食品","sort":9},{"categoryId":10,"categoryPid":0,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[],"name":"酒水","sort":10}]
     * errorCode :
     * errorMsg :
     * msg :
     * status : 1
     * total : 10
     */

    private String errorCode;
    private String errorMsg;
    private String msg;
    private String status;
    private int total;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * categoryId : 1
         * categoryPid : 0
         * createTime : 1516859749000
         * creator : -1
         * image :
         * isDel : 0
         * lastUpdateTime : 1516859749000
         * lastUpdator : -1
         * list : [{"categoryId":11,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":16,"categoryPid":11,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"儿童文学","sort":1},{"categoryId":17,"categoryPid":11,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"少儿英语","sort":2},{"categoryId":18,"categoryPid":11,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"幼儿启蒙","sort":3}],"name":"少儿","sort":1},{"categoryId":12,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":19,"categoryPid":12,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"名家作品及欣赏","sort":1},{"categoryId":20,"categoryPid":12,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"散文随笔","sort":2},{"categoryId":21,"categoryPid":12,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"文学史","sort":3}],"name":"文学","sort":2},{"categoryId":13,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":22,"categoryPid":13,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"推理小说","sort":1},{"categoryId":23,"categoryPid":13,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"历史小说","sort":2},{"categoryId":24,"categoryPid":13,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"世界名著","sort":3}],"name":"小说","sort":3},{"categoryId":14,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":25,"categoryPid":14,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"中国史","sort":1},{"categoryId":26,"categoryPid":14,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"欧洲史","sort":2},{"categoryId":27,"categoryPid":14,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"世界总史","sort":3}],"name":"历史","sort":4},{"categoryId":15,"categoryPid":1,"createTime":1516859749000,"creator":-1,"image":"","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":[{"categoryId":28,"categoryPid":15,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"投资理财","sort":1},{"categoryId":29,"categoryPid":15,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"经济学理论与读物","sort":2},{"categoryId":30,"categoryPid":15,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"企业经营与管理","sort":3},{"categoryId":31,"categoryPid":15,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"市场营销","sort":4}],"name":"经济管理","sort":5}]
         * name : 图书
         * sort : 1
         */

        private int categoryId;
        private int categoryPid;
        private long createTime;
        private int creator;
        private String image;
        private int isDel;
        private long lastUpdateTime;
        private int lastUpdator;
        private String name;
        private int sort;
        private List<ListBeanX> list;

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getCategoryPid() {
            return categoryPid;
        }

        public void setCategoryPid(int categoryPid) {
            this.categoryPid = categoryPid;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCreator() {
            return creator;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public long getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(long lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public int getLastUpdator() {
            return lastUpdator;
        }

        public void setLastUpdator(int lastUpdator) {
            this.lastUpdator = lastUpdator;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX implements Serializable {
            /**
             * categoryId : 11
             * categoryPid : 1
             * createTime : 1516859749000
             * creator : -1
             * image :
             * isDel : 0
             * lastUpdateTime : 1516859749000
             * lastUpdator : -1
             * list : [{"categoryId":16,"categoryPid":11,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"儿童文学","sort":1},{"categoryId":17,"categoryPid":11,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"少儿英语","sort":2},{"categoryId":18,"categoryPid":11,"createTime":1516859749000,"creator":-1,"image":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg","isDel":0,"lastUpdateTime":1516859749000,"lastUpdator":-1,"list":"","name":"幼儿启蒙","sort":3}]
             * name : 少儿
             * sort : 1
             */

            private int categoryId;
            private int categoryPid;
            private long createTime;
            private int creator;
            private String image;
            private int isDel;
            private long lastUpdateTime;
            private int lastUpdator;
            private String name;
            private int sort;
            private List<ListBean> list;

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public int getCategoryPid() {
                return categoryPid;
            }

            public void setCategoryPid(int categoryPid) {
                this.categoryPid = categoryPid;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getCreator() {
                return creator;
            }

            public void setCreator(int creator) {
                this.creator = creator;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public long getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(long lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public int getLastUpdator() {
                return lastUpdator;
            }

            public void setLastUpdator(int lastUpdator) {
                this.lastUpdator = lastUpdator;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean implements Serializable{
                /**
                 * categoryId : 16
                 * categoryPid : 11
                 * createTime : 1516859749000
                 * creator : -1
                 * image : https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3728719661,2416398292&fm=27&gp=0.jpg
                 * isDel : 0
                 * lastUpdateTime : 1516859749000
                 * lastUpdator : -1
                 * list :
                 * name : 儿童文学
                 * sort : 1
                 */

                private int categoryId;
                private int categoryPid;
                private long createTime;
                private int creator;
                private String image;
                private int isDel;
                private long lastUpdateTime;
                private int lastUpdator;
                private String list;
                private String name;
                private int sort;

                public int getCategoryId() {
                    return categoryId;
                }

                public void setCategoryId(int categoryId) {
                    this.categoryId = categoryId;
                }

                public int getCategoryPid() {
                    return categoryPid;
                }

                public void setCategoryPid(int categoryPid) {
                    this.categoryPid = categoryPid;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getCreator() {
                    return creator;
                }

                public void setCreator(int creator) {
                    this.creator = creator;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public int getIsDel() {
                    return isDel;
                }

                public void setIsDel(int isDel) {
                    this.isDel = isDel;
                }

                public long getLastUpdateTime() {
                    return lastUpdateTime;
                }

                public void setLastUpdateTime(long lastUpdateTime) {
                    this.lastUpdateTime = lastUpdateTime;
                }

                public int getLastUpdator() {
                    return lastUpdator;
                }

                public void setLastUpdator(int lastUpdator) {
                    this.lastUpdator = lastUpdator;
                }

                public String getList() {
                    return list;
                }

                public void setList(String list) {
                    this.list = list;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }
        }
    }
}
