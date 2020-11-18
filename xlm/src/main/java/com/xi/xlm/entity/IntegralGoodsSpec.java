package com.xi.xlm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 积分商品规格表
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IntegralGoodsSpec  extends  BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;



    /**
     * 名称
     */
    private String name;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 规格封面
     */
    private String specImg;

    /**
     * 商品类型
     */
    private String goodsTypeId;

    /**
     * 商品类型名称
     */
    private String goodsTypeName;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 价格==所需积分
     */
    private BigDecimal price;

    /**
     * 规格采用json字符串{"id":"name"}同商品规格数量保持一致（暂不启用）
     */
    private String specDetails;


    public static final String ID = "id";


    public static final String NAME = "name";


    public static final String GOODS_ID = "goods_id";
    public static final String SPEC_IMG = "spec_img";

    public static final String GOODS_TYPE_ID = "goods_type_id";

    public static final String GOODS_TYPE_NAME = "goods_type_name";

    public static final String INVENTORY = "inventory";

    public static final String PRICE = "price";

    public static final String SPEC_DETAILS = "spec_details";

}
