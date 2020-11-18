package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class HotelCustomizationConfig  extends  BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;




    private String name;


    private Integer type;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String TYPE = "type";

}
