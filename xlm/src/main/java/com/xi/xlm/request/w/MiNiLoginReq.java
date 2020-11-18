package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @className: miniLoginReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/3 14:58
 * @Version: 1.0
 **/
@Data
@ApiModel("登录需要参数")
public class MiNiLoginReq {

    @NotNull(message = "code不能为空")
    private String code;
    private String encryptedData;
    private String vi;

}
