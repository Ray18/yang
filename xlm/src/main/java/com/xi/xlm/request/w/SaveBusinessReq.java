package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @className: saveBusiness
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/15 16:57
 * @Version: 1.0
 **/
@ApiModel("提交商机")
@Data
public class SaveBusinessReq {

    /**
     * 酒店名称
     */
    @NotNull(message = "请填写酒店名称")
    private String hotelName;

    /**
     * 联系人
     */
    @NotNull(message = "请填写联系人")
    private String linkName;

    /**
     * 联系电话
     */
    @NotNull(message = "请填写联系电话")
    private String linkPhone;

    /**
     * 备注
     */
    private String remark;


}
