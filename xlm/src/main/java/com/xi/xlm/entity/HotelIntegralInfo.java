package com.xi.xlm.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 积分信息表
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelIntegralInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户名称
     */
    private String nickName;

    /**
     * 关联会员
     */
    private String memberId;

    /**
     * 本次积分
     */
    private Integer thisIntegral;

    /**
     * 剩余总积分
     */
    private Integer integralCount;

    /**
     * 1积分兑换，2提供商机
     */
    private Integer integralType;

    /**
     * 关联商机
     */
    private String businessId;

    /**
     * 提供商机的酒店名称
     */
    private String hotelName;

    /**
     * 兑换订单
     */
    @TableField("order_Id")
    private String orderId;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 商品规格
     */
    private String goodsSpecification;

    /**
     * 商品数量
     */
    private Integer goodsAmount;




    public static final String ID = "id";

    public static final String NICK_NAME = "nick_name";

    public static final String MEMBER_ID = "member_id";

    public static final String THIS_INTEGRAL = "this_integral";

    public static final String INTEGRAL_COUNT = "integral_count";

    public static final String INTEGRAL_TYPE = "integral_type";

    public static final String BUSINESS_ID = "business_id";

    public static final String HOTEL_NAME = "hotel_name";

    public static final String ORDER_ID = "order_Id";

    public static final String GOODS_NAME = "goods_name";

    public static final String GOODS_SPECIFICATION = "goods_specification";

    public static final String GOODS_AMOUNT = "goods_amount";



}
