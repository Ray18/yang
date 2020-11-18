package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @className: HotelGoodsOrderReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/17 11:31
 * @Version: 1.0
 **/
@ApiModel("积分商品下单入参")
@Data
public class HotelGoodsOrderReq {
    @ApiModelProperty("商品ID")
    @NotNull(message = "请传入商品ID")
    private String productId;

    @ApiModelProperty("规格ID")
    @NotNull(message = "请传入规格ID")
    private String specId;
    @ApiModelProperty("数量")
    @NotNull(message = "请传入数量")
    private int count;
    @ApiModelProperty("地址ID")
    @NotNull(message = "请传入地址ID")
    private String addressId;
}
