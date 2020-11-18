package com.xi.xlm.request.w;

import lombok.Data;

/**
 * @className: SaveHotelProduct
 * @Description:对外开放接口入参
 * @author:by yangtianfeng
 * @classDate: 2020/9/15 11:21
 * @Version: 1.0
 **/
@Data
public class SaveHotelProduct {
    /**
     * 产品名称
     */
    private String char016;

    /**
     * 分类
     */
    private String kcfl;

    /**
     * 床垫款式
     */
    private String cdks;

    /**
     * 主要材质
     */
    private String zycz;

    /**
     * 弹簧类型
     */
    private String thlx;

    /**
     * 成品硬度
     */
    private String cpyd;

    /**
     * 成品高
     */
    private String cpg;

    private String delFag;
}
