package com.xi.xlm.response.w;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @className: QuotationDetailsReq
 * @Description:
 * @author:by ya  ngtianfeng
 * @classDate: 2020/9/22 10:43
 * @Version: 1.0
 **/
@Data
public class QuotationDetailsExportResp extends BaseRowModel {

    @ExcelProperty("详情ID")
    private String id;
    /**
     * 产品ID
     */
    @ExcelProperty("产品ID")
    private String productId;

    /**
     * 产品名称
     */
    @ExcelProperty("产品名称")
    private String productName;

    /**
     * 产品规格
     */
    @ExcelProperty("产品规格")
    private String productSepc;

    /**
     * 产品数量
     */
    @ExcelProperty("产品数量")
    private Integer productCount;

    /**
     * 产品单价
     */
    @ExcelProperty("产品单价")
    private BigDecimal productPrice;

    /**
     * 产品总价
     */
    @ExcelProperty("产品总价")
    private BigDecimal productCountPrice;


    @ExcelProperty("true产品 false赠品")
    private Boolean productType;

    /**
     * 产品图片
     */
    @ExcelProperty("产品图片")
    private String productImg;

    /**
     * 产品原单价
     */
    @ExcelProperty("产品原单价")
    private BigDecimal originalPrice;
}
