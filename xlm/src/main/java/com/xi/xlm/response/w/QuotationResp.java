package com.xi.xlm.response.w;

import com.xi.xlm.request.w.QuotationDetailsReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @className: SaveQuotationReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/22 10:36
 * @Version: 1.0
 **/
@ApiModel("报价单返回视图")
@Data
public class QuotationResp {

    @ApiModelProperty("报价单ID")
    private String id;


    /**
     * 总价
     */
    @ApiModelProperty("总价")
    private BigDecimal countPrice;


    /**
     * 报价单优惠价
     */
    @ApiModelProperty("报价单优惠价")
    private BigDecimal discountPrice;

    private LocalDateTime createDate;


    /**
     * 报价人姓名
     */
    @ApiModelProperty("报价人姓名")
    private String memberName;

    /**
     * 报价人微信
     */
    @ApiModelProperty("报价人微信")
    private String memberWechat;

    /**
     * 报价人微信图片
     */
    @ApiModelProperty("报价人微信图片")
    private String memberWechatImg;

    /**
     * 报价单折扣
     */
    @ApiModelProperty("报价单折扣")
    private Double discount;
    @ApiModelProperty("报价单名称")
    private String productName;

    @ApiModelProperty("产品清单列表 ")
    private List<QuotationDetailsResp> reqs;


}
