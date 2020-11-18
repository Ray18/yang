package com.xi.xlm.response.m;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @className: AttractThreadResp
 * @Description:线索管理导出字段模型
 * @author:by yangtianfeng
 * @classDate: 2020/8/31 19:18
 * @Version: 1.0
 **/
@Data
public class AttractThreadResp extends BaseRowModel {
    //客户城市（国内国外）|客户姓名|客户电话|商机阶段|   从业经验|运营行业/品牌|经营理念|资金实力|年营业额|管理团队
    @ExcelProperty("客户城市")
    private String city;
    /**
     * 客户姓名
     */
    @ExcelProperty("客户姓名")
    private String name;

    /**
     * 客户电话
     */
    @ExcelProperty("客户电话")
    private String phone;

    @ExcelProperty("线索状态")
    private String auditState;
    /**
     * 从业经验
     */
    @ExcelProperty("从业经验")
    private String workingExperience;

    /**
     * 经营行业/品牌
     */
    @ExcelProperty("经营行业")
    private String industry;

    /**
     * 经营理念
     */
    @ExcelProperty("经营理念")
    private String industryIdea;

    /**
     * 资金实力
     */
    @ExcelProperty("资金实力")
    private String deepPocket;
    /**
     * 年营业额
     */
    @ExcelProperty("年营业额")
    private String yearBusiness;

    /**
     * 管理团队
     */
    @ExcelProperty("管理团队")
    private String managementTeam;

}
