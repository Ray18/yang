package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @className: NextThreadReq
 * @Description:审核商机参数
 * @author:by yangtianfeng
 * @classDate: 2020/9/7 21:49
 * @Version: 1.0
 **/
@ApiModel("审核商机参数")
@Data
public class NextThreadReq {

    @ApiModelProperty("是否无效商机 0无效，1=下一步 2确认提交")
    private int fl;

    @ApiModelProperty("id")
    @NotBlank(message = "线索不能为空"
    )
    private String id;

    @ApiModelProperty("用户id")
    private String memberId;


    /**
     * 是否协助提供商机
     */
    @ApiModelProperty("是否协助提供商机")
    private Boolean isSub;

    /**
     * 是否协助洽谈
     */
    @ApiModelProperty("是否协助洽谈")
    private Boolean isTalk;

    /**
     * 协助缴纳意向金
     */
    @ApiModelProperty("协助缴纳意向金")
    private Boolean isPurpose;

    /**
     * 协助缴纳定金
     */
    @ApiModelProperty("协助缴纳定金")
    private Boolean isSeated;

    /**
     * 本次佣金
     */
    @ApiModelProperty("本次佣金")
    private BigDecimal thisCommission;

}
