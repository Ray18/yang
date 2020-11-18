package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 酒店模块-案例展示分类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotelCaseShowType implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;



    /**
     * 分类名
     */
    private String name;




    public static final String NAME = "name";

}
