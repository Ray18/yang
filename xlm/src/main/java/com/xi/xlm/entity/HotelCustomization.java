package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelCustomization extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;



    /**
     * 项目名字
     */
    private String projectName;

    /**
     * 项目地址
     */
    private String projectAddress;

    /**
     * 联系人
     */
    private String linkName;

    /**
     * 联系方式
     */
    private String linkTel;

    /**
     * 高度
     */
    private String height;

    /**
     * 床芯
     */
    private String bedCore;

    /**
     * 特殊材质
     */
    private String specialMaterial;

    /**
     * 尺寸
     */
    private String size;

    /**
     * 备注
     */
    private String remark;


    public static final String ID = "id";

    public static final String PROJECT_NAME = "project_name";

    public static final String PROJECT_ADDRESS = "project_address";

    public static final String LINK_NAME = "link_name";

    public static final String LINK_TEL = "link_tel";

    public static final String HEIGHT = "height";

    public static final String BED_CORE = "bed_core";

    public static final String SPECIAL_MATERIAL = "special_material";

    public static final String SIZE = "size";

    public static final String REMARK = "remark";

}
