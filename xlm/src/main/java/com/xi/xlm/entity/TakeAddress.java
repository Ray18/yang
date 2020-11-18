package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author yangtianfeng
 * @since 2020-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TakeAddress extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String memberId;


    private String takePersonName;


    private String phone;


    private String province;

    private String city;

    private String district;


    private String addressDetails;

    private Boolean isDefault;


    public static final String ID = "id";

    public static final String MEMBER_ID = "member_id";

    public static final String TAKE_PERSON_NAME = "take_person_name";

    public static final String PHONE = "phone";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";
    private static final String DISTRICT="district";

    public static final String ADDRESS_DETAILS = "address_details";

    public static final String IS_DEFAULT = "is_default";



}
