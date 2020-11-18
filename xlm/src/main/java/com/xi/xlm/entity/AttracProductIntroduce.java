package com.xi.xlm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("attrac_product_introduce")
public class AttracProductIntroduce extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = 6127805335509142345L;
    private String id;

    private String producName;
    private String contentDetails;
    private String headImg;

    private String type;
    private String typeName;

    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String TYPE_NAME = "type_name";

    public static final String PRODUC_NAME = "produc_name";

    public static final String CONTENT_DETAILS = "content_details";

    public static final String HEAD_IMG = "head_img";

}
