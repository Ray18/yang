package com.xi.xlm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author yangtianfeng
 * @since 2020-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelQuotation extends  BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    private String name;



    private String memberId;


    private String memberName;


    private String memberWechat;


    private String memberWechatImg;

    private BigDecimal countPrice;


    private BigDecimal discountPrice;


    private Double discount;

    private String productName;

    public static final String PRODUCT_NAME = "product_name";
    public static final String ID = "id";

    public static final String NAME = "name";


    public static final String MEMBER_ID = "member_id";

    public static final String MEMBER_NAME = "member_name";

    public static final String MEMBER_WECHAT = "member_wechat";

    public static final String MEMBER_WECHAT_IMG = "member_wechat_img";

    public static final String COUNT_PRICE = "count_price";

    public static final String DISCOUNT_PRICE = "discount_price";

    public static final String DISCOUNT = "discount";

}
