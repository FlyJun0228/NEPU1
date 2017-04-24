package com.example.nedusoftware.myapplication.bean;

import java.util.List;

/**
 * Created by NEDUsoftware on 2017/4/24.
 */

public class Kdbean {


    /**
     * resultcode : 200
     * reason : 查询成功
     * result : {"company":"圆通","com":"yt","no":"884490956386670084","status":"1","list":[{"datetime":"2017-3-18 21:24:10","remark":"【北京市丰台区西客站公司】 已收件","zone":""},{"datetime":"2017-3-18 22:23:24","remark":"【北京市丰台区西客站公司】 已打包","zone":""},{"datetime":"2017-3-18 22:35:49","remark":"【北京市丰台区西客站公司】 已发出 下一站 【北京转运中心】","zone":""},{"datetime":"2017-3-19 0:34:06","remark":"【北京转运中心】 已收入","zone":""},{"datetime":"2017-3-19 0:35:17","remark":"【北京转运中心】 已发出 下一站 【长春转运中心】","zone":""},{"datetime":"2017-3-19 16:43:10","remark":"【长春转运中心】 已发出 下一站 【吉林省吉林市公司】","zone":""},{"datetime":"2017-3-20 4:28:21","remark":"【吉林省吉林市公司】 已收入","zone":""},{"datetime":"2017-3-20 4:29:38","remark":"【吉林省吉林市公司】 已发出 下一站 【吉林省吉林市船营区一公司】","zone":""},{"datetime":"2017-3-20 7:15:03","remark":"【吉林省吉林市船营区一公司】 已收入","zone":""},{"datetime":"2017-3-20 8:23:37","remark":"【吉林省吉林市船营区一公司】 派件人: 马吉林 派件中 派件员电话","zone":""},{"datetime":"2017-3-20 17:22:40","remark":"客户 签收人: 菜鸟驿站代签 已签收 感谢使用圆通速递，期待再次为您服务","zone":""}]}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * company : 圆通
         * com : yt
         * no : 884490956386670084
         * status : 1
         * list : [{"datetime":"2017-3-18 21:24:10","remark":"【北京市丰台区西客站公司】 已收件","zone":""},{"datetime":"2017-3-18 22:23:24","remark":"【北京市丰台区西客站公司】 已打包","zone":""},{"datetime":"2017-3-18 22:35:49","remark":"【北京市丰台区西客站公司】 已发出 下一站 【北京转运中心】","zone":""},{"datetime":"2017-3-19 0:34:06","remark":"【北京转运中心】 已收入","zone":""},{"datetime":"2017-3-19 0:35:17","remark":"【北京转运中心】 已发出 下一站 【长春转运中心】","zone":""},{"datetime":"2017-3-19 16:43:10","remark":"【长春转运中心】 已发出 下一站 【吉林省吉林市公司】","zone":""},{"datetime":"2017-3-20 4:28:21","remark":"【吉林省吉林市公司】 已收入","zone":""},{"datetime":"2017-3-20 4:29:38","remark":"【吉林省吉林市公司】 已发出 下一站 【吉林省吉林市船营区一公司】","zone":""},{"datetime":"2017-3-20 7:15:03","remark":"【吉林省吉林市船营区一公司】 已收入","zone":""},{"datetime":"2017-3-20 8:23:37","remark":"【吉林省吉林市船营区一公司】 派件人: 马吉林 派件中 派件员电话","zone":""},{"datetime":"2017-3-20 17:22:40","remark":"客户 签收人: 菜鸟驿站代签 已签收 感谢使用圆通速递，期待再次为您服务","zone":""}]
         */

        private String company;
        private String com;
        private String no;
        private String status;
        private List<ListBean> list;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

    }
}
