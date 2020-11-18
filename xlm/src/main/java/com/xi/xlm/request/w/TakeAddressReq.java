package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @className: TakeAddressReq
 * @Description:添加个人地址
 * @author:by yangtianfeng
 * @classDate: 2020/9/7 10:19
 * @Version: 1.0
 **/
@Data
@ApiModel("添加地址实体")
public class TakeAddressReq {
    @ApiModelProperty("地址id")
    private String id;

    @ApiModelProperty("会员id")
    private String memberId;

    /**
     * 收货人姓名
     */
    @ApiModelProperty("收货人姓名")
    @NotBlank(message = "请填写收货人姓名")
    private String takePersonName;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @NotBlank(message = "请填写手机号")
    private String phone;

    /**
     * 地址省
     */
    @ApiModelProperty("地址省")
    @NotBlank(message = "请填写地址省")
    private String province;

    /**
     * 地址市
     */
    @ApiModelProperty("地址市")
    @NotBlank(message = "请填写地址市")
    private String city;
    @ApiModelProperty("地址区")
    @NotBlank(message = "请填写地址区")
    private String district;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    @NotBlank(message = "请填写详细地址")
    private String addressDetails;

    /**
     * 1是 0不是
     */
    @ApiModelProperty("是否默认，true false")
    @NotNull(message = "请填写是否默认")
    private Boolean isDefault;

}
