package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @className: QuotationDetailsReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/22 10:43
 * @Version: 1.0
 **/
@ApiModel("报价单入参")
@Data
public class QuotationDetailsReq {

    @ApiModelProperty("详情ID")
    private String id;
    /**
     * 产品ID
     */
    @ApiModelProperty("产品ID")
    private String productId;

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    private String productName;

    /**
     * 产品规格
     */
    @ApiModelProperty("产品规格")
    private String productSepc;

    /**
     * 产品数量
     */
    @ApiModelProperty("产品数量")
    private Integer productCount;

    /**
     * 产品单价
     */
    @ApiModelProperty("产品折扣单价")
    private BigDecimal productPrice;

    /**
     * 产品原单价
     */
    @ApiModelProperty("产品原单价")
    private BigDecimal originalPrice;
    /**
     * 产品总价
     */
    @ApiModelProperty("产品总价")
    private BigDecimal productCountPrice;
    /**
     * 产品图片
     */
    @ApiModelProperty("产品图片")
    private String productImg;
}
