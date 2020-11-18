package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @className: ListCommissionReq
 * @Description:佣金信息
 * @author:by yangtianfeng
 * @classDate: 2020/9/9 9:24
 * @Version: 1.0
 **/
@Data
@ApiModel("入参")
public class ListCommissionReq {
    @ApiModelProperty("用户ID")
    @NotBlank(message = "请传入用户id")
    private String memberId;
    @ApiModelProperty("类型2收入 1支出")
    private String type;

    @ApiModelProperty("提现金额")
    private BigDecimal commission;

}
