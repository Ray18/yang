package com.xi.xlm.request.m;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: IntegralOrderReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/17 15:08
 * @Version: 1.0
 **/
@Data
public class IntegralOrderReq {

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 收货人姓名
     */
    private String takePersonName;

    /**
     * 手机号
     */
    private String phone;


    /**
     * 0代付款1已付款2代发货3已发货
     */
    private Integer orderState;

    private String createDate;

}
