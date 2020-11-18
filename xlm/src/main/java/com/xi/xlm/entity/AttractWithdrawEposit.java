package com.xi.xlm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class AttractWithdrawEposit extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


    private String memberId;


    private String money;
    private String nickName;
    private String phone;


    private Long isState;


    private boolean flas;


    private String remark;

    public static final String FLAS = "flas";
    public static final String ID = "id";
    public static final String PHONE = "phone";
    public static final String NICK_NAME = "nick_name";
    public static final String MEMBER_ID = "member_id";

    public static final String MONEY = "money";

    public static final String IS_STATE = "is_state";

    public static final String REMARK = "remark";

}
