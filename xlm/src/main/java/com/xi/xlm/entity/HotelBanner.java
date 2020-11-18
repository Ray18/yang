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
public class HotelBanner extends  BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    private String bannerImg;


    private String bannerDetails;


    private Boolean upState;


    public static final String ID = "id";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_BY = "update_by";

    public static final String UPDATE_DATE = "update_date";

    public static final String DEL_FLAG = "del_flag";

    public static final String BANNER_IMG = "banner_img";

    public static final String BANNER_DETAILS = "banner_details";

    public static final String UP_STATE = "up_state";

}
