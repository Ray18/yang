package com.xi.xlm.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttracArea implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    private String name;

    private String parent;

    private Integer invalid;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PARENT = "parent";

    public static final String INVALID = "invalid";

}
