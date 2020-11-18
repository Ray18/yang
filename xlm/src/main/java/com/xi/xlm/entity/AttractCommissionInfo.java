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
 * @since 2020-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttractCommissionInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String memberId;

    private Integer commissionType;

    private BigDecimal thisCommission;

    private BigDecimal commissionCount;


    private String threadId;
    private String epositId;


    public static final String ID = "id";
    public static final String EPOSIT_ID = "eposit_id";
    public static final String MEMBER_ID = "member_id";

    public static final String COMMISSION_TYPE = "commission_type";

    public static final String THIS_COMMISSION = "this_commission";

    public static final String COMMISSION_COUNT = "commission_count";


    public static final String THREAD_ID = "thread_id";

}
