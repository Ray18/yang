package com.xi.xlm.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("attrac_product_introduce_type")
public class AttracProductIntroduceType  extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;


    public static final String ID = "id";

    public static final String NAME = "name";

}
