package com.xi.xlm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**

 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelBusiness extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;




    private String memberId;
    private String nickName;


    private String memberPhone;


    private String hotelName;


    private String linkName;


    private String linkPhone;


    private String remark;


    private BigDecimal thisIntegral;


    private Integer audit;

    private String callMsg;

    public static final String CALL_MSG = "call_msg";
    public static final String ID = "id";
    public static final String NICK_NAME = "nick_name";

    public static final String MEMBER_ID = "member_id";

    public static final String MEMBER_PHONE = "member_phone";

    public static final String HOTEL_NAME = "hotel_name";

    public static final String LINK_NAME = "link_name";

    public static final String LINK_PHONE = "link_phone";

    public static final String REMARK = "remark";

    public static final String THIS_INTEGRAL = "this_integral";

    public static final String AUDIT = "audit";

}
