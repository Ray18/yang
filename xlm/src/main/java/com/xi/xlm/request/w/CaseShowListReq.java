package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: caseShowList
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 13:05
 * @Version: 1.0
 **/
@Data
@ApiModel("案例展示查询条件")
public class CaseShowListReq {

    @ApiModelProperty("查询框")
    private String name;

    @ApiModelProperty("分类ID")
    private String typeId;

}
