package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 酒店员工信息
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String memberId;

    /**
     * 0待审核 1通过 2未通过
     */
    private Integer status;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 凭证1
     */
    private String proofOne;

    /**
     * 凭证2
     */
    private String proofTwo;

    /**
     * 凭证3
     */
    private String proofThree;


    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 微信二维码
     */
    private String wechatImg;

    /**
     * 启用禁用
     */
    private Boolean up;

    public static final String UP = "up";
    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String STATUS = "status";

    public static final String NAME = "name";

    public static final String PHONE = "phone";

    public static final String JOB_NUMBER = "job_number";

    public static final String PROOF_ONE = "proof_one";

    public static final String PROOF_TWO = "proof_two";

    public static final String PROOF_THREE = "proof_three";


    public static final String EMPLOYEE_NAME = "employee_name";

    public static final String WECHAT = "wechat";

    public static final String WECHAT_IMG = "wechat_img";

}
