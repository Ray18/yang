package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @className: GetProductListReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 19:29
 * @Version: 1.0
 **/
@Data
@ApiModel("所有产品入参")
public class GetProductListReq {

    private String productName;
    private String typeName;

}
