package com.xi.xlm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttractProduct extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String propertyId;



    private String productName;

    private BigDecimal price;
    private String coverImg;

    private String productDetails;
    private Boolean upState;


    public static final String ID = "id";

    public static final String PROPERTY_ID = "property_id";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRICE = "price";

    public static final String COVER_IMG = "cover_img";

    public static final String PRODUCT_DETAILS = "product_details";

    public static final String UP_STATE = "up_state";

}
