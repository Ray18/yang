package com.xi.xlm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelQuotationDetails extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    private String quotationId;
    private String productId;


    private String productName;


    private String productSepc;


    private Integer productCount;



    private BigDecimal productPrice;

    private BigDecimal originalPrice;


    private String productImg;


    private BigDecimal productCountPrice;

    private Boolean productType;


    public static final String ID = "id";
    public static final String PRODUCT_IMG = "product_img";
    public static final String QUOTATION_ID = "quotation_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRODUCT_SEPC = "product_sepc";

    public static final String PRODUCT_COUNT = "product_count";

    public static final String PRODUCT_PRICE = "product_price";
    public static final String ORIGINAL_PRICE = "original_price";
    public static final String PRODUCT_COUNT_PRICE = "product_count_price";

    public static final String PRODUCT_TYPE = "product_type";

}
