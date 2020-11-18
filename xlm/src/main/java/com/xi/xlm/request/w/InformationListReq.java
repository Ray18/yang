package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @className: InformationListReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 20:35
 * @Version: 1.0
 **/
@ApiModel("官方咨询入参")
@Data
public class InformationListReq {

    private Integer integralType;
    private String title;

}
