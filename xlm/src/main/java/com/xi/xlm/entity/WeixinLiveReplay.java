package com.xi.xlm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 微信直播间回放
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinLiveReplay extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    /**
     * 回放过期时间
     */
    private String expireTime;

    /**
     * 回放创建时间
     */
    private String createTime;

    /**
     * 回放URL
     */
    private String mediaUrl;
    private String name;

    /**
     * 回放片段个数
     */
    private Integer total;


    public static final String ID = "id";


    public static final String EXPIRE_TIME = "expire_time";

    public static final String CREATE_TIME = "create_time";

    public static final String MEDIA_URL = "media_url";

    public static final String TOTAL = "total";

}
