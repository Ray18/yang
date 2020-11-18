package com.xi.xlm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttractEmployeeInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String memberId;

    private Integer status;

    private String jobInfo;

    private String areaName;

    private String areaCode;
    private String name;

    private String phone;
    private String parentJob;


    public static final String PARENT_JOB = "parent_job";
    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String STATUS = "status";

    public static final String JOB_INFO = "job_info";

    public static final String AREA_NAME = "area_name";

    public static final String AREA_CODE = "area_code";

    public static final String NAME = "name";

    public static final String PHONE = "phone";


}
