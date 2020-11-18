package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**

 * @author yangtianfeng
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelCase  extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;



    private Integer hotelType;


    private String projectName;


    private String img1;


    private String img2;


    private String img3;

    private String projectAddress;


    private String province;


    private String city;


    private String district;



    private String lng;


    private String lat;


    private String caseName;


    private String headImg;

    private String details;


    private String caseSku;


    private Integer caseClass;


    private Boolean up;

    public static final String PROVINCE = "province";

    public static final String CITY = "city";
    private static final String DISTRICT="district";
    public static final String ID = "id";

    public static final String HOTEL_TYPE = "hotel_type";

    public static final String PROJECT_NAME = "project_name";

    public static final String IMG1 = "img1";

    public static final String IMG2 = "img2";

    public static final String IMG3 = "img3";

    public static final String PROJECT_ADDRESS = "project_address";

    public static final String LNG = "lng";

    public static final String LAT = "lat";

    public static final String CASE_NAME = "case_name";

    public static final String HEAD_IMG = "head_img";

    public static final String DETAILS = "details";

    public static final String CASE_SKU = "case_sku";

    public static final String CASE_CLASS = "case_class";

    public static final String UP = "up";

}
