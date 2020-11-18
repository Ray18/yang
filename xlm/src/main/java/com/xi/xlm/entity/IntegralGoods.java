package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IntegralGoods  extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    private String img1;

    private String img2;

    private String img3;


    private Boolean upState;


    private String goodsDetails;


    private String goodsName;

    private Integer salesCount;


    private Integer integral;


    private String goodsType;


    private Boolean newProduct;


    private String coverImg;


    public static final String ID = "id";


    public static final String IMG1 = "img1";

    public static final String IMG2 = "img2";

    public static final String IMG3 = "img3";

    public static final String UP_STATE = "up_state";

    public static final String GOODS_DETAILS = "goods_details";

    public static final String GOODS_NAME = "goods_name";

    public static final String SALES_COUNT = "sales_count";

    public static final String INTEGRAL = "integral";

    public static final String GOODS_TYPE = "goods_type";

    public static final String NEW_PRODUCT = "new_product";

    public static final String COVER_IMG = "cover_img";

}
