package com.xi.xlm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttractCommissionRule extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String threadId;
    private Long submitTread;

    private Long talkRatio;

    private Long purposeRatio;
    private Long depositTatio;

    private Long seated;


    public static final String ID = "id";

    public static final String SUBMIT_TREAD = "submit_tread";
    public static final String THREAD_ID = "thread_id";

    public static final String TALK_RATIO = "talk_ratio";

    public static final String PURPOSE_RATIO = "purpose_ratio";

    public static final String DEPOSIT_TATIO = "deposit_tatio";

    public static final String SEATED = "seated";

}
