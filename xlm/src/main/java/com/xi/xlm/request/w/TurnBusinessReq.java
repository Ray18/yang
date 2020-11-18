package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @className: TurnAgencyReq
 * @Description: 同喜会提交参数
 * @author:by yangtianfeng
 * @classDate: 2020/9/5 15:48
 * @Version: 1.0
 **/
@ApiModel("同喜会提交参数")
@Data
public class TurnBusinessReq {

    /**
     * 姓名
     */
    @NotBlank(message = "请填写姓名")
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 身份证
     */
    @NotBlank(message = "请填写身份证")
    @ApiModelProperty("身份证")
    private String identityCard;

    /**
     * 职业
     */
    @NotBlank(message = "请填写职业")
    @ApiModelProperty("职业")
    private String profession;

    /**
     * 电话
     */
    @NotBlank(message = "请填写电话")
    @ApiModelProperty("电话")
    private String phone;
}
