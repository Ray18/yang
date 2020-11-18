package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 走进喜临门
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WalkContent extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;



    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String imgs;


    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String IMGS = "imgs";

}
