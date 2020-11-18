package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 物资名称
     */
    private String goodsName;

    /**
     * 数量
     */
    private Integer goodsNum;

    /**
     * 单位
     */
    private String goodsUnit;

    /**
     * 1.口罩，2消毒液，3 手套
     */
    private Integer goodsType;

    /**
     * 预计使用天数
     */
    private String goodsUseDay;

    /**
     * 累积使用数量
     */
    private Integer goodsAccumulateNum;

    private LocalDateTime createdDate;

    /**
     * 0删除1未删除
     */
    private String del;


    public static final String ID = "id";

    public static final String GOODS_NAME = "goods_name";

    public static final String GOODS_NUM = "goods_num";

    public static final String GOODS_UNIT = "goods_unit";

    public static final String GOODS_TYPE = "goods_type";

    public static final String GOODS_USE_DAY = "goods_use_day";

    public static final String GOODS_ACCUMULATE_NUM = "goods_accumulate_num";

    public static final String CREATED_DATE = "created_date";

    public static final String DEL = "del";

}
