package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @className: SaveQuotationReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/22 10:36
 * @Version: 1.0
 **/
@ApiModel("保存报价单")
@Data
public class SaveQuotationReq {

    @ApiModelProperty("报价单ID")
    private String id;


    /**
     * 总价
     */
    @ApiModelProperty("总价")
    @NotNull(message = "总价不能为空")
    private BigDecimal countPrice;


    /**
     * 报价单优惠价
     */
    @ApiModelProperty("报价单优惠价")
    @NotNull(message = "折扣价不能为空")
    private BigDecimal discountPrice;

    /**
     * 报价单折扣
     */
    @ApiModelProperty("报价单折扣")
    @NotNull(message = "折扣不能为空")
    private Double discount;
    @ApiModelProperty("报价单名称")
    @NotNull(message = "报价单名称不能为空")
    private String productName;

    @ApiModelProperty("产品清单列表")
    private List<QuotationDetailsReq> reqs;

    @ApiModelProperty("赠品清单列表")
    private List<QuotationDetailsReq> reqList;

}
