package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 全局系统参数
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Parameter extends  BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;



    /**
     * 参数值
     */
    private String parValue;

    /**
     * 参数描述
     */
    private String remark;

    /**
     * 参数code
     */
    private String code;

    /**
     * 图文详情
     */
    private String details;



    public static final String PAR_VALUE = "par_value";

    public static final String REMARK = "remark";

    public static final String CODE = "code";

    public static final String DETAILS = "details";

}
