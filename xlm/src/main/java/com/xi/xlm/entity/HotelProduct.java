package com.xi.xlm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelProduct extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    @TableField("char016")
    private String char016;

    @TableField("kcfl")
    private String kcfl;

    @TableField("cdks")
    private String cdks;

    @TableField("zycz")
    private String zycz;

    @TableField("thlx")
    private String thlx;

    @TableField("cpyd")
    private String cpyd;

    @TableField("cpg")
    private String cpg;

    @TableField("details")
    private String details;

    @TableField("product_no")
    private String productNo;
    @TableField("img1")
    private String img1;
    @TableField("img2")
    private String img2;

    @TableField("img3")
    private String img3;

    @TableField(value = "del_fag")
    private String delFag;


    private String price;

    private boolean up;


    private String caseName;


    public static final String UP = "up";
    public static final String CASE_NAME = "case_name";
    public static final String PRICE = "price";

    public static final String DEL_FAG = "del_fag";
    public static final String ID = "id";


    public static final String CHAR016 = "char016";

    public static final String KCFL = "kcfl";

    public static final String CDKS = "cdks";

    public static final String ZYCZ = "zycz";

    public static final String THLX = "thlx";

    public static final String CPYD = "cpyd";

    public static final String CPG = "cpg";

    public static final String DETAILS = "details";


    public static final String PRODUCT_NO = "product_no";

    public static final String IMG1 = "img1";

    public static final String IMG2 = "img2";

    public static final String IMG3 = "img3";

}
