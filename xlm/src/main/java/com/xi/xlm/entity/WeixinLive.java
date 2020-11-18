package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 微信直播间
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinLive  extends  BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;



    /**
     * 直播间名称
     */
    private String name;

    /**
     * 直播间id
     */
    private String roomid;

    /**
     * 直播间背景图
     */
    private String coverImg;

    /**
     * 直播间分享图
     */
    private String shareImg;

    /**
     * 直播间状态。101：直播中，102：未开始，103已结束，104禁播，105：暂停，106：异常，107：已过期
     */
    private String liveStatus;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 主播名
     */
    private String anchorName;

    /**
     * 拉取总数
     */
    private Integer total;

    /**
     * 1上0下
     */
    private Boolean up;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ROOMID = "roomid";

    public static final String COVER_IMG = "cover_img";

    public static final String SHARE_IMG = "share_img";

    public static final String LIVE_STATUS = "live_status";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String ANCHOR_NAME = "anchor_name";

    public static final String TOTAL = "total";

    public static final String UP = "up";

}
