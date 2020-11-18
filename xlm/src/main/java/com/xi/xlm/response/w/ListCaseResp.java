package com.xi.xlm.response.w;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @className: ListCaseResp
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 21:07
 * @Version: 1.0
 **/
@ApiModel("合作伙伴回参")
@Data
public class ListCaseResp {




    private String id;


    /**
     * 类型选择 1酒店 2地产 3其他  默认1
     */
    private Integer hotelType;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 图一
     */
    private String img1;

    /**
     * 图二
     */
    private String img2;

    /**
     * 图三
     */
    private String img3;

    /**
     * 项目地址
     */
    private String projectAddress;

    /**
     * 精度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 案例名称
     */
    private String caseName;

    /**
     * 头图
     */
    private String headImg;

    /**
     * 图文详情
     */
    private String details;

    /**
     * 案例参数
     */
    private String caseSku;

    /**
     * 级别
     */
    private Integer caseClass;

    /**
     * 距离
     */
    private Double distance;

}
