package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @className: AttractProductOrderReq
 * @Description:下单信息
 * @author:by yangtianfeng
 * @classDate: 2020/9/5 16:33
 * @Version: 1.0
 **/
@Data
@ApiModel("下单信息")
public class AttractProductOrderReq {
    /**
     * 关联产品id
     */
    @ApiModelProperty("产品ID")
    @NotBlank(message = "请选择产品")
    private String attractProductId;

    @ApiModelProperty("地址ID")
    @NotBlank(message = "请填写地址ID")
    private String addressId;

    /**
     * 收货人姓名
     */
    @ApiModelProperty("收货人姓名")
    private String takePersonName;

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    private String productName;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    @NotBlank(message = "请填写价格")
    private BigDecimal price;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 地址省
     */
    @ApiModelProperty("地址省")
    private String province;

    /**
     * 地址市
     */
    @ApiModelProperty("地址市")
    private String city;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    private String addressDetails;

    /**
     * 下单会员
     */
    @ApiModelProperty("下单会员")
    private String webMemberId;

}
