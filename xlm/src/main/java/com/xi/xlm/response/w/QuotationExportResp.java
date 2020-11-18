package com.xi.xlm.response.w;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @className: SaveQuotationReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/22 10:36
 * @Version: 1.0
 **/
@Data
public class QuotationExportResp extends BaseRowModel {

    @ExcelProperty("报价单ID")
    private String id;


    /**
     * 总价
     */
    @ExcelProperty("总价")
    private BigDecimal countPrice;


    /**
     * 报价单优惠价
     */
    @ExcelProperty("报价单优惠价")
    private BigDecimal discountPrice;

/*    @ExcelProperty("创建时间")
    private LocalDateTime createDate;*/


    /**
     * 报价人姓名
     */
    @ExcelProperty("报价人姓名")
    private String memberName;

    /**
     * 报价人微信
     */
    @ExcelProperty("报价人微信")
    private String memberWechat;

    /**
     * 报价人微信图片
     */
    @ExcelProperty("报价人微信图片")
    private String memberWechatImg;

    /**
     * 报价单折扣
     */
    @ExcelProperty("报价单折扣")
    private Double discount;
    @ExcelProperty("报价单名称")
    private String productName;


    @ExcelProperty("产品清单列表")
    private String details;


}
