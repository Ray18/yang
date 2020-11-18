package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 酒店版块-官方资讯
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelInformation extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    /**
     * 标题
     */
    private String title;

    /**
     * 头图
     */
    private String headImg;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 详情
     */
    private String details;

    /**
     * 1工具2内容
     */
    private Integer type;


    public static final String ID = "id";


    public static final String TITLE = "title";

    public static final String HEAD_IMG = "head_img";

    public static final String FILE_URL = "file_url";

    public static final String DETAILS = "details";

    public static final String TYPE = "type";

}
