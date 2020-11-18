package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: SaveCustomizationReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 16:32
 * @Version: 1.0
 **/
@Data
@ApiModel("提交个性定制参数")
public class SaveCustomizationReq {


    /**
     * 项目名字
     */
    @ApiModelProperty("项目名字")
    private String projectName;

    /**
     * 项目地址
     */
    @ApiModelProperty("项目地址")
    private String projectAddress;

    /**
     * 联系人
     */
    @ApiModelProperty("联系人")
    private String linkName;

    /**
     * 联系方式
     */
    @ApiModelProperty("联系方式")
    private String linkTel;

    /**
     * 高度
     */
    @ApiModelProperty("高度")
    private String height;

    /**
     * 床芯
     */
    @ApiModelProperty("床芯")
    private String bedCore;

    /**
     * 特殊材质
     */
    @ApiModelProperty("特殊材质")
    private String specialMaterial;

    /**
     * 尺寸
     */
    @ApiModelProperty("尺寸")
    private String size;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;



}
