package com.xi.xlm.enums;

/**
 * @Author YangTianFeng
 * @Description 系统参数变量
 * @Date 13:28 2020/9/14
 * @Param
 * @return
 **/
public enum ParameterEnum {

    FWCN("fwcn", "服务承诺"),
    FWZX("fwzx", "服务咨询"),
    FWZX_WX("fwzx_wx", "服务咨询-微信号"),
    FWZX_ZJ("fwzx_zj", "服务咨询-座机"),
    FWZX_TEL("fwzx_tel", "服务咨询-手机号"),
    SWQT("swqt", "商务洽谈"),
    SJTG_HDGZ("sjtg_hdgz","商机提交-活动规则"),
    JFZH_JFGZ("jfzh_jfgz","积分账户-积分规则");


    private String value;
    private String remark;

    ParameterEnum(String value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public String value() {
        return this.value;
    }

    public String remark() {
        return this.remark;
    }

}
