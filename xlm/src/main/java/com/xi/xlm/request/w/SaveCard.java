package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @className: SaveCard
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/22 10:04
 * @Version: 1.0
 **/
@ApiModel("保存员工名片")
@Data
public class SaveCard {

    @ApiModelProperty("id 查到了就传 没查到就不传")
    private String id;

    /**
     * 员工姓名
     */
    @ApiModelProperty("员工姓名")
    @NotNull(message = "请填写员工姓名")
    private String employeeName;

    /**
     * 微信号
     */
    @ApiModelProperty("微信号")
    @NotNull(message = "请填写微信号")
    private String wechat;

    /**
     * 微信二维码
     */
    @ApiModelProperty("微信二维码")
    @NotNull(message = "请上传微信二维码")
    private String wechatImg;

}
