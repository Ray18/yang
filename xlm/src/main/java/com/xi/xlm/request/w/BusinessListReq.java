package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: BusinessListReq
 * @Description: 商机列表查询参数
 * @author:by yangtianfeng
 * @classDate: 2020/9/7 19:06
 * @Version: 1.0
 **/
@ApiModel("商机列表查询参数")
@Data
public class BusinessListReq {

    @ApiModelProperty("用户id")
    private String memberId;
    @ApiModelProperty("商机状态 进程商机中传 11 其他正常状态传")
    private Integer auditState;
    @ApiModelProperty("线索id-下一步的时候传")
    private String id;

}
