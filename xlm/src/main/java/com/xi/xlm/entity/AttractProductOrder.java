package com.xi.xlm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class AttractProductOrder extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String attractProductId;

    private String productName;

    private BigDecimal price;

    private String takePersonName;

    private String phone;

    private String province;

    private String city;
    private String district;

    private String addressDetails;
    private String buyerHaedImg;

    private String buyerNickName;

    private String buyerTel;

    private Integer orderState;

    private String orderNo;

    private String webMemberId;


    public static final String ID = "id";



    private static final String DISTRICT="district";
    public static final String ATTRACT_PRODUCT_ID = "attract_product_id";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRICE = "price";

    public static final String TAKE_PERSON_NAME = "take_person_name";

    public static final String PHONE = "phone";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String ADDRESS_DETAILS = "address_details";

    public static final String BUYER_HAED_IMG = "buyer_haed_img";

    public static final String BUYER_NICK_NAME = "buyer_nick_name";

    public static final String BUYER_TEL = "buyer_tel";

    public static final String ORDER_STATE = "order_state";

    public static final String ORDER_NO = "order_no";

    public static final String WEB_MEMBER_ID = "web_member_id";

}
