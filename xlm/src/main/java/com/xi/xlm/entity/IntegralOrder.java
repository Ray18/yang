package com.xi.xlm.entity;

import java.math.BigDecimal;
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
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IntegralOrder  extends  BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;



    /**
     * 下单会员
     */
    private String webMemberId;

    /**
     * 关联产品id
     */
    private String integraProductId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 规格id
     */
    private String specId;

    /**
     * 规格详情
     */
    private String specDetails;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 收货人姓名
     */
    private String takePersonName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地址省
     */
    private String province;

    /**
     * 地址市
     */
    private String city;

    /**
     * 地址区
     */
    private String district;

    /**
     * 详细地址
     */
    private String addressDetails;

    /**
     * 买家头像
     */
    private String buyerHaedImg;

    /**
     * 买家昵称
     */
    private String buyerNickName;

    /**
     * 买家手机号
     */
    private String buyerTel;

    /**
     * 0代付款1已付款2代发货3已发货
     */
    private Integer orderState;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 购买数量
     */
    private Integer count;

    /**
     * 总价
     */
    private BigDecimal countPrice;


    public static final String ID = "id";


    public static final String WEB_MEMBER_ID = "web_member_id";

    public static final String INTEGRA_PRODUCT_ID = "integra_product_id";

    public static final String PRODUCT_NAME = "product_name";

    public static final String SPEC_ID = "spec_id";

    public static final String SPEC_DETAILS = "spec_details";

    public static final String PRICE = "price";

    public static final String TAKE_PERSON_NAME = "take_person_name";

    public static final String PHONE = "phone";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String DISTRICT = "district";

    public static final String ADDRESS_DETAILS = "address_details";

    public static final String BUYER_HAED_IMG = "buyer_haed_img";

    public static final String BUYER_NICK_NAME = "buyer_nick_name";

    public static final String BUYER_TEL = "buyer_tel";

    public static final String ORDER_STATE = "order_state";

    public static final String ORDER_NO = "order_no";

    public static final String COUNT = "count";

    public static final String COUNT_PRICE = "count_price";

}
