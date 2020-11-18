package com.xi.xlm.response.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @className: AttOrderListResp
 * @Description:购买记录
 * @author:by yangtianfeng
 * @classDate: 2020/9/7 17:10
 * @Version: 1.0
 **/
@ApiModel("购买记录")
@Data
public class AttOrderListResp {


    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("订单编号")
    private String orderNo;
    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    private String productName;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private BigDecimal price;
    @ApiModelProperty("图")
    private String banner;


}
