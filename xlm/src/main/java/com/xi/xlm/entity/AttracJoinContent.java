package com.xi.xlm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("attrac_join_content")
public class AttracJoinContent  extends  BaseEntity{
    private String id;

    private String type;
    private String details;


    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String DETAILS = "details";


}
