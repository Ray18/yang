package com.xi.xlm.request.m;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @className: EmployeeInfoRequest
 * @Description:招商员工导入字段
 * @author:by yangtianfeng
 * @classDate: 2020/8/31 15:26
 * @Version: 1.0
 **/
@Data
public class EmployeeInfoRequest extends BaseRowModel {

    /**
     * 岗位信息
     */
    @ExcelProperty("岗位信息")
    private String jobInfo;

    /**
     * 所属地区
     */
    @ExcelProperty("所属地区")
    private String areaName;
    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 电话
     */
    @ExcelProperty("电话")
    private String phone;

}
