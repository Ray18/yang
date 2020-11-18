package com.xi.xlm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 前台用户
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebMember extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;



    private String sessionKey;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 1白银会员，2黄金会员，3白金会员，4钻石会员，5至尊会员
     */
    private Integer memberClass;

    /**
     * 用户unionId
     */
    private String unionId;

    /**
     * 微信的openid
     */
    private String wxOpenId;

    /**
     * 用户性别 0未知 1男 2女*
     */
    private Integer gender;

    /**
     * 用户头像图片
     */
    private String avatarUrl;

    /**
     * 用户国家
     */
    private String country;

    /**
     * 用户省份
     */
    private String province;

    /**
     * 用户城市
     */
    private String city;

    /**
     * 酒店会员标识 1是 0否
     */
    private Boolean hotelFlag;

    /**
     * 招商员工标识 1 是 0否
     */
    private Boolean attractFlag;

    /**
     * 经销商标识 1是0否
     */
    private Boolean dealerFlag;

    /**
     * 同喜汇标识1是0否
     */
    private Boolean tongxihuiFlag;

    /**
     * 账户总积分
     */
    private BigDecimal integral;

    /**
     * 账户金额
     */
    private BigDecimal sumMoney;


    public static final String SESSION_KEY = "session_key";

    public static final String ID = "id";

    public static final String NICK_NAME = "nick_name";

    public static final String PHONE = "phone";

    public static final String MEMBER_CLASS = "member_class";

    public static final String UNION_ID = "union_id";

    public static final String WX_OPEN_ID = "wx_open_id";

    public static final String GENDER = "gender";

    public static final String AVATAR_URL = "avatar_url";

    public static final String COUNTRY = "country";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String HOTEL_FLAG = "hotel_flag";

    public static final String ATTRACT_FLAG = "attract_flag";

    public static final String DEALER_FLAG = "dealer_flag";

    public static final String TONGXIHUI_FLAG = "tongxihui_flag";

    public static final String INTEGRAL = "integral";

    public static final String SUM_MONEY = "sum_money";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_BY = "update_by";

    public static final String UPDATE_DATE = "update_date";

    public static final String DEL_FLAG = "del_flag";

}
