package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @className: SubmitBusinessReq
 * @Description: 提供商机实体
 * @author:by yangtianfeng
 * @classDate: 2020/9/7 13:53
 * @Version: 1.0
 **/
@ApiModel("提供商机实体")
@Data
public class SubmitBusinessReq {


    @ApiModelProperty("用户id")
    private String memberId;
    /**
     * 国内外1国内0国外
     */
    @ApiModelProperty("国内外true国内false国外")
    @NotBlank(message = "国内外不能为空")
    private Boolean isCountry;

    /**
     * 地区
     */
    @ApiModelProperty("地区")
    @NotBlank(message = "地区不能为空")
    private String city;

    /**
     * 客户姓名
     */
    @ApiModelProperty("客户姓名")
    @NotBlank(message = "客户姓名不能为空")
    private String name;

    /**
     * 客户电话
     */
    @ApiModelProperty("客户电话")
    @NotBlank(message = "客户电话不能为空")
    private String phone;

    /**
     * 从业经验
     */
    @ApiModelProperty("从业经验")
    @NotBlank(message = "从业经验不能为空")
    private String workingExperience;

    /**
     * 经营行业/品牌
     */
    @ApiModelProperty("经营行业")
    @NotBlank(message = "经营行业不能为空")
    private String industry;

    /**
     * 经营理念
     */
    @ApiModelProperty("经营理念")
    @NotBlank(message = "经营理念不能为空")
    private String industryIdea;

    /**
     * 资金实力
     */
    @ApiModelProperty("资金实力")
    @NotBlank(message = "资金实力不能为空")
    private String deepPocket;

    /**
     * 年营业额
     */
    @ApiModelProperty("年营业额")
    @NotBlank(message = "年营业额不能为空")
    private String yearBusiness;

    /**
     * 管理团队
     */
    @ApiModelProperty("管理团队")
    @NotBlank(message = "管理团队不能为空")
    private String managementTeam;

    /**
     * 总分
     */
    @ApiModelProperty("总分")
    @NotBlank(message = "总分不能为空")
    private String totalPoints;



}
