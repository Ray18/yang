package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 积分商品分类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 上级分类 0顶级分类
     */
    private String parent;

    /**
     * 名称
     */
    private String name;




    public static final String ID = "id";

    public static final String PARENT = "parent";

    public static final String NAME = "name";



}
