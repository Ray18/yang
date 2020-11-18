package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: CaseListReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 20:59
 * @Version: 1.0
 **/
@ApiModel("合作伙伴入参")
@Data
public class CaseListReq {
    /**
     * 精度
     */
    @ApiModelProperty("精度")
    private String lng;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String lat;

    @ApiModelProperty("城市")
    private String area;

    @ApiModelProperty("类型1酒店 2地产 3其他  默认1")
    private Integer hotelType;

    @ApiModelProperty("搜索")
    private String title;
}
