package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelCaseShow extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String caseName;

    private String caseInfo;

    private String headImg;

    private String type;

    private String typeName;
    private String contentDetails;

    private Boolean up;

    private String img1;

    private String img2;

    private String img3;


    public static final String ID = "id";


    public static final String CASE_NAME = "case_name";

    public static final String CASE_INFO = "case_info";

    public static final String HEAD_IMG = "head_img";

    public static final String TYPE = "type";

    public static final String TYPE_NAME = "type_name";

    public static final String CONTENT_DETAILS = "content_details";

    public static final String UP = "up";

    public static final String IMG1 = "img1";

    public static final String IMG2 = "img2";

    public static final String IMG3 = "img3";

}
