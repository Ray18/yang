package com.xi.xlm.response.w;

import com.xi.xlm.entity.AttractEmployeeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @className: BusinessListResp
 * @Description:前台招商信息实体
 * @author:by yangtianfeng
 * @classDate: 2020/9/7 17:24
 * @Version: 1.0
 **/
@ApiModel("前台招商信息实体")
@Data
public class AttractBusinessListResp {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("大区员工信息")
    private AttractEmployeeInfo DQInfo;
    @ApiModelProperty("区域员工信息")
    private AttractEmployeeInfo QYInfo;


    /**
     * 岗位信息
     */
    @ApiModelProperty("岗位信息")
    private String jobInfo;

    /**
     * 姓名
     */
    @ApiModelProperty("经理姓名")
    private String jobName;


    /**
     * 国内外1国内0国外
     */
    @ApiModelProperty("国内外")
    private Boolean isCountry;
    /**
     * 地区
     */
    @ApiModelProperty("地区")
    private String city;
    /**
     * 客户姓名
     */
    @ApiModelProperty("客户姓名")
    private String name;

    /**
     * 客户电话
     */
    @ApiModelProperty("客户电话")
    private String phone;


    /**
     * 0待审核-1已确认-2洽谈阶段-3意向金阶段-4定金阶段-5设计阶段-6装修阶段-7已落店-8确认佣金-9已发放
     * 10无效商机
     */
    @ApiModelProperty("0待审核-1已确认-2洽谈阶段-3意向金阶段-4定金阶段-5设计阶段-6装修阶段-7已落店-8确认佣金-9已发放\n" +
            " 10无效商机")
    private Integer auditState;

    /**
     * 本次佣金
     */
    @ApiModelProperty("本次佣金")
    private BigDecimal thisCommission;

    @ApiModelProperty("时间")
    private LocalDateTime createDate;
}
