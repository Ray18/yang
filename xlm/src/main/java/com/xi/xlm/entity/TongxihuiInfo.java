package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 同喜汇信息
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TongxihuiInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String memberId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证
     */
    private String identityCard;

    /**
     * 职业
     */
    private String profession;

    /**
     * 电话
     */
    private String phone;

    /**
     * 0待审核 1通过 2未通过
     */
    private Integer status;

    /**
     * 1白银会员，2黄金会员，3白金会员，4钻石会员，5至尊会员
     */
    private Integer memberClass;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String NAME = "name";

    public static final String IDENTITY_CARD = "identity_card";

    public static final String PROFESSION = "profession";

    public static final String PHONE = "phone";

    public static final String STATUS = "status";



    public static final String MEMBER_CLASS = "member_class";

}
