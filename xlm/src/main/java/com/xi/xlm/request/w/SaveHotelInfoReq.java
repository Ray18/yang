package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @className: SaveHotelInfoReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/21 13:56
 * @Version: 1.0
 **/
@ApiModel("提交员工信息")
@Data
public class SaveHotelInfoReq {

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    @NotNull(message = "请填写姓名")
    private String name;

    /**
     * 联系方式
     */
    @ApiModelProperty("联系方式")
    @NotNull(message = "请填写联系方式")
    private String phone;

    /**
     * 工号
     */
    @ApiModelProperty("工号")
    @NotNull(message = "请填写工号")
    private String jobNumber;

    /**
     * 凭证1
     */
    @ApiModelProperty("凭证1")
    @NotNull(message = "请填写凭证")
    private String proofOne;

    /**
     * 凭证2
     */
    @ApiModelProperty("凭证2")
    private String proofTwo;

    /**
     * 凭证3
     */
    @ApiModelProperty("凭证3")
    private String proofThree;

}
