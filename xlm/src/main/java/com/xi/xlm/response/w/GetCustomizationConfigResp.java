package com.xi.xlm.response.w;

import com.xi.xlm.entity.HotelCustomizationConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @className: GetCustomizationConfigResp
 * @Description:获取产品需求返回参数
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 14:24
 * @Version: 1.0
 **/
@ApiModel("获取产品需求返回参数")
@Data
public class GetCustomizationConfigResp {
    @ApiModelProperty("高度")
    List<HotelCustomizationConfig> heightList;
    @ApiModelProperty("床芯")
    List<HotelCustomizationConfig> bedCore;
    @ApiModelProperty("特殊材质")
    List<HotelCustomizationConfig> specialMaterial;
    @ApiModelProperty("尺寸")
    List<HotelCustomizationConfig> size;


}
