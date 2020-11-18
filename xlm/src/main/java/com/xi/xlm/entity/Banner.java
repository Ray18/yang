package com.xi.xlm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.soap.Text;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("attrac_banner")
@EqualsAndHashCode(callSuper = false)
public class Banner  extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String bannerImg;

    private String bannerDetails;


    private String upState;

    public static final String ID = "id";

    public static final String UP_STATE = "up_state";

    public static final String BANNER_IMG = "banner_img";

    public static final String BANNER_DETAILS = "banner_details";

}
