package com.xi.xlm.response.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: AgencyInfoResp
 * @Description:经销商身份标识
 * @author:by yangtianfeng
 * @classDate: 2020/9/10 17:17
 * @Version: 1.0
 **/
@Data
@ApiModel("经销商身份标识")
public class AgencyInfoResp {
    @ApiModelProperty("经销商标识 1是 2不是未申请过 3审核中 4审核不通过")
    private int agency;

    @ApiModelProperty("同喜会标识 1是 2不是未申请过 3审核中 4审核不通过")
    private int tongXiHui;

}
