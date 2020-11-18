package com.xi.xlm.response.w;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @className: FWZXInfoResp
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 16:40
 * @Version: 1.0
 **/
@ApiModel("服务咨询")
@Data
public class FWZXInfoResp {

    private String details;
    private String wx;
    private String zj;
    private String tel;

}
