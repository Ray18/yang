package com.xi.xlm.response.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: CommissionInfoIndexResp
 * @Description:首页佣金信息VO
 * @author:by yangtianfeng
 * @classDate: 2020/9/4 17:26
 * @Version: 1.0
 **/
@ApiModel("首页返回佣金信息")
@Data
public class CommissionInfoIndexResp {
    @ApiModelProperty("名字")
    private String nickName;
    @ApiModelProperty("价格")
    private String priceCount;
    private LocalDateTime createDate;

}
