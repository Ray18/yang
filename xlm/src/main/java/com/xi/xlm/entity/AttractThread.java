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
 * @since 2020-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttractThread extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String memberId;

    private BigDecimal thisCommission;


    private Boolean isCountry;

    private String city;

    private String name;

    private String phone;

    private String workingExperience;
    private String industry;

    private String industryIdea;

    private String deepPocket;

    private String yearBusiness;

    private String managementTeam;

    private String totalPoints;

    private Integer auditState;

    private String regionUserId;

    private String regionUserName;
    private String regionalUserId;

    private String regionalUserName;
    private Boolean isSub;

    private Boolean isTalk;

    private Boolean isPurpose;

    private Boolean isSeated;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String THIS_COMMISSION = "this_commission";


    public static final String IS_COUNTRY = "is_country";

    public static final String CITY = "city";

    public static final String NAME = "name";

    public static final String PHONE = "phone";

    public static final String WORKING_EXPERIENCE = "working_experience";

    public static final String INDUSTRY = "industry";

    public static final String INDUSTRY_IDEA = "industry_idea";

    public static final String DEEP_POCKET = "deep_pocket";

    public static final String YEAR_BUSINESS = "year_business";

    public static final String MANAGEMENT_TEAM = "management_team";

    public static final String TOTAL_POINTS = "total_points";

    public static final String AUDIT_STATE = "audit_state";

    public static final String REGION_USER_ID = "region_user_id";

    public static final String REGIONAL_USER_ID = "regional_user_id";

    public static final String IS_SUB = "is_sub";

    public static final String IS_TALK = "is_talk";

    public static final String IS_PURPOSE = "is_purpose";

    public static final String IS_SEATED = "is_seated";

}
