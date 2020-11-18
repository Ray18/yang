package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @className: TurnAgencyReq
 * @Description: 成为经销商提交参数
 * @author:by yangtianfeng
 * @classDate: 2020/9/5 15:48
 * @Version: 1.0
 **/
@ApiModel("我要成为经销商参数")
@Data
public class TurnAgencyReq {
    @ApiModelProperty("国内/国外 true/false")
    private boolean country;
    /**
     * 加盟城市
     */
    @NotBlank(message = "请填写城市")
    @ApiModelProperty("加盟城市")
    private String joinCity;

    /**
     * 经营行业
     */
    @NotBlank(message = "请填写经营行业")
    @ApiModelProperty("经营行业")
    private String industry;
    /**
     * 联系人
     */
    @NotBlank(message = "请填写联系人")
    @ApiModelProperty("联系人")
    private String linkPerson;
    /**
     * 联系电话
     */
    @NotBlank(message = "请填写电话")
    @ApiModelProperty("联系电话")
    private String linkTel;
}
