package com.xi.xlm.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("attrac_city_case")
public class AttracCityCase  extends BaseEntity{

    private String id;

    private String title;
    private String caseDetails;
    private String headImg;



    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String CASE_DETAILS = "case_details";

    public static final String HEAD_IMG = "head_img";


}
