package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttractActivityRules implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String createBy;

    private LocalDateTime createDate;

    /**
     */
    private String updateBy;

    /**
     */
    private LocalDateTime updateDate;

    /**
     */
    private Boolean delFlag;

    /**
     */
    private String details;


    public static final String ID = "id";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_BY = "update_by";

    public static final String UPDATE_DATE = "update_date";

    public static final String DEL_FLAG = "del_flag";

    public static final String DETAILS = "details";

}
