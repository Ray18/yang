package com.xi.xlm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttractDealerInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String memberId;

    private boolean country;

    private Integer status;

    private String joinCity;

    private String industry;
    private String linkPerson;
    private String linkTel;

    private String remark;

    public static final String LINK_TEL ="link_tel";
    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String STATUS = "status";

    public static final String JOIN_CITY = "join_city";

    public static final String INDUSTRY = "industry";

    public static final String LINK_PERSON = "link_person";
    private static final String IS_HOME = "is_home";

    public static final String REMARK = "remark";

}
